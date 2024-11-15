//Abby Jahns, Aliza Eaton, Rowan McKernan
//MainTestMethod.java
//Lab 2: Card Game 10/08/24
//test class

import java.util.Scanner;

public class MainTestMethod {

    public static void main(String[] args) throws Exception {
        Scanner choice = new Scanner(System.in);
        // Create an instance of DeckOfCards
        DeckOfCards myDeckOfCards = new DeckOfCards();
        //initialize newGame to 0 so game runs once
        int newGame = 0;
        myDeckOfCards.explainGame(); // Explain the game
        while (newGame == 0){
            myDeckOfCards.playGame(); // Start the game
            System.out.println("Would you like to play another(0 yes 1 no)?");
            newGame = choice.nextInt();
        } //end while loop
        System.out.println("Thanks for playing!");
    } //end method main
}//end class MainTestMethod