/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Josh Patterson
 *  Date          :  2017-02-16
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 *  @version 1.2.0  2017-02-21  Josh Patterson  Methods Filled in
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] dieCount = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
      if (count < 1) {
        throw new IllegalArgumentException();
      }

      this.count = count;
      this.sides = sides;

      dieCount = new Die[ count ];
      for ( int i = 0; i < dieCount.length; i++ ) {
        dieCount[i] = new Die( sides );
      }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
      int sum = 0;
      for ( int i = 0; i < dieCount.length; i++ ){
        sum = sum + dieCount[i].getValue();
      }
      return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
     for ( int i = 0; i < dieCount.length; i++ ) {
       dieCount[i].roll();
     }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @throws IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      if (dieIndex > dieCount.length) {
        throw new IllegalArgumentException( "Out of Range" );
      }
      return dieCount[dieIndex].roll();
    }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @throws IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      if (dieIndex > dieCount.length) {
        throw new IllegalArgumentException( "Out of Range" );
      }
      return dieCount[dieIndex].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      String dieString = "";
      for ( int i = 0; i < dieCount.length; i++ ) {
        dieString = "[" + dieCount[i].getValue() + "]";
      }
      return dieString;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
     String dieString = "";
     for ( int i = 0; i < ds.dieCount.length; i++ ) {
       dieString = "[" + ds.dieCount[i].getValue() + "]";
     }
     return dieString;
   }

  /**
   * @return  true iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds ) {
      if( ds.dieCount.length == dieCount.length ) {
        return true;
      }
      return false;
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      DiceSet d1 = new DiceSet(4,8);
      DiceSet d2 = new DiceSet(6,2);
      d1.roll();
      d2.roll();
      System.out.println("Test sum of dice 1's set " + d1.sum()); 

      d1.rollIndividual(0);
      System.out.println("Test of rolling dice 1 individually" + d1);

      System.out.println("Test if dice 1 and dice 2 are identical" + d1.isIdentical(d2));

      System.out.println("Test string result of dice 2" + d2.toString());
      }
   }
