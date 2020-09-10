public class Product
{
   private String name;
   private double price;
   private boolean onSale;
   
   public Product()
   {
      name = "Widget";
      price = 10.0;
      onSale = false;
   }
      
   public Product(String n, double p, boolean o)
   {
      name = n;
      price = p;
      onSale = o;
   }
   
   public Product(String n, double p)
   {
      name = n;
      price = p;
      onSale = true;
   }
   
   public String getName()
   {
      return name;
   }
   
   public double getPrice()
   {
      return price;
   }
   
   public void setOnSale(boolean o)
   {
      onSale = o;
   }
   
   public boolean getOnSale()
   {
      return onSale;
   }
   
   public void reducePrice()
   {
      price -= 5.0;
   }
   
   public String toString()
   {
      String productstring = "Name: " + name + "\nPrice: " + price + "\nIs it on sale? ";
      
      if (onSale)
         productstring += "Yes.";
      else
         productstring += "No.";
      
      return productstring;
   }
}