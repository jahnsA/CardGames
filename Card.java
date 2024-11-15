//Abby Jahns, Aliza Eaton, Rowan McKernan
//Card.java
//Lab 2: Card Game 10/08/24
//Card class represents a playing card
//object class

// Define enum for Face
enum Face {
    ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

// Define enum for Suit
enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

public class Card {
    private final Face face; // Enum for face of card
    private final Suit suit; // Enum for suit of card

    // Two-argument constructor initializes card's face and suit
    public Card(Face cardFace, Suit cardSuit) {
        this.face = cardFace; // Initialize face of card
        this.suit = cardSuit; // Initialize suit of card
    }

    // Return String representation of Card
    public String toString() {
        return face + " of " + suit;
    }

    // Get method for face
    public Face getFace() {
        return this.face;
    }
}