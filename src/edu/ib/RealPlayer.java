package edu.ib;

import java.util.ArrayList;
import java.util.Scanner;

public class RealPlayer extends Player {
    boolean real = true;

    @Override
    public boolean isReal() {
        return super.isReal(real);
    }

    @Override
    public Card turn(String rank, String suit, Table table, String currentSuit) {
        Card card = new Card();
        Boolean match = true;
        Boolean hasCard = false;

        for (int j = 0; j < getN(); j++) {

            if (getCards().get(j).getRank().equals(rank) && getCards().get(j).getSuit().equals(suit)) { //sprawdzenie czy karta jest na ręce
                hasCard = true;
                match = false;

                if (getCards().get(j).getSuit().equals(currentSuit)
                        || getCards().get(j).getRank().equals(table.getCards().get(table.getCards().size() - 1).getRank())
                        || (getCards().get(j).getRank().equals("8") && getN() != 1)) { // sprawdzenie czy karta pasuje do karty na stole
                    match = true; // karta pasuje
                    card = getCards().get(j);
                    // JavaFX - komunikat
                } else if (getCards().get(j).getRank().equals("8") && getN() == 1) {
                    System.out.println("Nie możesz skończyć ósemką");
                }
            }
        }

        // JavaFX - komunikat - tego nie będzie, bo gracz wybiera karty spośród widocznych na ekranie
        if (!hasCard) { // gracz nie ma karty, którą zadeklarował
            System.out.println("Nie posiadasz tej karty");
        }

        // JavaFX - komunikat
        if (!match) { // karta, którą zadeklarował gracz, nie pasuje
            System.out.println("Karta nie pasuje");
        }
        return super.turn(card);
    }
}
