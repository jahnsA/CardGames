//Abby Jahns, Aliza Eaton, Rowan McKernan
//DeckOfCards.java
//Lab 2: Card Game 10/08/24
//DeckOfCards class represents a deck of playing cards.
//manager class
import java.security.SecureRandom;
import java.util.ArrayList; //for random numbers
import java.util.Scanner;


public class DeckOfCards {

    //random number generator  ---> from Darrell's program.
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52;

    //creates an array of Card objects (deck)
    private Card[] deck = new Card[NUMBER_OF_CARDS]; //Card references
    private int currentCard = 0; //index of next Card to be dealt (0 -51)

     // Constructor fills deck of cards
     public DeckOfCards() {
        // Populate deck with Card objects using Face and Suit enums
        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(Face.values()[count % 13], Suit.values()[count / 13]);
        }
    } 

    //shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        //next call to method dealCard should start at deck[0] again
        currentCard = 0;

        //for each Card, pick another random Card (0-51) and swap them  ---- This is from Darrell's code, but I'm not sure we need this.  
        for (int first = 0; first < deck.length; first++) {
            //select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            //swap current Card with randomly selected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }//end for loop
    }//end of shuffle method

    //deals one card
    public Card dealCard() {  
        //determine whether Cards remain to be dealt
        if (currentCard < deck.length) {
            return deck[currentCard++]; //return current Card in array
        }
        else {
            return null; //return null to indicate that all Cards were dealt
        }//end if/else
    }//end of dealCard method 

    // Plays 1 game
    public void playGame() {   
        Scanner choice = new Scanner(System.in);

        DeckOfCards myDeckOfCards = new DeckOfCards();
        myDeckOfCards.shuffle(); //place Cards to random order

        //player Hand ArrayList
        ArrayList<Card> playerHand = new ArrayList<Card>();
        playerHand.add(myDeckOfCards.dealCard());
        playerHand.add(myDeckOfCards.dealCard());
        //dealer hand arraylist
        ArrayList<Card> dealerHand = new ArrayList<Card>();
        dealerHand.add(myDeckOfCards.dealCard());
        dealerHand.add(myDeckOfCards.dealCard());

        //print out cards dealt to player
        System.out.println("Your cards are: ");
        for (int i = 0; i < playerHand.size(); i ++) {
            System.out.println(playerHand.get(i));
        }
        System.out.println();

        //checks that player hasn't gone over 21, if not asks to hit or stay
        if (myDeckOfCards.totalHand(playerHand) > 21) {
            System.out.println("You are over 21.");
        } else{
            System.out.println("Your current hand total is: " + myDeckOfCards.totalHand(playerHand));
        }

        //scanner Hit or Stay   
        System.out.println("Do you want to Hit(0) or end game(1)?: ");
        int playerChoice = choice.nextInt();

        //if hit, deal a new card and reevalaute (while true)
        while(playerChoice == 0){
            playerHand.add(myDeckOfCards.dealCard());
            //shows player their cards
            System.out.println("Your cards are: ");
                    for (int i = 0; i < playerHand.size(); i ++) {
                        System.out.println(playerHand.get(i));
                    }
                    System.out.println();
            //checks that player hasn't gone over 21, if not asks to hit or stay
            if (myDeckOfCards.totalHand(playerHand) > 21) {
                System.out.println("You are over 21.");
                break;
            } else{
                System.out.println("Your current hand total is: " + myDeckOfCards.totalHand(playerHand));
                System.out.println("Do you want to Hit(0) or end game(1)?: ");
                playerChoice = choice.nextInt();
            }
        }
        myDeckOfCards.showWinner(playerHand, dealerHand); 
    } // end of playGame method

    //explains how to play blackjack
    public void explainGame() {
        System.out.printf("%n%38s%n", "Welcome to Blackjack!");
        System.out.println("The objective of this game is to get as close to 21 without stricking out.");
        System.out.println("You will recieve two cards and have the option to ask for another (hit)");
        System.out.println("or end the game (stay). If your total is closer to 21 you win!");
        System.out.println("If the dealer is closer you lose. Beware to not go over 21 or you will bust!");
        System.out.println("(For this game aces are worth 11)");
        System.out.println();
    } // end of explainGame method

    //method total the value of cards in array that it is passed
    public int totalHand(ArrayList<Card> hand) {
        int total = 0;
        for (int i = 0; i < hand.size(); i++) {
            switch (hand.get(i).getFace()) {
                case ACE:
                    total += 11;
                    break;
                case DEUCE:
                    total += 2;
                    break;
                case THREE:
                    total += 3;
                    break;
                case FOUR:
                    total += 4;
                    break;
                case FIVE:
                    total += 5;
                    break;
                case SIX:
                    total += 6;
                    break;
                case SEVEN:
                    total += 7;
                    break;
                case EIGHT:
                    total += 8;
                    break;
                case NINE:
                    total += 9;
                    break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING:
                    total += 10;
                    break;
            }
        }
        return total;
    }

    //after player chooses "stay" this method compares dealer and player
    //prints out who wins
    public void showWinner(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
        System.out.println("Your total is " + this.totalHand(playerHand));
        System.out.println("The dealers hand is " + this.totalHand(dealerHand));

        if (this.totalHand(playerHand) > this.totalHand(dealerHand) && 
        this.totalHand(playerHand) < 22) {
            System.out.println("You Win!");
        } else {
            System.out.println("You Lose!");
        }
    }//end showWinner method

}//end of BlackJack Game