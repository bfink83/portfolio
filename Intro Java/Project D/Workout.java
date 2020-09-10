/**
 * @author Bailey Finkelberg
 * JHED: bfinkel3
 * Date: 1/23/20
 * Workout class to use as a base class for Project D.
 *
 */

public class Workout {
   /**default duration of workout if amount of time is not uses as an arg. */
   public static int defaultDuration = 60;
   
   /**user ID value representing the person completing the workout.*/
   private String client;
   
   /**a boolean representation of the workout type (cardio or strength). */
   private boolean isCardio = true;
   
   /**duration of the workout in minutes. */
   private int workoutDuration;
   
   /**string that contains type of workout (cardio or strength). */
   private String type;
   
   /**
   * Default Workout constructor unlikely to be called b/c name is unspecified.
   * Duration set to default and isCardio is true by default.
   */
   public Workout() {
      client = "Default Client";
      
      workoutDuration = defaultDuration;
      
      if (isCardio) {
         type = "cardio";
      }
      else {
         type = "strength";
      }
   }
   
   /**
   * Workout constructor with just clientName as a parameter.
   * Duration set to default and isCardio is true by default.
   * @param clientName string that represents client's name or username.
   */
   public Workout(String clientName) {
      client = clientName;
      
      workoutDuration = defaultDuration;
      
      if (isCardio) {
         type = "cardio";
      }
      else {
         type = "strength";
      }
   }
   
   /**
   * Workout constructor with clientName and min as parameters.
   * isCardio set to true by default.
   * Duration set to min.
   * @param clientName string that represents client's name or username.
   * @param min duration of workout in min.
   */
   public Workout(String clientName, int min) {
      client = clientName;
      
      workoutDuration = min;
      
      if (isCardio) {
         type = "cardio";
      }
      else {
         type = "strength";
      }
   }
   
   /**
   * Workout constructor with clientName and cardioBool as parameters.
   * isCardio set to cardioBool.
   * Duration set to default duration.
   * @param clientName string that represents client's name or username.
   * @param cardioBool boolean to determine whether or not workout is cardio.
   */
   public Workout(String clientName, boolean cardioBool) {
      client = clientName;
      
      workoutDuration = defaultDuration;
      
      isCardio = cardioBool;
      
      if (isCardio) {
         type = "cardio";
      }
      else {
         type = "strength";
      }
   }
   
   /**
   * Workout constructor with clientName, min, and cardioBool as parameters.
   * isCardio set to cardioBool.
   * Duration set to min.
   * @param clientName string that represents client's name or username.
   * @param min duration of workout in min.
   * @param cardioBool boolean to determine whether or not workout is cardio.
   */
   public Workout(String clientName, int min, boolean cardioBool) {
      client = clientName;
      
      workoutDuration = min;
      
      isCardio = cardioBool;
      
      if (isCardio) {
         type = "cardio";
      }
      else {
         type = "strength";
      }
   }
   
   /**
   * Calculate calories burned.
   * Determines whether or not workout is cardio.
   * Caloric Multiplier is determined by type of workout.
   * Then multiplied by workout duration.
   * @return calories burned as a double
   */
   public double getCaloriesBurned() {
      if (isCardioWorkout()) {
         return getDuration() * 8.0;
      }
      else {
         return getDuration() * 5.0;
      } 
   }
   
   /**
   * Getter method for workout duration.
   * @return workout duration.
   */
   public int getDuration() {
      return this.workoutDuration;
   }
   
   /**
   * Setter method for workout duration.
   * @param min new workout duration.
   */
   public void setDuration(int min) {
      this.workoutDuration = min;
   }
   
   /**
   * Getter method for isCardio boolean.
   * isCardio determines whether or not workout is cardio.
   * @return true or false from isCardio field.
   */
   public boolean isCardioWorkout() {
      return this.isCardio;
   }
   
   /**
   * Setter method for isCardio boolean.
   * @param cardioBool new value for isCardio boolean.
   */
   public void setCardioWorkout(boolean cardioBool) {
      isCardio = cardioBool;
      
      if (isCardio) {
         type = "cardio";
      }
      else {
         type = "strength";
      }
   }
   
   /**
   * Compares two workouts and returns an int. 
   * Comparison is based on the number of calories burned.
   * Returns negative integer if Workout A burns less calories than Workout B.
   * Returns positive integer if A > B.
   * Returns 0 if even.
   * @param compWorkout Workout B that Workout A is being compared against.
   * @return difference in calories b/w workouts A and B.
   */
   public int compareTo(Workout compWorkout) {
      double calWorkA = getCaloriesBurned();
      double calWorkB = compWorkout.getCaloriesBurned();
      
      return (int) (calWorkA - calWorkB);
      
      /*if (calWorkA < calWorkB) {
         return calWorkA - calWorkB;
      }
      else if (calWorkA > calWorkB) {
         return calWorkA - calWorkB;
      }
      else if (calWorkA == calWorkB) {
         return 0;
      }*/
   }
   
   /**
   * Setter method to change default duration for entire class.
   * @param min new default duration.
   */
   public static void setDefaultDuration(int min) {
      defaultDuration = min;
   }
   
   /**
   * Overridden toString method that shows Workout details.
   * Displays: [USER completed TYPE workout: DUR mins, CALS cals].
   * @return string representation of Workout object.
   */
   @Override
   public String toString() {
      return "[" + client + " completed " + type + " workout: " 
         + workoutDuration + " mins, " + this.getCaloriesBurned() + " cals]";
   }
   
   /**
   * Getter method for client's name string.
   * To be called in derived classes
   * @return client string of client's name.
   */
   public String getClient() {
      return client;
   }
}