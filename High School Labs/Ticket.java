
public abstract class Ticket
{
   private int ticketNumber;
   
   public Ticket(int n)
   {
      ticketNumber = n;
   }

   public abstract double getPrice();
  
   public String toString()
   {
      return "Number: " + ticketNumber + ", Price ";
   }
}

class WalkupTicket extends Ticket
{
   private double ticketPrice;
    
   public WalkupTicket(int n)
   {
      super(n);
      ticketPrice = 50.0;
   }
   
   public double getPrice()
   { 
      return ticketPrice;
   }
   
   public String toString()
   {
      return super.toString() + getPrice();
   }
}
   
class AdvanceTicket extends Ticket
{
  
   private double ticketPrice; 
   private int daysAdv;
   
   public AdvanceTicket(int n, int dia)
   {
      super(n);
      
      daysAdv = dia;
      
      if(dia >= 10)
         ticketPrice = 30;
      else
         ticketPrice = 40;
   } 
   
   public double getPrice()
   { 
      return ticketPrice;
   }
   
   public int getDaysAdv()
   {
      return daysAdv;
   }
  
   public String toString()
   {
      return super.toString() + getPrice() + ", Purchased " + getDaysAdv() + " days in advance.";
   } 
   
}
   
class StudentAdvanceTicket extends AdvanceTicket
{
    
   private double ticketPrice;
   private int daysAdv;
   
   public StudentAdvanceTicket(int n, int dia)
   {
      super(n, dia);
      
      daysAdv = dia;
      
      ticketPrice = super.getPrice()/2;
   }  
   
   public double getPrice()
   { 
      return ticketPrice;
   }
   
   public int getDaysAdv()
   {
      return daysAdv;
   }
   
   public String toString()
   {
      return super.toString() + " (ID required) ";
   }
}