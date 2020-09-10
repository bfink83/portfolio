class Monster
{
    private String myName;
    
    public Monster()
    {
      myName = "Monster";
    }
    public Monster( String name )
    {
      myName = name;
    }
    public String toString()
    {
      return myName + "\n";
    }
}

class Teeth
{
    private int numTeeth;
    
    public Teeth()
    {
      numTeeth = 32;
    }
    
    public Teeth(int t)
    {
      numTeeth = t;
    }
    
    public void eatFlesh()
    {
      System.out.println("Chomp Chomp");
    }
    
    public String toString()
    {
      return numTeeth + "\n";
    }    
}

class Zombie extends Monster
{
   private double myWeight;
   private double myAge;
   private Teeth teeth;
   
   public Zombie()
   {
      super();
      
      teeth = new Teeth();
   }
   
   public Zombie(String name)
   {
      super(name);
      
      teeth = new Teeth();
   }
   
   public Zombie(double weight, int age)
   {
      myWeight = weight;
      
      myAge = age;
      
      teeth = new Teeth();
   }
   
   public Zombie(String name, double weight, int age)
   {
      super(name);
      
      myWeight = weight;
      
      myAge = age;
      
      teeth = new Teeth();
   }
   
   public Zombie(String name, double weight, int age, int t)
   {
      super(name);
      
      myWeight = weight;
      
      myAge = age;
      
      teeth = new Teeth(t);
   }
   
   public void feed()
    {
      teeth.eatFlesh();
    }

   
   public String toString()
    {
      return super.toString() + myWeight + " " + myAge + " " + teeth ;
    }
    

   
   
      
      
}

public class ZombieRun
{
   public static void main(String args[])
   {
      Zombie z = new Zombie();
      System.out.println(z);
      
      Zombie z2 = new Zombie("Jman");
      System.out.println(z2);
      
      Zombie z3 = new Zombie("Jordan", 75.4, 16, 8);
      System.out.println(z3);
      
      Zombie z4 = new Zombie("Eske", 101.3, 17);
      System.out.println(z4);
      
      Zombie z5 = new Zombie(1290.5, 12);
      System.out.println(z5);
      
      z.feed();
   }
}