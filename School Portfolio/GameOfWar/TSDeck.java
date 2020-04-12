import java.util.Random;
import java.io.*;

class TSDeck {

   TSCard[] deck = new TSCard[52];
   
   String[] origDeck = new String[52];
   
   public TSDeck() {
   
   }
   
   /**
   * Sets the deck
   *
   * @param tempDeck the array of cards to be set
   */
   public void setDeck(TSCard[] tempDeck) {
      for(int i = 0;i < tempDeck.length;i++) {
         origDeck[i] = tempDeck[i].getFaceValue();
      }
      deck = tempDeck;
   }
   
   /**
   * Gets the deck
   *
   * @return returns the array of cards
   */
   public TSCard[] getDeck() {
      return deck;
   }
   
   /**
   * Does the inital creation of the deck
   *
   * @param tempDeck the array of cards to be created
   */
   public static void buildDeck(TSCard[] tempDeck) {
      String face;
      int power;
      int index;
      
      for (int i = 0; i < 52; i++) {
         tempDeck[i] = new TSCard();
      }
      
      for (int i=0; i <= 12; i++) {
         index = i;
         face = tempDeck[i].findValue(index+1, "Spades");
         tempDeck[i].setFaceValue(face);
         power = tempDeck[i].findPower(i+1);
         tempDeck[i].setPower(power);
      }
      
      for (int i=13; i <= 25; i++) {
         index = i - 13;
         face = tempDeck[i].findValue(index+1, "Hearts");
         tempDeck[i].setFaceValue(face);
         power = tempDeck[i].findPower(i+1);
         tempDeck[i].setPower(power);
      }
      
      for (int i=26; i <= 38; i++) {
         index = i - 26;
         face = tempDeck[i].findValue(index+1, "Clubs");
         tempDeck[i].setFaceValue(face);
         power = tempDeck[i].findPower(i+1);
         tempDeck[i].setPower(power);
      }
      
      for (int i=39; i <= 51; i++) {
         index = i - 39;
         face = tempDeck[i].findValue(index+1, "Diamonds");
         tempDeck[i].setFaceValue(face);
         power = tempDeck[i].findPower(i+1);
         tempDeck[i].setPower(power);
      }
   }
   
   /**
   * Shuffles the deck
   *
   * @param deck the array of cards to be shuffled
   */
   public static void shuffle(TSDeck deck) {
      int index;
      TSCard tempCard;
      TSCard[] tempDeck;
      tempDeck = deck.getDeck();
      Random rand = new Random();
       
      for (int i = 0; i < tempDeck.length; i++) {
          index = rand.nextInt(tempDeck.length);
          tempCard = tempDeck[index];
          tempDeck[index] = tempDeck[i];
          tempDeck[i] = tempCard;
      }
   }
   
   /**
   * Deals the deck to each player
   *
   * @param tempDeck the deck to be dealt
   * @param hand1 player 1's hand
   * @param hand2 player 2's hand
   */
   public static void deal(TSPlayerHand hand1, TSPlayerHand hand2, TSDeck tempDeck) {
      int numb;
      int index1 = 0;
      int index2 = 0;
      TSCard[] deck;
      deck = tempDeck.getDeck();
      TSCard[] player1 = new TSCard[26];
      TSCard[] player2 = new TSCard[26];
      
      for (int i = 0; i < deck.length; i++) {
         numb = i%2;
         if (numb == 0) {
            player2[index2] = deck[i];
            index2++;
         }
         else {
            player1[index1] = deck[i];
            index1++;
         }
      }
      hand1.setHand(player1);
      hand2.setHand(player2);
   }
   
   /**
   * Plays the actual game. Prints to the output file.
   *
   * @param printer the PrintWriter object for the file output
   * @param hand1 player 1's hand
   * @param hand2 player 2's hand
   */
   public static void playWar(TSPlayerHand hand1, TSPlayerHand hand2, PrintWriter printer) {
      TSCard[] player1;
      TSCard[] player2;
      player1 = hand1.getHand();
      player2 = hand2.getHand();
      
      int j = 0;
      boolean control = true;
      
      printer.printf("%-30s%-30s%-30s%-30s%-30s","Player 1 plays:","Player 2 plays:","Winner:","Player 1 Count:","Player 2 Count:");
      printer.print("\n\n");
      
      for(int i = 0; i < player1.length; i++) {
         
         printer.printf("%-30s%-30s",player1[i].getFaceValue(),player2[i].getFaceValue());
         
         if(player1[i].getPower() < player2[i].getPower()) {
            hand2.addPoint(2);
            printer.printf("%-30s%-30s%-30s","Player 2",hand1.getPoints(),hand2.getPoints());
         }
         else if(player1[i].getPower() > player2[i].getPower()) {
            hand1.addPoint(2);
            printer.printf("%-30s%-30s%-30s","Player 1",hand1.getPoints(),hand2.getPoints());
         }
         else {
            printer.printf("%-30s","WAR!!!");
            printer.print("\n");
            while(control) {
               j += 1;
               
               if((j+i) < player1.length) {
                  if(player1[i+j].getPower() < player2[i+j].getPower()) {
                     hand2.addPoint(j*2+2);
                     printer.printf("%-30s%-30s%-30s%-30s%-30s", player1[i+j].getFaceValue(), player2[i+j].getFaceValue(), "Player 2",hand1.getPoints(),hand2.getPoints());
                     i++;
                     control = false;
                  }
                  else if(player1[i+j].getPower() > player2[i+j].getPower()) {
                     hand1.addPoint(j*2+2);
                     printer.printf("%-30s%-30s%-30s%-30s%-30s", player1[i+j].getFaceValue(), player2[i+j].getFaceValue(), "Player 1",hand1.getPoints(),hand2.getPoints());
                     i++;
                     control = false;
                  }
               }
               else {
                  control = false;
               }
            }
         }
         printer.print("\n");
      }
      printer.print("\n\n");
   }
   
   /**
   * Prints the final game results to output file.
   *
   * @param printer the PrintWriter object for file output
   * @param hand1 player 1's hand
   * @param hand2 player 2's hand
   */
   public static void endWar(PrintWriter printer, TSPlayerHand hand1, TSPlayerHand hand2) {
      printer.printf("%-30s%-30s%-30s","Game Winner:","Player 1 Count:","Player 2 Count:");
      printer.print("\n");
      if(hand1.getPoints() > hand2.getPoints()) {
         printer.printf("%-30s%-30s%-30s","Player 1", hand1.getPoints(), hand2.getPoints());
      }
      else if(hand1.getPoints() < hand2.getPoints()) {
         printer.printf("%-30s%-30s%-30s","Player 2", hand1.getPoints(), hand2.getPoints());
      }
      else {
         printer.printf("%-30s%-30s%-30s","TIE!!!", hand1.getPoints(), hand2.getPoints());
      }
   }
   
   /**
   * Prints the orignal deck, shuffled deck, and player hands
   *
   * @param printer the PrintWriter object for file output
   * @param hand1 player 1's hand
   * @param hand2 player 2's hand
   */
   public void printIt(PrintWriter printer, TSPlayerHand hand1, TSPlayerHand hand2) {
      TSCard[] player1;
      TSCard[] player2;
      player1 = hand1.getHand();
      player2 = hand2.getHand();
      
      printer.printf("%-30s%-30s%-30s%-30s","Original Deck","Shuffled Deck","Player 1 Hand","Player 2 Hand");
      printer.print("\n\n");
      
      for(int i = 0; i < deck.length; i++) {
         printer.printf("%-30s%-30s", origDeck[i], deck[i].getFaceValue());
         if(i < player1.length) {
            printer.printf("%-30s%-30s", player1[i].getFaceValue(), player2[i].getFaceValue());
         }
         printer.print("\n");
      }
      printer.print("\n\n");
   }
}