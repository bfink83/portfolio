import java.util.Scanner;

public class Littlelab3
{
   public static void main (String args[])
   {
      Scanner input = new Scanner(System.in);
      
      System.out.print("Enter number: ");
      int num1 = input.nextInt();
      
      double digitcounter = 0;
      int sum = 0;
      int remainder;
      
      while (num1 >= 10)
      {
         remainder = num1 % 10;
         sum = sum + remainder;
         num1 = num1 / 10;
         digitcounter++;
      }
      
      remainder = num1;
      
      sum = sum + remainder;
      
      digitcounter++;
      
      double average;
      
      average = sum / digitcounter;
      System.out.print("Average: " + average);
   }
}
      