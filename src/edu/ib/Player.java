package edu.ib;

import java.util.ArrayList;

public abstract class Player implements Pile {
    private ArrayList<Card> cards = new ArrayList<>();
    private int n;

    public int getN() {
        n = cards.size();
        return n;
    }

    @Override
    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public void move(int i, Pile o2) {
        o2.getCards().add(getCards().get(i));
        getCards().remove(i);
    }
}
