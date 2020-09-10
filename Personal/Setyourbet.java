import java.util.Scanner;
public class Setyourbet

{
   public static void main(String [] args)
   {
      Scanner keyboard = new Scanner(System.in);
      //System.out.print("What sport would you like to bet? \n 1) NFL | 2) NBA | 3) NHL | 4) MLB \n Choice: ");
      //int leagueChoice = keyboard.nextInt();
      
      
      
      System.out.print("Original Spread: ");
      double originalSpreadInput = keyboard.nextDouble();
      
      System.out.print("Your Spread: ");
      double newSpreadInput = keyboard.nextDouble();
      
      System.out.print("Original Odds Line: ");
      int originalLineInput = keyboard.nextInt();
      
      double pointDifferentialInput;
      
      if (originalSpreadInput < 0 && newSpreadInput > 0)
         pointDifferentialInput = (-1)*(newSpreadInput - originalSpreadInput);
      else
         pointDifferentialInput = newSpreadInput - originalSpreadInput;
      
      int leagueChoice = 1;
      if (leagueChoice == 1)
      {
         if (pointDifferentialInput < 0)
            NFL.buyPoints(newSpreadInput, originalSpreadInput, pointDifferentialInput, originalLineInput);
         else if (pointDifferentialInput > 0)
            NFL.sellPoints(newSpreadInput, originalSpreadInput, pointDifferentialInput, originalLineInput);
      }
   }  
   
   static class NFL
   {
      private static double pointDif = 0.0;
      
      private static double divider = 0.0;
      
      private static double multiplier = 0.0;
      
      private static double originalSpread = 0.0;
      
      private static double newSpread = 0.0;
      
      private static double originalLine = 0.0;
      
      private static double newLine = 0.0;
      
      private static String newLineString = "";
      
      private static String newSpreadString = "";
      
      final private static int upperLimitSpread = 21;
      final private static int lowerLimitSpread = -21;
      
      final private static int upperLimit = 42;
      final private static int lowerLimit = -42;
      
      public static void buyPoints(double ns, double os, double pd, int oL)
      {
         pointDif = pd;
         
         newSpread = ns;
         
         originalSpread = os;
         
         originalLine = oL;
         
         if (newSpread > 0)
            newSpreadString = "+" + newSpread;
         else
            newSpreadString = String.valueOf(newSpread);
         
         
         divider = 1.5 + Math.abs(.75 * (pointDif - 1)); //33.75
       
         
         if (pointDif >= lowerLimit && pointDif < 0)
         {
            newLine = ((Math.pow(pointDif, 2)) / divider) * (-1) * (100 - (Math.abs((originalLine/100)))) + originalLine;
            newLine = (int)(newLine);
            
            if (newLine > 100)
               newLineString = "+" + newLine;
            
            else if (newLine < -100)
               newLineString = String.valueOf(newLine);
            
            else if (newLine > -100 && newLine < 0)
            {
               newLine = (1 / (newLine/10) * 1000);
               newLine = (int)(newLine);
               newLineString = "+" + Math.abs(newLine);
            }  
            
            else if (newLine < 100 && newLine > 0)
            {
               newLine = (1 / (newLine/10) * 1000);
               newLine = (int)(newLine);
               newLineString = "-" + Math.abs(newLine);
            }  

            else if (newLine == 100 || newLine == -100 || newLine == 0)
               newLineString = "EVEN";
         }
         
         System.out.println("The odds line that you can bet on this game with a spread of " + newSpreadString + " is " + newLineString + ".");
      }
      
      public static void sellPoints(double ns, double os, double pd, int oL)
      {
         pointDif = pd;
         
         newSpread = ns;
         
         originalSpread = os;
         
         originalLine = oL;
         
         if (newSpread > 0)
            newSpreadString = "+" + newSpread;
         else
            newSpreadString = String.valueOf(newSpread);
         
         
         divider = .93 + Math.abs((4.7 - (pointDif * .15)) * (pointDif - 1));
         multiplier = 1;//(Math.abs(pointDif)) / ((pointDif / 3) + 1);
         
         if (pointDif <= upperLimit && pointDif > 0)
         {
            newLine = (((-1) * (Math.pow((pointDif * multiplier), 3))) / divider) * ((Math.abs((originalLine)))) + (originalLine);
            newLine = (int)(newLine);
            
            if (newLine > 100)
               newLineString = "+" + newLine;
            
            else if (newLine < -100)
               newLineString = String.valueOf(newLine);
            
            else if (newLine > -100 && newLine < 0)
            {
               newLine = (1 / (newLine/10) * 1000);
               newLine = (int)(newLine);
               newLineString = "+" + Math.abs(newLine);
            }  
            
            else if (newLine < 100 && newLine > 0)
            {
               newLine = (1 / (newLine/10) * 1000);
               newLine = (int)(newLine);
               newLineString = "-" + Math.abs(newLine);
            }  

            else if (newLine == 100 || newLine == -100 || newLine == 0)
               newLineString = "EVEN";
         }
         
         System.out.println("The odds line that you can bet on this game with a spread of " + newSpreadString + " is " + newLineString + ".");
      }
   }
}