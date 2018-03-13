/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Josh Patterson
 *  Date written  :  2017-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None 
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2018-03-13  Josh Patterson  Methods Filled
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 
public class Clock {
  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
  private static final double INVALID_ARGUMENT_VALUE = -1.0;
  private static final double MAXIMUM_DEGREE_VALUE = 360.0;
  private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
  private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;

  int hours;
  int minutes;
  double seconds;
  private static double secondsAngle;
  private static double minutesAngle;
  private static double hoursAngle;
  private static double handAngle;
  private static double timeSlice;

  public Clock(int hours, int minutes, double seconds) throws NumberFormatException {

    if (hours < 0 || hours > 12) {
      throw new NumberFormatException("Enter valid number of hours");
    }
  
    if (minutes < 0 || minutes > 60) {
      throw new NumberFormatException("Enter valid number of minutes");
    } 

    if (seconds < 0 || seconds > 60) {
      throw new NumberFormatException("Enter valid number of seconds");
    }

    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  } 

public double tick(double timeSlice){   
    this.seconds = this.seconds + timeSlice;
        while (this.seconds >= DEFAULT_TIME_SLICE_IN_SECONDS){
            this.seconds = this.seconds - DEFAULT_TIME_SLICE_IN_SECONDS;
            this.minutes = this.minutes + 1;
            while(this.minutes >= 60){
                this.minutes = this.minutes - 60;
                this.hours = this.hours + 1;
            }
        }
        return this.seconds;
    }

public double validateAngleArg( String argValue ) throws NumberFormatException {
        double angleargVal = Double.parseDouble(argValue);
         if ((angleargVal < 360 || angleargVal > 0)) {
      return angleargVal;
       }
         else {
          throw new NumberFormatException("Invalid Angle Format");
         }
      }
    
public double validateTimeSliceArg( String argValue ) {
         double sliceargVal = Double.parseDouble(argValue);
         if (sliceargVal < 1800 || sliceargVal > INVALID_ARGUMENT_VALUE) {
           return sliceargVal;
         }
         return sliceargVal;
      }

 public double getHourHandAngle() {
      hoursAngle = (hours * 30) + (minutes * 0.5) + (seconds * HOUR_HAND_DEGREES_PER_SECOND);
    while (hoursAngle >= MAXIMUM_DEGREE_VALUE){
      hoursAngle = hoursAngle - MAXIMUM_DEGREE_VALUE;
    }
    return hoursAngle;
  }
    
public double getMinuteHandAngle() {
     minutesAngle = (minutes * 6) + (seconds * MINUTE_HAND_DEGREES_PER_SECOND);
    while (minutesAngle >= MAXIMUM_DEGREE_VALUE){
      minutesAngle = minutesAngle - MAXIMUM_DEGREE_VALUE;
    }
    return minutesAngle;
  }
    
public double getHandAngle(double hoursAngle, double minutesAngle) {
    handAngle = Math.abs(hoursAngle - minutesAngle);
    if (handAngle > (MAXIMUM_DEGREE_VALUE / 2) ){
      handAngle = MAXIMUM_DEGREE_VALUE - handAngle;
    }
    return handAngle;
  }

public double getTotalSeconds(double totalSeconds) 
    {
        totalSeconds = (hours * 3600) + (minutes * 60) + seconds;
    return totalSeconds;
    } 

public String toString() {
    return "Current time:" + "hours" + ":" + "minutes" + ":" + "seconds";
   }

public static void main( String[] args ) {
    Clock clock1 = new Clock(60, 30, 0);
    System.out.println(clock1.hours);
    System.out.println(clock1.minutes);
    System.out.println(clock1.seconds);
    System.out.println(clock1.tick(180));
    System.out.println( "Testing Validation of Angle Argument..." + clock1.validateAngleArg("0"));
    System.out.println( "Testing Validation of Time Slice Argument..." + clock1.validateTimeSliceArg("45"));
    System.out.println( "Testing Validation of Time Slice Argument..." + clock1.validateTimeSliceArg("45x"));
    System.out.println(clock1.getHourHandAngle());
    System.out.println(clock1.getMinuteHandAngle());
    System.out.println(clock1.getHandAngle(clock1.getHourHandAngle(), clock1.getMinuteHandAngle()));
    try { System.out.println( (250 == clock1.validateAngleArg( "250" )) ? " - got 250" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    try { System.out.println( (370 == clock1.validateAngleArg( "370" )) ? " - got 370" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

   Clock clock2 = new Clock(120, 60, 0);
    System.out.println(clock2.hours);
    System.out.println(clock2.minutes);
    System.out.println(clock2.seconds);
    System.out.println(clock2.tick(180));
    System.out.println( "Testing Validation of Angle Argument..." + clock2.validateAngleArg("4"));
    System.out.println( "Testing Validation of Time Slice Argument..." + clock2.validateTimeSliceArg("60"));
    System.out.println( "Testing Validation of Time Slice Argument..." + clock2.validateTimeSliceArg("60x"));
    System.out.println(clock2.getHourHandAngle());
    System.out.println(clock2.getMinuteHandAngle());
    System.out.println(clock2.getHandAngle(clock2.getHourHandAngle(), clock2.getMinuteHandAngle()));
    try { System.out.println( (320 == clock2.validateAngleArg( "320" )) ? " - got 320" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    try { System.out.println( (900 == clock2.validateAngleArg( "900" )) ? " - got 900" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
  }
}
