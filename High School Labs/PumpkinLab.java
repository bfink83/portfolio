import java.util.Scanner;

public class PumpkinLab
{
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      Scanner integerScanner = new Scanner(System.in);
      Scanner characterScanner = new Scanner(System.in);
      
      System.out.print("Enter String: ");
      String str1 = keyboard.nextLine();
      
      vertical(str1);
      
      
      System.out.print("Enter another String: ");
      String str2 = keyboard.nextLine();

      printReverse(str2);
      
      
      System.out.print("Please enter your full name: ");
      String str3 = keyboard.nextLine();
      
      processName(str3);
      
      
      System.out.print("Enter another String: ");
      String str4 = keyboard.nextLine();
      
      System.out.print("Enter an integer for the length: ");
      int length = integerScanner.nextInt();
      
      System.out.print("Enter a character: ");
      String character = characterScanner.nextLine();
      
      padString(str4, length, character);
   }
   
   public static void vertical(String s)
   {
      int n = s.length();
      
      for (int k = 0; k < n; k++) 
         {
            System.out.println(s.substring(k, k + 1));
         }
   
   }
   
   public static void printReverse(String s)
   {
      int n = s.length();
      String reverse = "";
      
      for (int k = n - 1; k >= 0; k--) 
         {
            reverse = reverse + s.substring(k, k + 1);
         }
      System.out.println();
   }
   
   public static void processName(String s)
   {
      int spaceIndex = s.indexOf(" ");
      
      s.trim();
      
      String fullname = s.substring(spaceIndex + 1) + ", " + s.substring(0, spaceIndex);
      
      System.out.println("Your name in reverse order is " + fullname);
   } 
 
   public static void padString(String s, int finalLength, String padCharacter)
   {
      int currentLength = s.length();
      
      String paddedString = s;
      
      while (currentLength < finalLength)
      {
         paddedString = paddedString + padCharacter;
         
         currentLength = paddedString.length();
      }
      
      System.out.println(paddedString);
   }
} 

