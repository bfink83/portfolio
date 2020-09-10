import java.util.Scanner;

public class Littlelab2
{
   public static void main (String args[])
   {
      Scanner input = new Scanner(System.in);
      
      System.out.print("Enter number: ");
      int num1 = input.nextInt();
      
      if (num1 % 5 == 0)
      System.out.println("Evaluates to 1");
      
      else if (num1 % 4 == 0)
      System.out.println("Evaluates to 2");
      
      else if (num1 % 3 == 0)
      System.out.println("Evaluates to 3");
      
      else
      System.out.println("Evaluates to 4");
   }
}
      