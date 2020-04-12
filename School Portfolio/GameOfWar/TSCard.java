class TSCard {
   
   private String faceValue; //The face of the crad
   private int power; //The power of the card
   
   public TSCard() {
      faceValue = "Unassigned";
      power = 0;
   }
   
   /**
   * Sets the faceValue
   *
   * @param name the name to be set
   */
   public void setFaceValue(String name) {
      faceValue = name;
   }
   
   /**
   * Gets the face value
   *
   * @return the face value
   */
   public String getFaceValue() {
      return faceValue;
   }
   
   /**
   * Sets the power
   *
   * @param value the power of the card
   */
   public void setPower(int value) {
      power = value;
   }
   
   /**
   * Gets the power
   *
   * @return the power of the card
   */
   public int getPower() {
      return power;
   }
   
   /**
   *Finds the face value of the card
   *
   * @return returns the face value
   */
   public String findValue(int numb2, String suit) {

      String ans;

      if (numb2 == 13) {
         ans = "King of " + suit;
      }
      else if (numb2 == 12) {
         ans = "Queen of " + suit;
      }
      else if (numb2 == 11) {
         ans = "Jack of " + suit;
      }
      else if (numb2 == 1) {
         ans = "Ace of " + suit;
      }
      else {
         ans = numb2 + " of " + suit;
      }
      return ans;
   }
   
   /**
   * Finds the power of the card
   *
   * @return returns the power of the card
   */
   public int findPower(int numb) {
      
      int ans;
      
      if (numb >= 1 && numb <=13) {
         ans = numb;
      }
      else if (numb >= 14 && numb <= 26) {
         ans = numb - 13;
      }
      else if (numb >= 27 && numb <= 39) {
         ans = numb - 26;
      }
      else if (numb >= 40 && numb <= 52) {
         ans = numb - 39;
      }
      else {
         ans = -1;
      }
      return ans;
   }
}