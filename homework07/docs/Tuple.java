import java.util.Arrays;

/**
 * This class represents a tuple of integers, indexed from zero.
 */
public class Tuple {

  /**
   * Singleton constant representing an impossible tuple.
   */
   public static final Tuple IMPOSSIBLE = new Tuple( new int[0] );

  /**
   * int array that contains the data values for this Tuple object
   */
   private int[] data;

  /**
   * Constructs a tuple of size n with all integers initialized to zero.
   *
   * @param n  the number of elements in this tuple
   *
   * @throws IllegalArgumentException if the count argument is negative
   */
   public Tuple( int n ) throws IllegalArgumentException {
      if( n < 0 ) {
         throw new IllegalArgumentException();
      }

      data = new int[n];
      for( int i = 0; i < n; i++ ) {
         data[i] = 0;
      }
   }

  /**
   * Constructs a tuple of size n from the given data, where n = data.length.
   *
   * @param data  the ints that should populate the tuple
   *
   * @throws IllegalArgumentException if the data argument is empty
   */
   public Tuple( int[] data ) throws IllegalArgumentException {
      if( data == null ) {
         throw new IllegalArgumentException();
      }

      this.data = new int[data.length];
      for( int i = 0; i < data.length; i++ ) {
         this.data[i] = data[i];
      }
   }

  /**
   * Returns whether the tuple is impossible.
   *
   * @return whether the tuple is impossible
   */
   public boolean isImpossible() {
      return this.equals( IMPOSSIBLE );
   }

  /**
   * Sets the int at position i to value j.
   *
   * @param i  the position of the int to set
   * @param j  the value to which the position should be set
   */
   public void setElement( int i, int j ) {
      checkIndex(i);
      data[i] = j;
   }

  /**
   * Returns the int at position i.
   *
   * @param i  the position whose int is requested
   *
   * @return the int at position i
   */
   public int getElement( int i ) {
      checkIndex(i);
      return data[i];
   }

  /**
   * Returns the length (i.e., the number of elements) of this tuple.
   *
   * @return the length of this tuple
   */
   public int length() {
      return data.length;
   }

  /**
   * Returns the total of the elements in this tuple. For example, the
   *   three-element tuple <9,3,2> has a total of 14.
   *
   * @return the total of the elements of this tuple
   */
   public int total() {
      int sum = 0;
      for( int i = 0; i < length(); i++ ) {
         sum = sum + getElement( i );
      }

      return sum;
   }

  /**
   * Adds tuple t to this tuple, returning the "sum" as a new tuple.
   *
   * @param t  the tuple to add to this tuple
   *
   * @return the element-wise sum of this and t
   */
   public Tuple add( Tuple t ) {

      if( length() != t.length()) {
         throw new IllegalArgumentException();
      }

      Tuple sum = new Tuple(length());
      for( int i = 0; i < length(); i++ ) {
         sum.setElement(i, getElement( i ) + t.getElement( i ) );
      }

      return sum;
   }

  /**
   * Returns true iff this tuple is value-identical to t.
   *
   * @param t  the object (presumably a Tuple) to check for equality
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
   @Override
   public boolean equals( Object t ) {
      if( (t == null) || (!(t instanceof Tuple)) || (length() != ((Tuple)t).length())       ) {
         return false;
      }

      for( int i = 0; i < length(); i++ ) {
         if( getElement( i ) != ((Tuple)t).getElement( i ) ) {
            return false;
         }
      }

      return true;
   }

  /**
   * Returns the product of this tuple's elements as its hash code.
   *  Note that an IMPOSSIBLE tuple returns a zero
   *
   * @see java.lang.Object#hashCode()
   */
   @Override
   public int hashCode() {
      int product = 1;
      if( this.isImpossible() ) {
         return 0;
      }
      for( int i = 0; i < length(); i++ ) {
         product = product * getElement( i );
      }
      return (product >= 0) ? product : -product;
   }

  /**
   * Returns a string representation of this tuple.
   *
   * @see java.lang.Object#toString()
   */
   @Override
   public String toString() {
      if( isImpossible()) {
         return "Impossible tuple";
      }

      String result = "<";
      for( int i = 0; i < length(); i++ ) {
         result += (i > 0 ? "," : "") + data[i];
      }
      return result + ">";
   }

  /**
   * Checks the validity of a passed index on this Tuple
   *   NOTE: there is no return; if the index is out of range, assessed as
   *   either negative or bigger than the count of elements in this Tuple,
   *   it just throws an exception.  This means that any calling routine needs
   *   to catch that and handle it gracefully if the program should continue
   *   rather than abort at that point.
   *
   * @param i  integer index being checked
   *
   * @throws IllegalArgumentExcpection when the argument is out of range
   *           for this tuple
   *
   * @see java.lang.Object#toString()
   */
   private void checkIndex( int i ) throws IllegalArgumentException {
      if( i < 0 ) {
         throw new IllegalArgumentException();
      }

      if( i >= length() ) {
         throw new IllegalArgumentException();
      }
   }

}
