public class Blank
{
   public static void main(String[] args)
   {
            
      
      Workout workout = new Workout();
      
      Workout bailey = new Workout("Bailey");
      
      Workout dur = new Workout("Ben", 20);
      
      //System.out.println(Workout.defaultDuration);
      
      Workout.setDefaultDuration(55);
      
      Workout card = new Workout("Josh", false);
      
      Workout all = new Workout("Jordan", 33, false);
      
      //System.out.print(Workout.defaultDuration);
      
      //System.out.println(bailey.getCaloriesBurned());
      //System.out.println(all.getCaloriesBurned());
      //System.out.println(card.getCaloriesBurned());
      
      
      Bicycle bike = new Bicycle("Dan", 35, true);
      bike.setResistance(7);
      
      Bicycle bike2 = new Bicycle("Bob", 40, true, 5);
      
      double bike2Dist = bike2.getDistance();
      double bike2Cals = bike2.getCaloriesBurned();
      
      System.out.println("" + bike2Cals);
      
      System.out.println(bike2);
      
      Yoga yoga = new Yoga("Allen", 60, false, 3);
      System.out.println(yoga);
      
      
      Client mickey = new Client("Mickey");
      
     
   }
}


/*if (currentCell.getNumber() == grid[erow][ecol].getNumber()) {
         return true;
      }
      else if (!currentCell.hasNorth() && !currentCell.fromNorth) {
         grid[srow - 1][scol].fromSouth = true;
         
         return solve(srow - 1, scol, erow, ecol);
      }
      else if (!currentCell.hasEast() && !currentCell.fromEast) {
         
         grid[srow][scol + 1].fromWest = true;
         
         return solve(srow, scol + 1, erow, ecol);
      }
      else if (!currentCell.hasSouth() && !currentCell.fromSouth) {
         grid[srow + 1][scol].fromNorth = true;
         
         return solve(srow + 1, scol, erow, ecol);
      }
      else if (!currentCell.hasWest() && !currentCell.fromWest) {
         grid[srow][scol - 1].fromEast = true;
         
         return solve(srow, scol - 1, erow, ecol);
      }
      
      
      if (!currentCell.hasNorth()) {
         grid[srow - 1][scol].fromSouth = true;
         return solve(srow - 1, scol, erow, ecol);
      }
      else if (!currentCell.hasEast()) {
         grid[srow][scol + 1].fromWest = true;
         return solve(srow, scol + 1, erow, ecol);
      }
      else if (!currentCell.hasSouth()) {
         grid[srow + 1][scol].fromNorth = true;
         return solve(srow + 1, scol, erow, ecol);
      }
      else if (!currentCell.hasWest()) {
         grid[srow][scol - 1].fromEast = true;
         return solve(srow, scol - 1, erow, ecol);
      }


      
      
      
      
      
      for (int i = 0; i < Client.clientCount; ++i) {
         System.out.println(clientList[i]);
      }
      
      for (int i = 0; i < Client.clientCount; ++i) {
         for (int j = 0; j < clientList[i].getWorkoutCount(); ++j) {
         System.out.println(clientList[i].getWorkout(j));
         }
      }







      else if(calsList[j] instanceof Yoga) {
                     Yoga tempYoga = calsList[j];
                     calsList[j] = calsList[j - 1];
                     calsList[j - 1] = tempYoga;
                  }
                  else if(calsList[j] instanceof Bicycle) {
                     Bicycle tempBike = calsList[j];
                     calsList[j] = calsList[j - 1];
                     calsList[j - 1] = tempBike;
                  }

*/