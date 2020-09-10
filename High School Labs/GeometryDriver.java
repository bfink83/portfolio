public class GeometryDriver
{
   public static void main(String[] args)
   {
      System.out.println(Geometry.areaTriangle(7.0 , 9.0));
      
      System.out.println(Geometry.areaTrapezoid(10.0 , 5.0 , 8.0));
      
      System.out.println(Geometry.volumeSphere(4.0));
      
      System.out.println(Geometry.areaRectangle(4.0 , 5.0));
      
      System.out.println(Geometry.areaSquare(6.0));
      
      System.out.println(Geometry.areaParallelogram(8.0 , 4.0));
      
      System.out.println(Geometry.areaCircle(7.0));
      
      System.out.println(Geometry.volumeRightCircularCylinder(4.0 , 4.0));
      
      System.out.println(Geometry.perimeter(13.0));
      
      System.out.println(Geometry.perimeter(2.0 , 8.0));
      
      System.out.println(Geometry.perimeter(5.0 , 5.0 , 9.0));
      
      System.out.println(Geometry.distance (12.0 , 15.0 , 8.0 , 9.0));
   }
}