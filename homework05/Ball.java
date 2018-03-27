import java.lang.Math;
import java.util.Arrays;

public class Ball{
  double x1;
  double y1;
  double x2;
  double y2;
  double xSpeed;
  double ySpeed;
  double radius;
  double weight;
  int hours;
  int minutes;
  double seconds;
  double fieldLength;
  double[] time;
  double maxCurrentSpeed;


  public Ball(double x1, double y1, double xSpeed, double ySpeed){
    double radius = 4.45;
    double weight = 1;
    this.radius = radius;
    this.x1= x1;
    this.y1 = y1;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  public boolean CollisionFlag(double[] xList, double[] yList){
    Ball balls = null;
    int i = 0;
    while (i < (xList.length)){
      double d = Math.sqrt( Math.pow(xList[i], 2) + Math.pow(yList[i], 2) );
      if(d < 4.45){
        System.out.println("Collision between ball number " + (i+1) + " and the flagpole");
        return true;
      }
      else{
        i = i +1;
      }
    }
    return false;
  }

  public boolean VerifyCollision(double[] xList, double[] yList){
    Ball balls = null;
    int j = 0;
    int k = 1;
    while (j< (xList.length-1)){
      double d = Math.sqrt( (Math.pow(Math.abs(xList[k]-xList[j]),2)) + (Math.pow(Math.abs(yList[k]-yList[j]),2)) );
      if(d<8.9){
        System.out.println("Collision between ball number " + (j+1) + " and ball number " + (k+1));
        return true;
      }

      else{
        k = j + 1;
      }
      if(k == xList.length){
        k = j + 2;
      }
      j= j+1;
    }
    return false;
  }

  public double[] updatexList(double[] xList, double[] yList, double[] xListVel, double timeSplit){
    int h = 0;
    double[] result = new double[xList.length];
    while(h < timeSplit){
      int m = 0;
      while(m < xList.length){
        result[m]= xList[m] + xListVel[m];
        xListVel[m] = 0.99 * xListVel[m];
        m = m + 1;
      }
      h = h + 1;
    }
    return result;
  }

  public double[] updateyList(double[] yList, double[] xList, double[] yListVel, double timeSplit){
    int n = 0;
    double[] result = new double[yList.length];
    while(n < timeSplit){
      int o = 0;
      while(o < xList.length){
        result[o]= yList[o] + yListVel[o];
        yListVel[o] = 0.99 * yListVel[o];
        o = o + 1;
      }
      n = n + 1;
    }
    return result;
  }

  public double getMaxSpeed(double[] xListVel, double[] yListVel){
    int p = 0;
    maxCurrentSpeed = 0;
    while(p < xListVel.length){
      if(xListVel[p] < -maxCurrentSpeed){
        maxCurrentSpeed = -xListVel[p];
      }
      if(yListVel[p] < -maxCurrentSpeed){
        maxCurrentSpeed = -yListVel[p];
      }
      if(maxCurrentSpeed < xListVel[p]){
        maxCurrentSpeed = xListVel[p];
      }
      if(maxCurrentSpeed < yListVel[p]){
        maxCurrentSpeed = yListVel[p];
      }
      p = p+1;
    }
    return maxCurrentSpeed;
  }

  public static void main( String[] args ){
    Ball testball1 = new Ball(1, 2, 3, 4);
    Ball testball2 = new Ball(5, 6, 7, 8);
    System.out.println("x value of a: " + testball1.x1);
    System.out.println("y value of a: " + testball1.y1);
    System.out.println("xSpeed value of a: " + testball1.xSpeed);
    System.out.println("ySpeed value of a: " + testball1.ySpeed);
    System.out.println(" ");
    System.out.println("x value of b: " + testball2.x1);
    System.out.println("y value of b: " + testball2.y1);
    System.out.println("xSpeed value of b: " + testball2.xSpeed);
    System.out.println("ySpeed value of b: " + testball2.ySpeed);
    System.out.println(" ");
    Ball testball3 = new Ball(-1, -2.5, 0, -7);
    System.out.println("x value of c: " + testball3.x1);
    System.out.println("y value of c: " + testball3.y1);
    System.out.println("xSpeed value of c: " + testball3.xSpeed);
    System.out.println("ySpeed value of c: " + testball3.ySpeed);
    System.out.println("Radius value of c: " + testball3.radius);
    System.out.println(" ");
    Ball testball4 = new Ball(123.45, 234.5,  3.45, 4.5);
    System.out.println("x value of d: " + testball4.x1);
    System.out.println("y value of d: " + testball4.y1);
    System.out.println("xSpeed value of d: " + testball4.xSpeed);
    System.out.println("ySpeed value of d: " + testball4.ySpeed);
    System.out.println("Radius value of d: " + testball4.radius);
    System.out.println(" ");
    Ball testball5 = new Ball(77, -77,  77, -77);
    System.out.println("x value of e: " + testball5.x1);
    System.out.println("y value of e: " + testball5.y1);
    System.out.println("xSpeed value of e: " + testball5.xSpeed);
    System.out.println("ySpeed value of e: " + testball5.ySpeed);
    System.out.println("Radius value of e: " + testball5.radius);
    System.out.println(" ");
    System.out.println("Collision with a flag at position 0,0: " + testball1.CollisionFlag(new double[] { 123.0, 80.0, -1.0, 103.0}, new double[] {200.0, 60.0, -2.0, 73.0}));
    System.out.println("Collision with a flag at position 0,0: " + testball1.CollisionFlag(new double[] { -60.0, 81.0, 124.0, 197.0}, new double[] {36.0, -103.0, 116.0, 91.0}));
    System.out.println("Collision with a flag at position 0,0: " + testball1.CollisionFlag(new double[] { -161.0, -125.0, 0.0}, new double[] {-41.0, -169.0, 2.0}));
    System.out.println(" ");
    System.out.println("Verify Collision between more than 2 balls: " + testball1.VerifyCollision(new double[] { 123.0, 80.0, -1.0, 1.0}, new double[] {200.0, 60.0, -2.0, 0.0}));
    System.out.println("Verify Collision between more than 2 balls: " + testball1.VerifyCollision(new double[] { -200.0, -200.0, 200.0, 200.0}, new double[] {-200.0, 200.0, 200.0, -200.0}));
    System.out.println("Verify Collision between more than 2 balls: " + testball1.VerifyCollision(new double[] { 134, -58.0, -91.0, -79.0, 103}, new double[] {85.0, -82.0, 143.0, 167.0, -28}));
    System.out.println("Verify Collision between more than 2 balls: " + testball1.VerifyCollision(new double[] { 0.0, -200.0, 3.0, 200.0}, new double[] {0.0, 200.0, 1.0, -200.0}));
    System.out.println("Verify Collision between more than 2 balls: " + testball1.VerifyCollision(new double[] { 0.0, -200.0, 100.0, 102.0}, new double[] {0.0, 200.0, 100.0, 99.0}));
    System.out.println(" ");
    System.out.println("Update xList & yList test (timeSplit = 3):");
    double[] xList = {testball1.x1, testball2.x1, testball3.x1, testball4.x1, testball5.x1};
    double[] xListVel = {testball1.xSpeed, testball2.xSpeed, testball3.xSpeed, testball4.xSpeed, testball5.xSpeed};
    double[] yList = {testball1.y1, testball2.y1, testball3.y1, testball4.y1, testball5.y1};
    double[] yListVel = {testball1.ySpeed, testball2.ySpeed, testball3.ySpeed, testball4.ySpeed, testball5.ySpeed};
    System.out.println("Initial xList: " + Arrays.toString(xList));
    System.out.println("Updated xList: " + Arrays.toString(testball1.updatexList(xList, yList, xListVel, 3)));
    System.out.println("Initial yList: " + Arrays.toString(yList));
    System.out.println("Updated yList: " + Arrays.toString(testball1.updatexList(yList, xList, yListVel, 3)));
    System.out.println(" ");
    System.out.println("Max speed: " + testball1.getMaxSpeed(new double[] { 123.0, 80.0, -1.0, 1.0}, new double[] {200.0, 60.0, -2.0, 0.0}));
    System.out.println("Max speed: " + testball1.getMaxSpeed(new double[] { 0.0, -200.0, 100.0, 102.0}, new double[] {0.0, 100.0, 100.0, 99.0}));
    System.out.println("Max speed: " + testball1.getMaxSpeed(new double[] { 134, -58.0, -91.0, -79.0, 103}, new double[] {85.0, -82.0, 143.0, 167.0, -28}));
    System.out.println("Max speed: " + testball1.getMaxSpeed(new double[] { -134, 58.0, 11.0}, new double[] {85.0, -82.0, 14.0}));
    System.out.println("Max speed: " + testball1.getMaxSpeed(new double[] { 123.0, 80.0, -1.0}, new double[] {0.0, 100.0, 100.0}));
  }
}
