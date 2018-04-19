public class Fibonacci {
   private static final String usageMessage = "\n  You must enter an integer number....." +
                                             "\n    Please try again!" +
                                             "\n  USAGE: java Fibonacci <required_integer>\n\n";
  private static int    maxCount    = 0;
  private static int    working     = 15000;
  private static String end1        = "st";
  private static String end2        = "nd";
  private static String end3        = "rd";
  private static String endRest     = "th";
  private static String cardinality = "";

  private static final  int NO_CMD_LINE_ARGS = -1;
  private static final  int BAD_CMD_LINE_ARG = -2;

  public Fibonacci() {
     super();
  }
   
   public static BrobInt Fibonacci(int x) {
      if (x <= 2) {
         return new BrobInt("1");
      } else {
         BrobInt brob0 = new BrobInt("0");
         BrobInt brob1 = new BrobInt("1");
         for (int i = 2; i < x; i++) {
            BrobInt brob2 = brob0.addInt(brob1);
            System.out.rintln(brob2);
            brob0 = brob1;
            brob1 = brob2;
         }
         return brob0.addInt(brob1);
      }
    }
    public static void main(String[] args) {
       System.out.println(Fibonacci(Integer.parseInt(args[0])));
    }
   
}
