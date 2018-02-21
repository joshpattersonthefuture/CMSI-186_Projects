/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Demonstrates the use of input from a command line for use with Yahtzee
 *  Author        :  Joshua Patterson
 *  Date          :  2017-02-14
 *  Description   :  
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-14  B.J. Johnson   Initial writing and release
 *  @version 1.1.0  2017-02-21  Josh Patterson Methods Filled in
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{

   public static void main( String args[] ) {
      System.out.println( "\n   Welcome to the Highroll\n" );
      System.out.println("Press 1 to roll all the dice");
      System.out.println("Press 2 to roll a single die");
      System.out.println("Press 3 to calculate the score for the set");
      System.out.println("Press 4 to save the current score as high score");
      System.out.println("Press 5 to display the high score");
      System.out.println("Press the 'q' to quit the program." );

     // This line uses the two classes to assemble an "input stream" for the user to type
     // text into the program
      DiceSet ds = new DiceSet(4,8);
      int HighScore = 0; 

      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      while( true ) {
         System.out.print( ">>" );
         String inputLine = null;
         try {

            inputLine = input.readLine();
            if ( '1' == inputLine.charAt(0)){
              ds.roll();
              System.out.println("Roll value:" + ds.toString());
            }
            if ( '2' == inputLine.charAt(0)){
              System.out.println("Roll of a single die:" + ds.rollIndividual(0));
            }
            if ( '3' == inputLine.charAt(0)){
              System.out.println("Score of the set: " + ds.sum());
            }
            if ( '4' == inputLine.charAt(0)){
              HighScore = ds.sum();
            }
            if ( '5' == inputLine.charAt(0)){
              System.out.println("Current High score: " + HighScore);
            }
            if( 0 == inputLine.length() ) {
               System.out.println("enter some text!:");
            }
            System.out.println( "   You typed: " + inputLine );
            if( 'q' == inputLine.charAt(0) ) {
               break;
            }       
        }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
      }
   }
}
