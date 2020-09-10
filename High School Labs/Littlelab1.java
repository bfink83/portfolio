import java.util.Scanner;

public class Littlelab1
{
   public static void main (String args[])
   {
      Scanner input = new Scanner(System.in);
      
      System.out.print("Enter the original bill amount: ");
      double bill = input.nextDouble();
      
      if (bill > 2000)
      {   
         double discount;
         discount = bill * 0.15;
         
         double total = bill - discount;
         System.out.print("Bill after discount: " + total);
      }
      
      else
      System.out.print("Bill after discount: " + bill);
   }
}  
      
         