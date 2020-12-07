package edu.ib;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;

    public Player() {
        ArrayList<Card> hand = new ArrayList<>();
        this.hand = hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
