public class Geometry
{   
   public static double areaTriangle(double base, double height)
   {
      return ((base * height) / 2); 
   }
   
      
   public static double areaTrapezoid(double base1, double base2, double height)
   {
      return ((base1 + base2)/2) * height;
   }
   
   public static double volumeSphere(double radius)
   {
      return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
   }
   
   public static double areaRectangle(double length, double width)
   {
      return (length * width);
   }
   
   public static double areaSquare(double side)
   {
      return areaRectangle (side, side);
   }
   
   public static double areaParallelogram (double base, double height)
   {
      return areaRectangle (base, height);
   }
   
   public static double areaCircle (double radius)
   {
      return Math.PI * Math.pow(radius, 2);
   }
   
   public static double volumeRightCircularCylinder (double radius, double height)
   {
      return (areaCircle (radius)) * height;
   }
   
   public static double perimeter (double side)
   {
      return side * 4;
   }
   
   public static double perimeter (double side1, double side2)
   {
      return (2 * side1) + (2 * side2);
   }
   
   public static double perimeter (double side1, double side2, double side3)
   {
      return side1 + side2 + side3;
   }
   
   public static double distance (double x1, double x2, double y1, double y2)
   {
      return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
   }
}  