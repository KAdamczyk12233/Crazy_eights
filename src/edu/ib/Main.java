package edu.ib;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle(); // przetasowanie
        Table table = new Table();
        RealPlayer p1 = new RealPlayer();
        ComputerPlayer p2 = new ComputerPlayer();
        Player[] p = {p1, p2}; // gracze
        for (int j = 0; j < p.length; j++) {
            for (int i = 0; i < 5; i++) {
                deck.move(deck.getCards().size() - 1, p[j]);
            }
        }

        deck.move(deck.getCards().size() - 1, table);
        Scanner scan1 = new Scanner(System.in);

        String rank = "";
        String suit = "";
        Boolean winner = true;
        String winnerName = "";
        Card currentCard = new Card();

        while (winner) { // rozgrywka

            for (int i = 0; i < p.length; i++) { // tura gry
                Boolean flag1 = true;
                System.out.println("GRACZ " + (i + 1));
                if (p[i].isReal()) {
                    while (flag1) {

                        if (deck.getCards().size() == 0) {  // sprawdzenie czy deck jest pusty
                            Boolean continueplay = false;
                            for (int j = 0; j < p[i].getN(); j++) {
                                if (p[i].getCards().get(j).getSuit().equals(table.getCards().get(table.getCards().size() - 1).getSuit())
                                        || p[i].getCards().get(j).getRank().equals(table.getCards().get(table.getCards().size() - 1).getRank())
                                        || p[i].getCards().get(j).getRank().equals("8")) {
                                    continueplay = true;
                                }
                            }

                            for (int j = 0; j < table.getCards().size() - 1; j++) {
                                table.move(j, deck);
                            }
                            deck.shuffle();
                            System.out.println("Potasowano");
                            if (!continueplay) { //sprawdzenie czy turę można kontynuować
                                System.out.println("Brak kart na stosie, koniec tury");
                                break;
                            }
                        }

                        System.out.println("Twoje karty:");
                        for (int j = 0; j < p[i].getN(); j++) {
                            System.out.println(p[i].getCards().get(j).getRank() + p[i].getCards().get(j).getSuit());
                        }

                        System.out.println("\nKarta na stole:");
                        System.out.println(table.getCards().get(table.getCards().size() - 1).getRank()
                                + table.getCards().get(table.getCards().size() - 1).getSuit());


                        while (flag1) { // ruch gracza
                            rank = scan1.nextLine();
                            if (rank.equals("P")) { // gracz dobiera karty
                                p[i].getCards().add(deck.getCards().get(deck.getCards().size() - 1));
                                deck.getCards().remove(deck.getCards().size() - 1);
                                break;
                            }
                            suit = scan1.nextLine();
                            currentCard = p[i].turn(rank, suit, table);
                            if (!currentCard.getSuit().equals("notyet")) {
                                flag1 = false;
                                for (int j = 0; j < p[i].getCards().size(); j++) {
                                    if (p[i].getCards().get(j).getRank().equals(currentCard.getRank()) && p[i].getCards().get(j).getSuit().equals(currentCard.getSuit())) {
                                        p[i].move(j, table);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    while (flag1) {

                        if (deck.getCards().size() == 0) {  // sprawdzenie czy deck jest pusty
                            Boolean continueplay = false;
                            for (int j = 0; j < p[i].getN(); j++) {
                                if (p[i].getCards().get(j).getSuit().equals(table.getCards().get(table.getCards().size() - 1).getSuit())
                                        || p[i].getCards().get(j).getRank().equals(table.getCards().get(table.getCards().size() - 1).getRank())
                                        || p[i].getCards().get(j).getRank().equals("8")) {
                                    continueplay = true;
                                }
                            }

                            for (int j = 0; j < table.getCards().size() - 1; j++) {
                                table.move(j, deck);
                            }
                            deck.shuffle();
                            System.out.println("Potasowano");
                            if (!continueplay) { //sprawdzenie czy turę można kontynuować
                                System.out.println("Brak kart na stosie, koniec tury");
                                break;
                            }
                        }

                        System.out.println("Twoje karty:");
                        for (int j = 0; j < p[i].getN(); j++) {
                            System.out.println(p[i].getCards().get(j).getRank() + p[i].getCards().get(j).getSuit());
                        }

                        System.out.println("\nKarta na stole:");
                        System.out.println(table.getCards().get(table.getCards().size() - 1).getRank()
                                + table.getCards().get(table.getCards().size() - 1).getSuit());


                        while (flag1) { // ruch gracza
                            boolean madeAMove = false;
                            for (int j = 0; j < p[i].getCards().size(); j++) {
                                if (!p[i].getCards().get(j).getRank().equals("8")) {
                                    rank = p[i].getCards().get(j).getRank();
                                    suit = p[i].getCards().get(j).getSuit();
                                    currentCard = p[i].turn(rank, suit, table);
                                    if (!currentCard.getSuit().equals("notyet")) {
                                        flag1 = false;
                                        for (int h = 0; h < p[i].getCards().size(); h++) {
                                            if (p[i].getCards().get(h).getRank().equals(currentCard.getRank()) && p[i].getCards().get(h).getSuit().equals(currentCard.getSuit())) {
                                                p[i].move(h, table);
                                                madeAMove = true;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!madeAMove) {
                                for (int j = 0; j < p[i].getCards().size(); j++) {
                                    if (p[i].getCards().get(j).getRank().equals("8")) {
                                        rank = p[i].getCards().get(j).getRank();
                                        suit = p[i].getCards().get(j).getSuit();
                                        currentCard = p[i].turn(rank, suit, table);
                                        if (!currentCard.getSuit().equals("notyet")) {
                                            flag1 = false;
                                            for (int h = 0; h < p[i].getCards().size(); h++) {
                                                if (p[i].getCards().get(h).getRank().equals(currentCard.getRank()) && p[i].getCards().get(h).getSuit().equals(currentCard.getSuit())) {
                                                    p[i].move(h, table);
                                                    madeAMove = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!madeAMove) {
                                p[i].getCards().add(deck.getCards().get(deck.getCards().size() - 1));
                                deck.getCards().remove(deck.getCards().size() - 1);
                            }
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
