class TSPlayerHand {
   
   TSCard[] hand = new TSCard[26]; //The player hand
   int points; //The player score
   
   public TSPlayerHand() {
      points = 0;
   }
   
   /**
   * Sets the hand
   *
   * @param tempHand stores the array of cards
   */
   public void setHand(TSCard[] tempHand) {
      hand = tempHand;
   }
   
   /**
   * Gets the hand
   *
   * @return returns the hand of cards
   */
   public TSCard[] getHand() {
      return hand;
   }
   
   /**
   * Adds to the player score
   *
   * @param amount the amount to be added
   */
   public void addPoint(int amount) {
      points += amount;
   }
   
   /**
   * Gets the current player score
   *
   * @return the player score
   */
   public int getPoints() {
      return points;
   }
}