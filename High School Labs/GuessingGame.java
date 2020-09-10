import java.util.Scanner;

public class GuessingGame
{
   public static void main(String args[])
   {
      Scanner input = new Scanner(System.in);
      System.out.print("Do you want to play a game? (Yes or No)==> ");
      char yesorno = input.next().charAt(0);
      
      if ((yesorno == 'Y') || (yesorno == 'y'))
      {   
         char yesorno2;
         
         do
         {
            System.out.print("What would you like the lower limit to be? ");
            int lowerlimit = input.nextInt();
      
            System.out.print("What would you like the upper limit to be? ");
            int upperlimit = input.nextInt();
      
            System.out.print("How many guesses would you like? ");
            int guesses = input.nextInt();
            
            int randomNum = (int) (Math.random()*(upperlimit - lowerlimit +1) + lowerlimit);
            
            int guesscounter = 1;
            
            int guessInput = 234229341;
                 
            while ((guesscounter <= guesses) && (guessInput != randomNum))
            {
               System.out.print("Guess " + guesscounter + ":");
               guessInput = input.nextInt();
               
               if (guessInput == randomNum)
                  System.out.println("Correct! It took you " + guesscounter + " tries to guess the number. ");
                  
               else if (guessInput < randomNum)
                  System.out.println("Incorrect. The number is higher than your guess. ");
                  
               else if (guessInput > randomNum)
                  System.out.println("Incorrect. The number is lower than your guess. ");
                  
               guesscounter++;
            }
            
            if (guessInput == randomNum)
               guesscounter--;
            
            if (guesscounter > guesses)
               System.out.println("Game over. You ran out of guesses. The correct number was " + randomNum + ".");
               
            System.out.print("Do you want to play again? (Yes or No)==> ");
            yesorno2 = input.next().charAt(0);
      
         }
         while ((yesorno2 == 'Y') || (yesorno2 == 'y'));
      }
   }
}