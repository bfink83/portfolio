import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Bailey Finkelberg
 * JHED: bfinkel3
 * Date: 1/24/20
 * ProjectD class to be used as driver class for Project D.
 *
 */
public class ProjectD {
   /**
   * Main method for Project D.
   * @param args not used.
   * @throws IOException FileNotFoundException if file is not found in folder.
   */
   public static void main(String[] args) throws IOException {
   
      Client[] clientList = new Client[500];
      
      int userChoice = -1;
      
      Scanner scnr = new Scanner(System.in);
      
      System.out.println("0)     Quit the program");
      System.out.println("1)     Add clients from a plain text file");
      System.out.println("       Enter file name: " + 
         "[Get user input for the name of the input file]");
      System.out.println("2)     Add workouts for clients"
         + " from a plain text file");
      System.out.println("       Enter file name: "
         + "[Get user input for the name of the input file]");
      System.out.println("3)     Display all client "
         + "identification strings and their names, "
         + "along with total number of ");
      System.out.println("       workouts completed, and "
         + "total numbers of bicycle workouts and yoga workouts");
      System.out.println("4)     Display details of all "
         + "workouts completed by a given client");
      System.out.println("       Enter client identification string: "
         + "[Get user input for the name of the client]");
      System.out.println("5)     Display total calories "
         + "burned by a given client");
      System.out.println("       Enter client identification string: "
         + "[Get user input for the name of the client]");
      System.out.println("6)     Display average calories burned per "
         + "workout for a given client");
      System.out.println("       Enter client identification string: "
         + "[Get user input for the name of the client]");
      System.out.println("7)     Display all workouts in the system, "
         + "sorted by calories burned, with the");
      System.out.println("       lowest-calorie-burning "
         + "workout listed first");
      System.out.println("8)     Display a list of client identification "
         + "strings containing only those clients who have"); 
      System.out.println("       completed at least one bicycle "
         + "workout and at least one yoga workout");
      System.out.println("");
      
   
      do {
         System.out.print("Enter the number of your choice -> ");
         userChoice = scnr.nextInt();
         
         if (userChoice == 0) {
            System.out.println("Goodbye.");
         }
         else if (userChoice == 1) {
            System.out.print("Enter file name: ");
            
            String fileName = scnr.next();
            
            FileReader inReader = new FileReader(fileName);   //open file
            Scanner inFS = new Scanner(inReader);
            
            while (inFS.hasNextLine()) {
               clientList[Client.clientCount] = new Client(inFS.nextLine());
            }
            
            inReader.close();
         }
         else if (userChoice == 2) {
            System.out.print("Enter file name: ");
            
            String fileName = scnr.next();
            
            FileReader inReader = new FileReader(fileName);   //open file
            Scanner inFS = new Scanner(inReader);
            
            while (inFS.hasNext()) {
               int userID = inFS.nextInt();
               int userIndex = userID - 100;
               
               String type = inFS.next();
               
               int duration = inFS.nextInt();
               
               if (type.equals("other")) {
               
                  String cardio = inFS.next();
                  
                  clientList[userIndex].addWorkout(duration, cardio);
                  
               }
               else if (type.equals("yoga")) {
               
                  int level = inFS.nextInt();
                  
                  clientList[userIndex].addYoga(duration, level);
               }
               else if (type.equals("bike")) {
                  
                  int resistance = inFS.nextInt();
                  
                  clientList[userIndex].addBike(duration, resistance);
               }
               if (inFS.hasNextLine()) {
                  inFS.nextLine();
               }
            }
            
            inReader.close();     
         }
         else if (userChoice == 3) {
            for (int i = 0; i < Client.clientCount; ++i) {
               System.out.print(clientList[i]);
               System.out.println(" Total = " + clientList[i].getWorkoutCount() 
                  + " Bike = " + clientList[i].getBikeCount() 
                  + " Yoga = " + clientList[i].getYogaCount());
            }
         }
         else if (userChoice == 4) {
            scnr.nextLine();
            System.out.print("Enter client identification string: ");
            String clientName = scnr.nextLine();
            
            boolean clientFound = false;
            
            int userIndex = 0;
            
            int i = 0;
            
            while (!clientFound) {
               
               if (clientName.equals(clientList[i].getName())) {
                  userIndex = i;
                  clientFound = true;
               }
               
               i++;
            }
            
            for (int j = 0; j < clientList[userIndex].getWorkoutCount(); ++j) {
               System.out.println(clientList[userIndex].getWorkout(j));
            }
            
         }
         else if (userChoice == 5) {
            scnr.nextLine();
            System.out.print("Enter client identification string: ");
            String clientName = scnr.nextLine();
            
            double totalCals = 0.0;
            
            boolean clientFound = false;
            
            int userIndex = 0;
            
            int i = 0;
            
            while (!clientFound) {
               
               if (clientName.equals(clientList[i].getName())) {
                  userIndex = i;
                  clientFound = true;
               }
               i++;
            }
            
            for (int j = 0; j < clientList[userIndex].getWorkoutCount(); ++j) {
               totalCals = totalCals 
                  + clientList[userIndex].getWorkout(j).getCaloriesBurned();
            }
            System.out.println("Total calories burned: " + totalCals);
         }
         else if (userChoice == 6) {
            scnr.nextLine();
            System.out.print("Enter client identification string: ");
            String clientName = scnr.nextLine();
            
            double totalCals = 0.0;
            double avgCals = 0.0;
            
            boolean clientFound = false;
            int userIndex = 0;
            int i = 0;
            
            while (!clientFound) {
               
               if (clientName.equals(clientList[i].getName())) {
                  userIndex = i;
                  clientFound = true;
               }
               i++;
            }
            
            for (int j = 0; 
               j < clientList[userIndex].getWorkoutCount(); ++j) {
               totalCals = totalCals + 
                  clientList[userIndex].getWorkout(j).getCaloriesBurned();
            }
            
            avgCals = totalCals / clientList[userIndex].getWorkoutCount();
            
            System.out.println("Average calories burned: " + avgCals);
         }
         else if (userChoice == 7) {
            int totWorkouts = 0;
            
            for (int i = 0; i < Client.clientCount; ++i) {
               totWorkouts = totWorkouts + clientList[i].getWorkoutCount();
            }
            
            Workout[] calsList = new Workout[totWorkouts];
            int index = 0;
            
            for (int i = 0; i < Client.clientCount; ++i) {
               for (int j = 0; j < clientList[i].getWorkoutCount(); ++j) {
                  
                  calsList[index] = clientList[i].getWorkout(j);
                  index++;
               }
            }
            
            for (int i = 0; i < totWorkouts; ++i) {
               int j = i;
               
               while (j > 0 && calsList[j].compareTo(calsList[j - 1]) < 0) {
                  if (calsList[j] instanceof Workout) {
                     Workout tempOther = calsList[j];
                     calsList[j] = calsList[j - 1];
                     calsList[j - 1] = tempOther;
                     --j;
                  }
               }
            }
            for (int i = 0; i < totWorkouts; ++i) {
               System.out.println(calsList[i]);
            }
         }
         else if (userChoice == 8) {
            for (int i = 0; 
               i < Client.clientCount; ++i) {
               if (clientList[i].getBikeCount() > 0 
                  && clientList[i].getYogaCount() > 0) {
                  
                  System.out.println(clientList[i]); 
               }
            }
         }
      } while (userChoice != 0);
   }
}