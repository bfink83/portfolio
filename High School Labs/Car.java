public class Car
{
   private static int counter;
   private String name;
   
   public Car()
   {
      name = "Mustang";
      counter++;
   }
   
   public Car(String n)
   {
      name = n;
      counter++;
   }
   
   public String getName()
   {
      return name;
   }
   
   public int getCounter()
   {
      return counter;
   }
}