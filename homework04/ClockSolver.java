/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  Josh Patterson
 *  Date written  :  2017-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None 
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-03-13  Josh Patterson  Methods Filled
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 
import java.util.*;
public class ClockSolver {

private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
private static final double EPSILON_VALUE = 0.1;

public ClockSolver() {
      super();
   }

public void handleInitialArguments( String args[] ) {

System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
    if( 0 == args.length ) {
       System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
       System.exit( 1 );
     }

      
Clock clock = new Clock(0,0,0);
     clock.validateAngleArg(args[0]);
      if (args.length == 2) {
        clock.validateTimeSliceArg(args[1]);
      }
   }

public static void main( String args[] ) {
	 double angle = Double.parseDouble(args[0]);
     double time;

      ClockSolver cse = new ClockSolver();
      Clock clock    = new Clock(0,0,0);
      double[] newTime = new double[3];
      cse.handleInitialArguments( args );
      time = Double.parseDouble(args[1]);

  /*Tests using arguments */

      if (args.length == 1) {
        while (clock.tick(60) <= 43200) {
          if ((angle - 2 < clock.getHourHandAngle()) && (angle + 2 > clock.getHourHandAngle())) {
            System.out.println(clock.toString());
          }
        }
      }    
	  }
  }
