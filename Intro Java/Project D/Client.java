
/**
 * @author Bailey Finkelberg
 * JHED: bfinkel3
 * Date: 1/24/20
 * Client class to be used in Project D.
 *
 */
public class Client {

   /**counter to be incremented every time new Client is created.*/
   public static int clientCount = 0;
   
   /**string containing client's name.*/
   private String clientName;
   
   /**int containing client's id number.*/
   private int idNum;
   
   /**initialized empty Workout array with maximum 100 members.*/
   private Workout[] workoutList = new Workout[100];
   
   /**workout counter to be incremented when any type of workout is added.*/
   private int workoutCount = 0;
   
   /**bike counter to be incremented when bike workout is added.*/
   private int bikeCount = 0;
   
   /**yoga counter to be incremented when yoga workout is added.*/
   private int yogaCount = 0;
   
   /**Client Constructor.
   * IdNum is set to the value of client counter + 100.
   * clientCount is then incremented.
   * @param name client's name read from file.
   */
   public Client(String name) {
      clientName = name;
      
      idNum = clientCount + 100;
      
      clientCount++;
   }
   
   /**method to add an "other" workout to Workout array.
   * @param duration length of workout
   * @param cardio boolean to tell if workout is cardio.
   */
   public void addWorkout(int duration, String cardio) {
      boolean isCardio = true;
      
      if (cardio.equals("no")) {
         isCardio = false;
      }
      
      workoutList[workoutCount] = new Workout(clientName, duration, isCardio);
      
      workoutCount++;
   }
   
   /**Method like addWorkout but specifically for yoga workouts.
   * @param duration length of workout.
   * @param level difficulty level
   */
   public void addYoga(int duration, int level) {
      
      workoutList[workoutCount] = new Yoga(clientName, duration, level);
      
      workoutCount++;
      yogaCount++;
   }
   
   /**Method like addWorkout but specifically for bike workouts.
   * @param duration length of workout.
   * @param resistance level of resistance.
   */
   public void addBike(int duration, int resistance) {
      
      workoutList[workoutCount] = new Bicycle(clientName, duration, resistance);
      
      workoutCount++;
      bikeCount++;
   }
   
   /**
   * Getter method for workout counter.
   * @return workoutCount
   */
   public int getWorkoutCount() {
      return workoutCount;
   }
   
   /**
   * Getter method for bike counter.
   * @return bikeCount
   */
   public int getBikeCount() {
      return bikeCount;
   }
   
   /**
   * Getter method for yoga counter.
   * @return yogaCount
   */
   public int getYogaCount() {
      return yogaCount;
   }
   
   /**
   * Getter method for Workout object from array.
   * @param index specified index of Workout array.
   * @return workoutList[index] Workout object at specified index.
   */
   public Workout getWorkout(int index) {
      return workoutList[index];
   }
   
   /**
   * Getter method for client name.
   * @return clientName
   */
   public String getName() {
      return clientName;
   }
   
    /**
   * Overridden toString method.
   * @return string representing client ID and name.
   */
   @Override
   public String toString() {
      return "[" + idNum + ", " + clientName + "]";
   }

}