import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class DynamicChangeMaker {

    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }
        try {
            String[] stringDenom = args[0].split(",");
            int[] Denom = new int[stringDenom.length];

            for (int i = 0; i < Denom.length; i++) {
                Denom[i] = Integer.parseInt(stringDenom[i]);
                if (Denom[i] <= 0) {
                    System.out.println("BAD DATA: Inputs must be greater than 0\n");
                    return;
                }

                for (int j = 0; j < i; j++) {
                    if (Denom[j] == Denom[i]) {
                        System.out.println("BAD DATA: Inputs cannot repeat\n");
                        return;
                    }
                }
            }

            int coinAmount = Integer.parseInt(args[1]);
            if (coinAmount < 0) {
                System.out.println("BAD DATA: Inputs cannot be negative\n");
                return;
            }
            Tuple change = makeChangeWithDynamicProgramming(Denom, coinAmount);
            if (change.isImpossible()) {
                System.out.println("BAD DATA: Impossible Tuple:" + coinAmount);
            } else {
                int coinTotal = change.total();
                System.out.println("BAD DATA: " + coinAmount + " cents can be made with " + coinTotal + "coins" +
                        getSimplePluralSuffix(coinTotal) + " as follows:");

                for (int i = 0; i < Denom.length; i++) {
                    int coinCount = change.getElement(i);
                    System.out.println("- "  + coinCount + " " + Denom[i] + "-cent coin" +
                            getSimplePluralSuffix(coinCount));
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("BAD DATA: All inputs must be integers\n");
        }
    }

    public static Tuple add(Tuple one, Tuple two) {
          if(one.isImpossible() || two.isImpossible()) {
            return Tuple.IMPOSSIBLE;
          }
          return one.add(two);
    }

    public static Tuple equals(Tuple one, Tuple two) {
              if (one.isImpossible()) {
                return two;
              }
              if (two.isImpossible()) {
                return one;
              } else {
                return (one.total() < two.total()) ? one : two;
              }
    }

    public static Tuple makeChangeWithDynamicProgramming(int[] Denoms, int coinAmount) { //need BAD DATA
      int columnCount= coinAmount + 1;                                                  //CONTAINS: ZERO,REPEATS,NEGATIVE
      int[] rowCount = new int[Denoms.length];
      Tuple[][] Tup = new Tuple[Denoms.length][columnCount];
      Tuple Denom = new Tuple(Denoms);
      Tuple x = new Tuple(Denoms.length);
      int temp = 0;

      for(int i=0; i < Denoms.length; i++){
        temp = 0;
        for(int j=0; j < (coinAmount+1); j++){
          if(j==0){
            Tup[i][j]=new Tuple(Denoms.length);
          }
            else{
            if(j >= Denoms[i]){
              Tup[i][j] = new Tuple(Denoms.length);
              Tup[i][j].setElement(i,1);
              temp = j - Denoms[i];
              if( (Tup[i][temp].equals(Tuple.IMPOSSIBLE))){
                Tup[i][j] = Tuple.IMPOSSIBLE;
              }
              else{
                Tup[i][j] = Tup[i][j].add(Tup[i][temp]);
              }
            }
            else{
              Tup[i][j] = Tuple.IMPOSSIBLE;
            }
          if(i != 0){
            if(!Tup[i][j].equals(Tuple.IMPOSSIBLE)){
              if( !Tup[i-1][j].equals(Tuple.IMPOSSIBLE) && (Tup[i-1][j].total() < Tup[i][j].total())){
                  Tup[i][j] = Tup[i-1][j];
                }
            }
            else{
                if(!Tup[i-1][j].equals(Tuple.IMPOSSIBLE)){
                  Tup[i][j] = Tup[i-1][j];
                }
              }
            }
            if (i != 0){
            if(Tup[i-1][j] != Tuple.IMPOSSIBLE && Tup[i][j] == Tuple.IMPOSSIBLE){
              Tup[i][j] = Tup[i-1][j];
            } else if (Tup[i][j] != Tuple.IMPOSSIBLE && Tup[i-1][j] != Tuple.IMPOSSIBLE) {
              if (Tup[i][j].total() > Tup[i-1][j].total()){
                Tup[i][j] = Tup[i-1][j];
              }
             }
            }
           }
        }
      }
          return Tup[Denoms.length - 1][coinAmount];
    }

    public static boolean extraTests(int[] Denoms, int coinAmount) {
        if (coinAmount < 0) {
            return true;
        }
        for (int i = 0; i < Denoms.length; i++) {
            if (Denoms[i] < 1) {
                return true;
            }
            for (int j = i+1; j < Denoms.length; j++) {
                if (Denoms[i] == Denoms[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    private static String getSimplePluralSuffix(int sAdder) {
        return sAdder == 1 ? "" : "s";
    }
}
