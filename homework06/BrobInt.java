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
   private byte   sign          = 0;
   private byte[] byteVersion   = null;
   private String sBrobInt;
   private String reverse = "";
   private int[] intArray;
   private int BrobSign;
   private int[] BrobInt;
   private int BrobSize;
   private int remainder;
   private int compare;
   private String sReverse;
   private String intString = "";
   private int[] result;
   private int temp = 0;
   private int carry = 0;
   private int start = 0;
   private boolean Equals = false;


   public BrobInt( String value ) {
     intArray = new int[BrobSize];

        if (value.charAt(0) == '+') {
          sign = 0;
          intArray = new int [value.length() - 1];
          for (int i = 1; i <value.length(); i ++) {
            intArray[start] = Character.getNumericValue(value.charAt(i));
            start++;
          }
        } else if (value.charAt(0) == '-') {
          sign = 1;
          intArray = new int [value.length() - 1];
          for (int i = 1; i <value.length(); i ++) {
            intArray[start] = Character.getNumericValue(value.charAt(i));
            start++;
          }
        } else{
          intArray = new int [value.length()];
          for (int i = 0; i <value.length(); i ++) {
            intArray[start] = Character.getNumericValue(value.charAt(i));
            start++;
          }
        }
      }

    public BrobInt( int[] value ) {
        intArray = value;
         }


   public boolean validateDigits() {
     for(byte i : byteVersion) {
        if(i > 9 || i < 0) {
        }
        return false;
      }
      return true;
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

   public BrobInt addInt( BrobInt gint ) {

   int newSize = toString().length();
     int [] integers = new int[newSize];

     for (int i = 0; i < newSize; i++) {

       if (i < BrobSize ) {
         temp += intArray[i];
       }

       if (i <gint.BrobSize) {
         temp +=gint.intArray[i];
       }

       temp += carry;

       integers[i] = temp % 10;
       carry = temp / 10;
    }

     if (carry == 1){
       integers[newSize - 1] = 1;
     } else {
       integers[newSize - 1] = 0;
       }

     return new BrobInt(integers);
   }


   public BrobInt subtractInt( BrobInt gint ) {
     int [] integers = new int[BrobSize];
   for (int i = 0; i < BrobSize; i++) {
     int temp = intArray[i];
     if (i <gint.BrobSize) {
     temp -=gint.intArray[i];
     }
     temp -= carry;
     carry = 0;
     if (temp < 0) {
       temp = temp + 10;
       carry = 1;
     }
     integers[i] = temp;
   }
   return new BrobInt(Arrays.toString(integers));
 }



 public BrobInt signChanger() {
     BrobSign = BrobInt[byteVersion.length - 1] = BrobInt[byteVersion.length - 1] * -1;
     return this;
   }

   public BrobInt multiply( BrobInt gint ) {
     if (this.equals(ZERO) || gint.equals(ZERO)) {
       return ZERO;
    }
    if (this.equals(ONE)) {
        return gint;
    } if (gint.equals(ONE)) {
        return this;
      }
       if (this.byteVersion.length < gint.byteVersion.length) {
         return gint.multiply(this);
       }
     int[] result = new int[this.BrobSize + gint.BrobSize];
     result[result.length - 1] = this.BrobSign * gint.BrobSign;
     for (int i = 0; i < gint.BrobSize; i++) {
        for (int j = 0; j < this.BrobSize; j++) {
           result[j + i] = result[j+i] + (this.BrobInt[j] * gint.byteVersion[i]);
        }
        for (int k = 0; k < result.length; k++) {
           int temp = result[k];
           if (result[k] > 10) {
              result[k] = result[k] % 10;
              result[k + 1] = result[k + 1] + temp/10;
           }
        }
     }
     return new BrobInt(result);
  }

   public BrobInt divide(BrobInt gint) {
     int newSign = this.BrobSign * gint.BrobSign;
     if (this.BrobSign == -1) {
        this.signChanger();
     }
     if (gint.BrobSign == -1) {
        gint.signChanger();
     }
     if (this.compareTo(gint) == -1) {
        return ZERO;
     } else if (this.equals(gint)) {
        return ONE;
     }
     else {
        int i = 2;
        System.out.println(gint.multiply(new BrobInt(i + "")).compareTo(this));
        while (gint.multiply(new BrobInt(i + "")).compareTo(this) < 0) {
           System.out.println(i);
           i++;
        }
        i--;
        return new BrobInt(i + "");
     }
  }

   public BrobInt remainder( BrobInt gint ) {
        int k = Integer.parseInt(gint.sBrobInt);

        Scanner scan = new Scanner (System.in);
        int i = scan.nextInt();
        while (i < 2000)
        {
            if (i < 2000)
            {
                i = scan.nextInt();
            }
        }
        return new BrobInt(String.valueOf(k + i));
    }


   public boolean signChecker() {
       if (sBrobInt.charAt(0) == '-') {
         return true;
       }
       if (sBrobInt.charAt(0) == '+') {
         return false;
       }
       return false;
     }

   public int compareTo( BrobInt gint ) {
     if (gint.signChecker() == false && signChecker() == false){
      if (intArray.length >gint.intArray.length) {
        return 1;
      } else if (intArray.length <gint.intArray.length) {
        return -1;
      } else if (intArray.length ==gint.intArray.length) {
        for (int i = 0; i < intArray.length; i++) {
          if (intArray[i] >gint.intArray[i]) {
            return 1;
          } else if (intArray[i] ==gint.intArray[i]){
            return 0;
          } else if (intArray[i] <gint.intArray[i]) {
            return -1;
          }
        }
      }
    } else if (gint.signChecker() == true && signChecker() == true) {
      if (intArray.length <gint.intArray.length) {
        return 1;
      } else if (intArray.length >gint.intArray.length) {
        return -1;
      } else if (intArray.length ==gint.intArray.length) {
        for (int i = 1; i < intArray.length + 1; i++) {
          if (intArray[i] <gint.intArray[i]) {
            return 1;
          } else if (intArray[i] >gint.intArray[i]) {
            return -1;
          } else if (intArray[i] ==gint.intArray[i]) {
            return 0;
          }
        }
      }
    }
    return -1;
  }

   public boolean equals( BrobInt gint ) {
     if (sBrobInt.length() == gint.toString().length()){
      for (int i = 0; i < sBrobInt.length(); i++) {
        if (sBrobInt.charAt(i) != gint.toString().charAt(i)) {
          Equals = false;
        } else {
          Equals = true;
          break;
        }
      }
    }
    return Equals;
  }

   public static BrobInt valueOf( long value ) throws NumberFormatException {
     BrobInt gi = null;
     try {
        gi = new BrobInt(new Long(value).toString());
     }
     catch(NumberFormatException nfe) {
       System.out.println( "\n  Sorry, the value must be numeric of type long." );
     }
     return gi;
  }


   public String toString() {
     sBrobInt = "";
    if( sign == 1 ) {
      sBrobInt = "-";
    } else {
      sBrobInt = "";
    }

    for( int i = 0; i < intArray.length; i++) {
      sBrobInt = sBrobInt + Integer.toString( intArray[i] );
    }
    return sBrobInt;
  }

   public void toArray( byte[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

      System.exit( 0 );
   }
}
