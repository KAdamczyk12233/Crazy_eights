package edu.ib;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand = new ArrayList<>();
    private int n = hand.size();

    public int getN() {
        return n;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
