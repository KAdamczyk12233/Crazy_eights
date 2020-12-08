
package edu.ib;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle(); // przetasowanie
        Table table = new Table();
        Player p1 = new Player();
        Player p2 = new Player();
        Player[] p = {p1, p2}; // gracze
        for (int j = 0; j < p.length; j++) {
            for (int i = 0; i < 5; i++) {
                p[j].getHand().add(deck.getDeck().get(deck.getDeck().size() - 1)); // dodanie karty graczowi 1
                deck.getDeck().remove(deck.getDeck().size() - 1); // usunięcie karty z puli kart
            }
        }

        table.getCards().add(deck.getDeck().get(deck.getDeck().size() - 1)); // karta na stół
        deck.getDeck().remove(deck.getDeck().size() - 1);
        Scanner scan1 = new Scanner(System.in);

        String rank = "";
        String suit = "";
        Boolean winner = true;
        String winnerName = "";

        while (winner) { // rozgrywka

            for (int i = 0; i < p.length; i++) { // tura gry
                Boolean flag1 = true;
                System.out.println("GRACZ " + (i + 1));

                while (flag1) {

                    if (deck.getDeck().size() == 0) {  // sprawdzenie czy deck jest pusty
                        Boolean continueplay = false;
                        for (int j = 0; j < p[i].getHand().size(); j++) {
                            if (p[i].getHand().get(j).getSuit().equals(table.getCards().get(table.getCards().size() - 1).getSuit())
                                    || p[i].getHand().get(j).getRank().equals(table.getCards().get(table.getCards().size() - 1).getRank())
                                    || p[i].getHand().get(j).getRank().equals("8")) {
                                continueplay = true;
                            }
                        }

                        ArrayList<Card> newDeck = new ArrayList<>(); //przetasowanie nowego stosu
                        for (int j = 0; j < table.getCards().size() - 1; j++) {
                            newDeck.add(table.getCards().get(j));
                            table.getCards().remove(j);
                        }
                        deck.setDeck(newDeck);
                        deck.shuffle();
                        if (!continueplay) { //sprawdzenie czy turę można kontynuować
                            System.out.println("Brak kart na stosie, koniec tury");
                            break;
                        }
                    }

                    System.out.println("Twoje karty:");
                    for (int j = 0; j < p[i].getHand().size(); j++) {
                        System.out.println(p[i].getHand().get(j).getRank() + p[i].getHand().get(j).getSuit());
                    }

                    System.out.println("\nKarta na stole:");
                    System.out.println(table.getCards().get(table.getCards().size() - 1).getRank()
                            + table.getCards().get(table.getCards().size() - 1).getSuit());


                    while (flag1) { // ruch gracza
                        rank = scan1.nextLine();
                        suit = scan1.nextLine();

                        if (rank.equals("Pociągnij")) { // gracz dobiera karty
                            p[i].getHand().add(deck.getDeck().get(deck.getDeck().size() - 1));
                            deck.getDeck().remove(deck.getDeck().size() - 1);
                            break;
                        }

                        Boolean match = true;
                        Boolean hasCard = false;

                        for (int j = 0; j < p[i].getHand().size(); j++) {

                            if (p[i].getHand().get(j).getRank().equals(rank) && p[i].getHand().get(j).getSuit().equals(suit)) { //sprawdzenie czy karta jest na ręce
                                hasCard = true;
                                match = false;

                                if (p[i].getHand().get(j).getSuit().equals(table.getCards().get(table.getCards().size() - 1).getSuit())
                                        || p[i].getHand().get(j).getRank().equals(table.getCards().get(table.getCards().size() - 1).getRank())
                                        || p[i].getHand().get(j).getRank().equals("8")) { // sprawdzenie czy karta pasuje do karty na stole
                                    match = true; // karta pasuje
                                    table.getCards().add(p[i].getHand().get(j)); // dodanie karty do stołu
                                    p[i].getHand().remove(j); // usunięcie karty od gracza
                                    flag1 = false;
                                }
                            }
                        }

                        if (!hasCard) { // gracz nie ma karty, którą zadeklarował
                            System.out.println("Nie posiadasz tej karty");
                        }

                        if (!match) { // karta, którą zadeklarował gracz, nie pasuje
                            System.out.println("Karta nie pasuje");
                        }
                    }
                }

                if (p[i].getN() == 0) { // wygrana
                    winner = false;
                    winnerName = "Player" + (i + 1);
                    System.out.println("Winner: " + winnerName);
                    break;
                }
            }
        }
    }
}
