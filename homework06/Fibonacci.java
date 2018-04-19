public class Fibonacci {

    private static final String usageMessage = "\n  You must enter an integer number....." +
                                              "\n    Please try again!" +
                                              "\n  USAGE: java Fibonacci <required_integer>\n\n";
    private static BrobInt brob1 = new BrobInt("0");
    private static BrobInt brob2 = BrobInt.ZERO;
    private static BrobInt brob3 = BrobInt.ONE;

    public static BrobInt Fibonacci(int value) {
      if (value == 0 || value == 1) {
        return BrobInt.ONE;
      } else {
        for (int i = 1; i < value; i++) {
           brob1 = brob2.addInt(brob3);
           brob2 = brob3;
           brob3 = brob1;
        }
      }
      return brob3;
    }

    public static void main( String[] args) {
      System.out.println(Fibonacci(Integer.parseInt(args[0])));
    }
  }
