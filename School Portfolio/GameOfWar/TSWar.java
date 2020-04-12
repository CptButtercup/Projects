/*
-----------------------------------------------------------------------------
Name:                 Thomas Shear
Filename:             TSWar.java
Due Date:             05/18/2017
Program Description:  This program plays the card game war. It creates a deck
                      of cards in order. It then shuffles the deck to randomize
                      the cards. It deals every other card to each player. Then
                      starts the game. It outputs the results to a file called
                      "war.out". If the players put down cards of the same
                      value it goes into a "WAR" state. When in war players keep
                      putting down cards until one wins then that player gets
                      all the cards played.
Input:                none
Output:               "war.out" file
Errors:               IOException, no others
-----------------------------------------------------------------------------
*/
//-------- Import Statements ------------------------------------------------
import java.io.*;
/*
-----------------------------------------------------------------------------
Class Name:  TSWar
Description: This class has one method, the main method
-----------------------------------------------------------------------------
*/

public class TSWar
{
   /*
   --------------------------------------------------------------------------
   Method Name: main
   Description: One method, the main method.
   --------------------------------------------------------------------------
   */
   
   public static void main(String[] args) throws IOException
   {
      //---------------- Local Constants ------------------------------------
      //---------------- Local Variables ------------------------------------
      //---------------- Objects --------------------------------------------
      TSDeck myDeck = new TSDeck();
      TSCard[] theDeck = new TSCard[52];
      TSPlayerHand player1 = new TSPlayerHand();
      TSPlayerHand player2 = new TSPlayerHand();
      
      File outFile = new File("war.out");
      FileOutputStream fOS = new FileOutputStream(outFile);
      PrintWriter printer = new PrintWriter(fOS);
      //---------------- Method Body ----------------------------------------
      
      myDeck.buildDeck(theDeck);
      
      myDeck.setDeck(theDeck);
      
      myDeck.shuffle(myDeck);      
      
      myDeck.deal(player1, player2, myDeck);
      
      myDeck.printIt(printer, player1, player2);
      
      myDeck.playWar(player1, player2, printer);
      
      myDeck.endWar(printer, player1, player2);
      
      printer.close();
   }//End main method
 
}//End class template