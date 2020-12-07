package edu.ib;

import java.util.ArrayList;

public class Table {
    private ArrayList<Card> cards;

    public Table() {
        ArrayList cards = new ArrayList<>();
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
