package edu.ib;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle(); // przetasowanie
        Table table = new Table();
        Player p1 = new Player();
        Player p2 = new Player();
        Player[] p = {p1, p2}; // gracze
        
        for (int i = 0; i < 5; i++) {
            p[0].getHand().add(deck.getDeck().get(deck.getDeck().size() - 1)); // dodanie karty graczowi 1
            deck.getDeck().remove(deck.getDeck().size() - 1); // usunięcie karty z puli kart
            p[1].getHand().add(deck.getDeck().get(deck.getDeck().size() - 1));
            deck.getDeck().remove(deck.getDeck().size() - 1);
        }
        table.getCards().add(deck.getDeck().get(deck.getDeck().size() - 1));
        deck.getDeck().remove(deck.getDeck().size() - 1);
        Scanner scan1 = new Scanner(System.in);
        String rank = "";
        String suit = "";
        Boolean winner = true;
        String winnerName = "";
        while (winner) {
            for (int i = 0; i < p.length; i++) {
                Boolean flag1 = true;
                System.out.println("GRACZ " + (i + 1));
                while (flag1) {
                    System.out.println("Twoje karty:");
                    for (int j = 0; j < p[i].getHand().size(); j++) {
                        System.out.println(p[i].getHand().get(j).getRank() + p[i].getHand().get(j).getSuit());
                    }
                    System.out.println("Karta na stole:");
                    System.out.println(table.getCards().get(table.getCards().size() - 1).getRank() + table.getCards().get(table.getCards().size() - 1).getSuit());
                    if (deck.getDeck().size() == 0) break;
                    while (flag1) {
                        rank = scan1.nextLine();
                        suit = scan1.nextLine();
                        if (rank.equals("Pociągnij")) {
                            p[i].getHand().add(deck.getDeck().get(deck.getDeck().size() - 1));
                            deck.getDeck().remove(deck.getDeck().size() - 1);
                            break;
                        }
                        Boolean match = true;
                        Boolean hasCard = false;
                        for (int j = 0; j < p[i].getHand().size(); j++) {
                            if (p[i].getHand().get(j).getRank().equals(rank) && p[i].getHand().get(j).getSuit().equals(suit)) {
                                hasCard = true;
                                match = false;
                                if (p[i].getHand().get(j).getSuit().equals(table.getCards().get(table.getCards().size() - 1).getSuit())
                                        || p[i].getHand().get(j).getRank().equals(table.getCards().get(table.getCards().size() - 1).getRank())
                                        || p[i].getHand().get(j).getRank().equals("8")) {
                                    match = true;
                                    table.getCards().add(p[i].getHand().get(j));
                                    p[i].getHand().remove(j);
                                    flag1 = false;
                                }
                            }
                        }
                        if (!hasCard) {
                            System.out.println("Nie posiadasz tej karty");
                        }
                        if (!match) {
                            System.out.println("Karta nie pasuje");
                        }
                    }
                }
                if (p[i].getHand().size() == 0) {
                    winner = false;
                    winnerName = "Player" + (i + 1);
                    System.out.println("Winner: " + winnerName);
                    break;
                }
            }
        }
    }
}




