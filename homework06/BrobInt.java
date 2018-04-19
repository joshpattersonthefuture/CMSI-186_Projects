 import java.io.IOException;
 import java.util.Scanner;
 import java.util.Random;
 import java.util.Arrays;

public class BrobInt{

   public static final BrobInt ZERO     = new BrobInt(  "0" );
   public static final BrobInt ONE      = new BrobInt(  "1" );
   public static final BrobInt TWO      = new BrobInt(  "2" );
   public static final BrobInt THREE    = new BrobInt(  "3" );
   public static final BrobInt FOUR     = new BrobInt(  "4" );
   public static final BrobInt FIVE     = new BrobInt(  "5" );
   public static final BrobInt SIX      = new BrobInt(  "6" );
   public static final BrobInt SEVEN    = new BrobInt(  "7" );
   public static final BrobInt EIGHT    = new BrobInt(  "8" );
   public static final BrobInt NINE     = new BrobInt(  "9" );
   public static final BrobInt TEN      = new BrobInt( "10" );

   private String internalValue = "";
   private byte[] byteVersion   = null;
   private String sBrobInt;
   private String reverse = "";
   private int[] intArray;
   private String sReverse;
   private String intString = "";
   private int[] Brob;
   private int BrobSize;
   private int BrobSign;
   private int[] result;
   private int temp = 0;
   private int carry = 0;
   private int start = 0;
   private boolean Equals = false;
   private int index = 0;

public BrobInt( int[] gint ) {
 Brob = gint;
}

public BrobInt( String gint ) {
     if (gint.equals("1")) {
       Brob = new int[3];
       BrobSize= 1;
       Brob[0] = 1;
       BrobSign = Brob[Brob.length-1] = 1;
        return;
     }
    if (gint.equals("0")) {
       Brob = new int[1];
       BrobSize = 1;
       Brob[0] = 0;
       BrobSign = 0;
        return;
  }
       Brob = new int[gint.length()+2];
       BrobSize = Brob.length-2;
       BrobSign = Brob[Brob.length-1] = 1;
       Brob[Brob.length-2] = 0;
         for (int i = 0; i <= gint.length()-1; i++) {
            if(gint.charAt(gint.length()-1-i) == '-') {
               BrobSign = Brob[Brob.length-1] = -1;
               break;
        }
       Brob[i] = Integer.parseInt(gint.charAt(gint.length()-1-i)+"");
         }
}

public BrobInt reverser() {
     StringBuffer sb = new StringBuffer(toString());
     sReverse = sb.reverse().toString();
     return new BrobInt(sReverse);
  }


   public static BrobInt reverser( BrobInt gint ) {
     StringBuffer sb = new StringBuffer(gint.toString());
     String sReverse = sb.reverse().toString();
     return new BrobInt(sReverse);
 }

///signs still confuse program and add/subtract as if theres only one negative always
public BrobInt signCheck() {
  BrobSign = Brob[Brob.length-1]
  =Brob[Brob.length-1]* -1;
  return this;
}

public int compareTo( BrobInt gint ) {
      if (this.BrobSign > gint.BrobSign) {
         return 1;
      } else if (this.BrobSign < gint.BrobSign) {
         return (-1);
      } else {
         if (this.BrobSize > gint.BrobSize) {
            return 1 * this.BrobSign;
         } else if (this.BrobSize < gint.BrobSize) {
            return -1 * this.BrobSign;
         } else {
            for (int i = Brob.length-2; i >= 0; i--) {
               if (this.Brob[i] > gint.Brob[i]) {
                  return 1 * this.BrobSign;
               } else if (this.Brob[i] < gint.Brob[i]) {
                  return (-1) * this.BrobSign;
               }
            }
            return 0;
         }
      }
   }

///first few digits correct, next 3 seem to subtract by 20 or 30 recursively

public BrobInt subtractInt( BrobInt gint ) {
  if (this.compareTo(gint) == 0) {
      return ZERO;
   }
   int[] result;
   if (this.compareTo(gint) == 1 && this.BrobSign + gint.BrobSign == 2) {
      result = this.Brob;
      for (int i = 0; i < gint.BrobSize; i++) {
         if (result[i] < gint.Brob[i]) {
            result[i] = result[i] + 10;
            result[i+1] = result[i+1] - 1;
         }
         result[i] = result[i] - gint.Brob[i];
      }
      return new BrobInt(result);
   } else if (this.compareTo(gint) == -1 && this.BrobSign + gint.BrobSign == 2) {
      return gint.subtractInt(this).signCheck();
   } else if (this.compareTo(gint) == 1 && this.BrobSign + gint.BrobSign == -2) {
      return gint.signCheck().subtractInt(this.signCheck());
   } else if (this.compareTo(gint) == -1 && this.BrobSign + gint.BrobSign == -2) {
      return (this.signCheck().subtractInt(gint.signCheck())).signCheck();
   } else if (this.BrobSign > gint.BrobSign) {
      return this.addInt(gint.signCheck());
   } else if (this.BrobSign < gint.BrobSign) {
      return gint.addInt(this.signCheck());
   }
   return null;
}

///first three digits correct, next few seem to add by 30 or 20 recursively
public BrobInt addInt( BrobInt gint ) {
      int[] result;
      if (gint.equals(ZERO)) {
         return this;
      }
      if (this.BrobSize >= gint.BrobSize && this.BrobSign + gint.BrobSign != 0) {
         result = this.Brob;
         result[this.Brob.length-1] = this.BrobSign;
         for (int i = 0; i < gint.BrobSize; i++) {
            result[i] = result[i] + gint.Brob[i];
            if (result[i] > 9) {
               result[i] = result[i] - 10;
               result[i+1] = result[i+1] + 1;
            }
         }
         return new BrobInt(result);
      } else if (this.compareTo(gint) == -1 && this.BrobSign + gint.BrobSign == -2) {
         return (gint.signCheck().addInt(this.signCheck())).signCheck();
      } else if (this.compareTo(gint) == 1 && this.BrobSign + gint.BrobSign == -2) {
         return (this.signCheck().addInt(gint.signCheck())).signCheck();
      }   else if (this.compareTo(gint) == -1 && this.BrobSign + gint.BrobSign == 2) {
           return gint.addInt(this);
      } else if (this.BrobSign < gint.BrobSign) {
         return gint.subtractInt(this.signCheck());
      } else if (this.BrobSign > gint.BrobSign) {
          return this.subtractInt(gint.signCheck());
      }
              return null;
}


//first digit of each answer seems to be missing when length > 4
//tried to debug but continued to persist
public BrobInt multiply( BrobInt gint ) {
          if (this.equals(ZERO)) {
             return ZERO;
          }
          if (gint.equals(ZERO)) {
              return ZERO;
          }
         if (this.equals(ONE)) {
            return gint;
         } if (gint.equals(ONE)) {
            return this;
         }
         if (gint.BrobSize > this.BrobSize) {
            return gint.multiply(this);
         }
         int[] result = new int[gint.BrobSize + this.BrobSize];
         result[result.length-1] = gint.BrobSign * this.BrobSign;
         for (int i = 0; i < gint.BrobSize; i++) {
            for (int k = 0; k < this.BrobSize; k++) {
               result[k+i] = result[k+i] + (this.Brob[k] * gint.Brob[i]);
            }
            for (int j = 0; j < result.length; j++) {
               int temp = result[j];
               if (result[j] > 10) {
                  result[j] = result[j] % 10;
                  result[j+1] = result[j+1] + temp/10;
               }
            }
         }
         return new BrobInt(result);
}


      /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
     *  @param  gint         BrobInt to divide this by
     *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public BrobInt divide( BrobInt gint ) {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
}

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to get the remainder of division of this BrobInt by the one passed as argument
     *  @param  gint         BrobInt to divide this one by
     *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public BrobInt remainder( BrobInt gint ) {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
}

public boolean equals( Object x ) {
        if (x instanceof BrobInt) {
           BrobInt y = (BrobInt) x;
           if (y.BrobSize  != this.BrobSize) {
              return false;
           }
           return this.compareTo(y) == 0;
        }
        return false;
}


public static BrobInt valueOf( long gint ) throws NumberFormatException {
     BrobInt gi = null;
     try {
        gi = new BrobInt(new Long(gint).toString());
     }
     catch(NumberFormatException nfe) {
       System.out.println( "\n  Sorry, the gint must be numeric of type long." );
     }
     return gi;
   }

public String toString() {
     if (this.equals(ZERO)) {
        return "0";
     }
     if (this.equals(ONE)) {
        return "1";
     }
     String blob = "";
     for (int i = 0; i <= Brob.length-2; i++) {
        blob = Brob[i] + blob;
     }
     while (blob.charAt(0) == '0' && blob.length()-1 > 0) {
        blob = blob.substring(1);
     }
     if (Brob[Brob.length-1] == -1) {
        blob = "-" + blob;
     }
     return blob;
   }

public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

      System.exit( 0 );
   }
}
