/**
 * @author Bailey Finkelberg
 * JHED: bfinkel3
 * Date: 1/20/20
 * Cell represents a single tile of a maze.
 *
 * STUDENT WILL NEED TO COMPLETE THIS CLASS AS INDICATED BELOW
 *
 */
public class Cell {

   /** this counter is used to number the cells in a maze; we give
       it package-level (not private or public) access on purpose. */
   static int count = 0;

   /** the number of the cell in the grid, counting left to right,
       starting at 0. */
   private int num;
   
   /** the data stored in the cell. */
   private String data;
   
   //TO DO: ADD PRIVATE INSTANCE VARIABLES AS NEEDED TO COMPLETE THE CLASS
   
   /**cell's north wall boolean. */
   private boolean northWallBool;
   
   /**cell's south wall boolean. */
   private boolean southWallBool;
   
   /**cell's east wall boolean. */
   private boolean eastWallBool;
   
   /**cell's west wall boolean. */
   private boolean westWallBool;
   
   /**cell's north wall bit for to.String() method. */
   private int northBit;
   
   /**cell's south wall bit for to.String() method. */
   private int southBit;
   
   /**cell's east wall bit for to.String() method. */
   private int eastBit;
   
   /**cell's west wall bit for to.String() method. */
   private int westBit;

   
   /**
    * Construct a cell that has all four walls by default and
    * which is given a String value that matches the uniquely-
    * assigned number of the Cell.
    */
   public Cell() {
      this.num = count++;
      this.data = String.valueOf(this.num);
      
      this.setWalls("1111");
      // TO DO: COMPLETE THIS CONSTRUCTOR (NO NEED TO EDIT LINES ABOVE)
   }
   
   /**
    * Cell constructor with defined wall parameters and
    * which is given a String value that matches the uniquely-
    * assigned number of the Cell.
    * @param n true if north side of the cell should have a wall
    * @param s true if south side of the cell should have a wall
    * @param w true if west side of the cell should have a wall
    * @param e true if east side of the cell should have a wall
    */
   public Cell(boolean n, boolean e, boolean s, boolean w) {
      this.num = count++;
      this.data = String.valueOf(this.num);
      
      char north;
      char south;
      char east;
      char west;
     
      if (n) {
         north = '1';
      }
      else {
         north = '0';
      }
      
      if (e) {
         east = '1';
      }
      else {
         east = '0';
      }
      
      if (s) {
         south = '1';
      }
      else {
         south = '0';
      }
      
      
      if (w) {
         west = '1';
      }
      else {
         west = '0';
      }
      
      String walls = "" + north + east + south + west;
      
      this.setWalls(walls);
      
      
      //TO DO: REPLACE THIS STUB

   }
   
   

   /**
    * Use a "bit" string in NESW (north-east-south-west) order to 
    * represent and set the walls of this cell.  A 1 bit indicates 
    * that the wall exists, a 0 (or anything else) means no wall.
    * The given string is assumed to have length at least 4; any 
    * characters in it beyond the first four will be ignored.
    * @param walls the bit string to parse
    */
   public void setWalls(String walls) {
      boolean n;
      boolean e;
      boolean s;
      boolean w;
      
      if (walls.charAt(0) == '1') {
         n = true;
      }
      else {
         n = false;
      }
      
      if (walls.charAt(1) == '1') {
         e = true;
      }
      else {
         e = false;
      }

      
      if (walls.charAt(2) == '1') {
         s = true;
      }
      else {
         s = false;
      }

      
      if (walls.charAt(3) == '1') {
         w = true;
      }
      else {
         w = false;
      }

      
      setNorth(n);
      setEast(e);
      setSouth(s);
      setWest(w);
      
      
      //return;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Get a "bit string" representation of this cell's walls, in 
    * NESW (north-east-south-west) order.
    * A 1 represents that a wall exists, and a 0 represents no wall.
    * For example, "1001" is returned when only the north and west
    * walls exist for a cell.
    * @return the 4-character "bit string"
    */
   public String toString() {
      return "" + northBit + eastBit + southBit + westBit;  
      //TO DO: REPLACE THIS STUB
   }

   /**
    * Return whether this cell's north wall exists.
    * @return true if and only if the north wall exists
    */
   public boolean hasNorth() {
      return northWallBool;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Indicate whether this cell's north wall should exist.
    * @param northVal  true if wall should exist; false otherwise
    */
   public void setNorth(boolean northVal) {
      
      if (northVal) {
         northWallBool = true;
         northBit = 1;
      }
      else if (!northVal) {
         northWallBool = false;
         northBit = 0;
      }
      
      //return;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Return whether this cell's south wall exists.
    * @return true if and only if the south wall exists
    */
   public boolean hasSouth() {
      return southWallBool;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Indicate whether this cell's south wall should exist.
    * @param southVal  true if wall should exist; false otherwise
    */
   public void setSouth(boolean southVal) {
      
      if (southVal) {
         southWallBool = true;
         southBit = 1;
      }
      else if (!southVal) {
         southWallBool = false;
         southBit = 0;
      }
      
      //return;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Return whether this cell's west wall exists.
    * @return true if and only if the west wall exists
    */
   public boolean hasWest() {
      return westWallBool;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Indicate whether this cell's west wall should exist.
    * @param westVal  true if wall should exist; false otherwise
    */
   public void setWest(boolean westVal) {
      
      if (westVal) {
         westWallBool = true;
         westBit = 1;
      }
      else if (!westVal) {
         westWallBool = false;
         westBit = 0;
      }
      
      //return;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Return whether this cell's east wall exists.
    * @return true if and only if the east wall exists
    */
   public boolean hasEast() {
      return eastWallBool;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Indicate whether this cell's east wall should exist.
    * @param eastVal  true if wall should exist; false otherwise
    */
   public void setEast(boolean eastVal) {
      
      if (eastVal) {
         eastWallBool = true;
         eastBit = 1;
      }
      else if (!eastVal) {
         eastWallBool = false;
         eastBit = 0;
      }
      
      //return;  //TO DO: REPLACE THIS STUB
   }

   /** 
    * Return the String contents of this cell.
    * @return the data value
    */
   public String getData() {
      return data;  //TO DO: REPLACE THIS STUB
   }

   /** 
    * Set the String contents of this cell.
    * @param contents the cell's new data
    * @return the original contents
    */
   public String setData(String contents) {
      String prevData = this.data;
      
      this.data = contents;
      
      return prevData;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Get the cell number in the grid (row x column order).
    * @return the number
    */
   public int getNumber() {
      return num;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Check if two cells are the same in a grid, based on number.
    * @param other the other cell to compare to this
    * @return true if same, false otherwise.
    */
   public boolean equals(Cell other) {
         
      return (this.num == other.num);
   }
   
   /**
    * Check if an Object is same as this cell, based on number. If
    * the Object is not a cell, then return false.
    * THIS METHOD IS COMPLETE; STUDENT SHOULD NOT MODIFY IT.
    * @param other the other cell to compare to this
    * @return true if same, false otherwise.
    */
   public boolean equals(Object other) {
      if (!(other instanceof Cell)) {
         return false;
      }
      // now other must be a cell, so we can call the other equals method
      return this.equals((Cell) other);
   }

}
