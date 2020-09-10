
/**
 * @author Bailey Finkelberg
 * JHED: bfinkel3
 * Date: 1/24/20
 * Yoga class to extend Workout class for Project D.
 *
 */
public class Yoga extends Workout {
   
   /**int representing difficulty level.*/
   private int difficulty;
   
   /**String representing difficulty level.
   *  (beginner, intermediate, advanced)*/
   private String diffString;
   
   /**
   * Default constructor unlikely to be called.
   */
   public Yoga() {
      super();
      
      setDifficulty(1);
   }
   
   /**
   * Yoga constructor most likely to be used.
   * @param clientName string containing client's name
   * @param min workout duration
   * @param diffLvl difficulty level of session
   */
   public Yoga(String clientName, int min, int diffLvl) {
      super(clientName, min);
      setDifficulty(diffLvl);
   }
   
   /**
   * Yoga constructor.
   * @param clientName string containing client's name
   * @param min workout duration
   * @param cardioBool boolean to tell if workout is cardio.
   */
   public Yoga(String clientName, int min, boolean cardioBool) {
      super(clientName, min, cardioBool);
      setDifficulty(1);
   }
   
   /**
   * Yoga constructor most likely to be used.
   * @param clientName string containing client's name
   * @param min workout duration
   * @param cardioBool boolean to tell if workout is cardio.
   * @param diffLvl difficulty level of session
   */
   public Yoga(String clientName, int min, boolean cardioBool, int diffLvl) {
      super(clientName, min, cardioBool);
      
      setDifficulty(diffLvl);
   }
   
   /**
   * Setter method for difficulty level.
   * @param diffLvl int of difficulty level.
   */
   public void setDifficulty(int diffLvl) {
      difficulty = diffLvl;
      
      if (difficulty == 1) {
         diffString = "beginner";
      }
      else if (difficulty == 2) {
         diffString = "intermediate";
      }
      else if (difficulty == 3) {
         diffString = "advanced";
      }
   }
   
   /**
   * Getter method for difficulty level.
   * @return difficulty int of difficulty level.
   */
   public int getDifficulty() {
      return difficulty;
   }
   
   /**
   * Getter method for difficulty level word.
   * @return diffString string containing difficulty level.
   */
   public String getDifficultyString() {
      return diffString;
   }
   
   /**
   * Overridden method to calculate calories burned.
   * @return calories burned as a double.
   */
   @Override
   public double getCaloriesBurned() {
      return super.getDuration() * 2.1 * difficulty;
   }
   
   /**
   * Overridden toString method.
   * @return string representing yoga workout.
   */
   @Override
   public String toString() {
      return "[" + super.getClient() + " completed yoga workout: " 
         + super.getDuration() + " mins, " + diffString + " level, " 
            + getCaloriesBurned() + " cals]";
   //[USER completed yoga workout: DUR mins, LEV level, CALS cals]
   }
}