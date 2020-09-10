import java.util.Scanner;

public class CarRunner
{
   public static void main (String args[])
   {
      Scanner scan = new Scanner(System.in);
      
      Car car1 = new Car();
      System.out.println("Car One is a " + car1.getName());
      System.out.println("Number of cars created: " + car1.getCounter());
      
      Car car2 = new Car("Charger");
      System.out.println("Car Two is a " + car2.getName());
      System.out.println("Number of cars created: " + car2.getCounter());
      
      Car car3 = new Car("Corvette");
      System.out.println("Car Three is a " + car3.getName());
      System.out.println("Number of cars created: " + car3.getCounter());
      
      Car car4 = new Car(scan.nextLine());
      System.out.println("Car Four is a " + car4.getName());
      System.out.println("Number of cars created: " + car4.getCounter());
   }
}