public class Fibonacci {
   public static BrobInt Fibonacci(int x) {
      if (x <= 2) {
         return new BrobInt("1");
      } else {
         BrobInt brob0 = new BrobInt("0");
         BrobInt brob1 = new BrobInt("1");
         for (int i = 2; i < x; i++) {
            BrobInt brob2 = brob0.addInt(brob1);
            System.out.println(brob2);
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
