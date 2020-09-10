/**
 * @author Bailey Finkelberg
 * JHED: bfinkel3
 * Date: 1/24/20
 * Bicycle class to extend Workout class for Project D.
 *
 */
public class Bicycle extends Workout {
   /**resistance level of bike.*/
   private int resistance;
   
   /**
   * Default Bicycle constructor, unlikely to be called.
   */
   public Bicycle() {
      super();
      
      resistance = 1;
   }
   
   /**
   * Bicycle constructor most likely to be used.
   * @param clientName string containing client's name
   * @param min bike duration
   * @param resistLvl resistance level of bike
   */
   public Bicycle(String clientName, int min, int resistLvl) {
      super(clientName, min);
      setResistance(resistLvl);
   }
   
   /**
   * Bicycle constructor.
   * @param clientName string containing client's name
   * @param min bike duration
   * @param cardioBool boolean that represents if workout is cardio
   */
   public Bicycle(String clientName, int min, boolean cardioBool) {
      super(clientName, min, cardioBool);
      resistance = 1;
   }
   
   /**
   * Bicycle constructor most likely to be used.
   * @param clientName string containing client's name
   * @param min bike duration
   * @param cardioBool boolean that represents if workout is cardio
   * @param resistLvl resistance level of bike
   */
   public Bicycle(String clientName, int min, 
      boolean cardioBool, int resistLvl) {
      super(clientName, min, cardioBool);
      
      setResistance(resistLvl);
   }
   
   /**
   * Setter method for resistance.
   * @param resistLvl int resistance level
   */
   public void setResistance(int resistLvl) {
      resistance = resistLvl;
   }
   
   /**
   * Getter method for resistance.
   * @return resistance int of reistsance level.
   */
   public int getRestistance() {
      return resistance;
   }
   
   /**
   * Method to calculate distance travelled.
   * @return distance travelled in miles as a double.
   */
   public double getDistance() {
      return super.getDuration() / (8.0 * resistance);
   }
   
   /**
   * Overridden method to calculate calories burned.
   * @return calories burned as a double.
   */
   @Override
   public double getCaloriesBurned() {
      return super.getDuration() * (resistance / 3.0) * 2.0;
   }
   
   /**
   * Overridden toString method.
   * @return string representing bicycle workout.
   */
   @Override
   public String toString() {
      return "[" + super.getClient() + " completed bicycle workout: " 
         + super.getDuration() + " mins, " + getCaloriesBurned() 
            + " cals, resistance " + resistance + ", " + getDistance() + " mi]";
   }
   
//[USER completed bicycle workout: DUR mins, CALS cals, resistance RES, DIST mi]
   
}