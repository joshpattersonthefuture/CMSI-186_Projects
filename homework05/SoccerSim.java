import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class SoccerSim{
  public static void main( String[] args ){
    Ball ball1 = new Ball(0,0,0,0);
    double fieldLength = 350;
    double maxSpeed = 50;
    int maxBall = 200;

    int i = (int) Math.floor(args.length/4);
    int timeslice = 1;
    if (args.length %4 == 1) {
      timeslice = args.length - 1;
    }

    int j = 0;
    double[] xList = new double[i];
    double[] yList = new double[i];
    double[] xListVel = new double[i];
    double[] yListVel = new double[i];
    Ball ball2 = null;
    while(j < i) {
      if(i>maxBall || i<1 || timeslice<0){
        System.out.println("Either the ball number or the time split is incorrect");
        break;
      }
      double x = Double.parseDouble(args[j*4 + 0]);
      double y = Double.parseDouble(args[j*4 + 1]);
      double xSpeed = Double.parseDouble(args[j*4 + 2]);
      double ySpeed = Double.parseDouble(args[j*4 + 3]);
      xList[j] = x;
      yList[j] = y;
      xListVel[j] = xSpeed;
      yListVel[j] = ySpeed;
      j +=1;
      System.out.println("Ball " + j + " : " + x + "," + y + "   Speed: " + xSpeed + "," + ySpeed);

     ball2 = new Ball(x,y,xSpeed,ySpeed);
    }

    if (ball2.VerifyCollision(xList, yList) == false){
      System.out.println("No initial collision.");
    }
    else{
      System.out.println("Initial collision.");
      return ;
    }
    Clock clock1 = new Clock(0,0,0);
    double maxSpeed2 = 1.1;

    while( true ){
      maxSpeed2 = ball2.getMaxSpeed(xListVel, yListVel);
      System.out.println(" ");
      System.out.println("Current positions:");
      System.out.println("Max Speed: " + maxSpeed2);
      if (ball2.VerifyCollision(xList, yList)){
        System.out.println("Collision time:" + clock1.hours() + ":" + clock1.minutes() + ":" + clock1.seconds() );
        break;
      }
      if (ball2.CollisionFlag(xList, yList)){
        System.out.println("Collision time:" + clock1.hours() + ":" + clock1.minutes() + ":" + clock1.seconds() );
        break;
      }
      else{
        xList = ball2.updatexList(xList, yList, xListVel, timeslice);
        yList = ball2.updateyList(yList, xList, yListVel, timeslice);
        j = 0;
        while(j<i){
          System.out.println("Ball " + (j + 1));
          System.out.println(xList[j] + "," + yList[j]);
          j = j + 1;
        }
        System.out.println("No Collision at " + clock1.hours() + ":" + clock1.minutes() + ":" + clock1.seconds());
      }
      clock1.tick(timeslice);
      if (Math.abs(maxSpeed2)<.08){
        System.out.println("NO COLLISION POSSIBLE.");
        break;
      }
    }
    }
  }
