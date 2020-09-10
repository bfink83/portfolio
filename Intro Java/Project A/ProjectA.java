import java.util.Random;
import java.util.Scanner;
import java.awt.Color;


/** Starter file for Project A, EN.500.132 Bootcamp: Java, Intersession 2020.
* This program simulates Brownian Motion
* @author Joanne Selinski
* @author Sara More
* @author Bailey Finkelberg
* JHED: bfinkel3
* Date: 1/12/20
*/
public class ProjectA {

   /** Main method.
   * @param args not used
   */
   public static void main(String[] args) {
   
      Scanner kb = new Scanner(System.in);  // this allows us to collect input
      Random rand = new Random();  // random number generator object

      int red, green, blue;  // will store random color info
      int angle;             // will store random heading
      int length;            // will store random step length
      double total;          // will store current walk's total length

      double min, max;  // min and max lengths over all walks so far

      int numParticles;  // how many particles to simulate
      int totalSteps;    // how many steps each particle should take

      int stepCount;     // number of steps in current walk
      double x0, y0, x1, y1;  //old and new position values

      // fixed-value "constants" needed by the program
      final int colormax = 256;
      final int canvasCoordinateMax = 256;
      final int stepmin = 5;
      final int steprange = 11; // number of values between 5 and 15, inclusive
    
      // Prompt for and collect parameters from the user
      System.out.print("Enter number of particles to simulate: ");
      numParticles = kb.nextInt();
      System.out.print("Enter numer of steps per walk: ");
      totalSteps = kb.nextInt();
    
      // Adjust the scale of the canvas; (0,0) is now in center
      StdDraw.setScale(-canvasCoordinateMax, canvasCoordinateMax);
      
      double radAngle;  //will store angle in radians
      
      //initialize min and max at theoretical max and zero, respectively
      min = totalSteps * 15.0;
      max = 0.0;
      
      //for-loop to iterate for each particle
      for (int j = 0; j < numParticles; ++j) {
      
         //initial position of particle at center (0,0 for each loop
         x0 = 0.0;
         y0 = 0.0;
         x1 = 0.0;
         y1 = 0.0;
         
         //initialize total walk length for each loop
         total = 0.0;
         
         //Set random color for each loop
         red = rand.nextInt(colormax);
         green = rand.nextInt(colormax);
         blue = rand.nextInt(colormax);
         
         //set pen color to random color generated
         Color randColor = new Color(red, green, blue);
         StdDraw.setPenColor(randColor);
      
         //for-loop to iterate for each step per walk
         for (int i = 0; i < totalSteps; ++i) {
         
            //Generate random step length (5-15)
            stepCount = rand.nextInt(steprange) + stepmin;
      
            //Generate random angle (0-359)
            angle = rand.nextInt(360);
            
            //if-elseif branches to determine quadrant from angle
            //within branches: convert angle to rads 
            //and find next pos. from warmup formula
            if ((angle >= 0) && (angle <= 90)) {
               radAngle = Math.toRadians(angle);
               
               x1 = x0 + stepCount * Math.cos(radAngle);
               y1 = y0 + stepCount * Math.sin(radAngle);
            }
            else if ((angle > 90) && (angle <= 180)) {
               radAngle = Math.toRadians(180 - angle);
               
               x1 = x0 - stepCount * Math.cos(radAngle);
               y1 = y0 + stepCount * Math.sin(radAngle);
            }
            else if ((angle > 190) && (angle <= 270)) {
               radAngle = Math.toRadians(270 - angle);
               
               x1 = x0 - stepCount * Math.sin(radAngle);
               y1 = y0 - stepCount * Math.cos(radAngle);
            }
            else if ((angle > 270) && (angle < 360)) {
               radAngle = Math.toRadians(360 - angle);
               
               x1 = x0 + stepCount * Math.cos(radAngle);
               y1 = y0 - stepCount * Math.sin(radAngle);
            }
            
            //Draw line from (x0, y0) to (x1, y1)
            StdDraw.line(x0, y0, x1, y1);
            
            //set (x0,y0) to (x1, y1) for new initial pt in next iteration
            x0 = x1;
            y0 = y1;
            
            //increment total
            total = total + stepCount;
         }
         
         //set new min or max walk length if it applies
         if (total < min) {
            min = total;
         }
         else if (total > max) {
            max = total;
         }
      }
      
      //print min and max step length after all loops
      System.out.println("Minimum walk length: " + min);
      System.out.println("Maximum walk length: " + max);
   }
}
