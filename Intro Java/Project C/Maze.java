import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implement a basic maze.
 * Intersession 2020
 * @author Bailey Finkelberg
 * JHED: bfinkel3
 * Date: 1/20/20
 */
public class Maze {

   /** Default maze size (rows and columns), set to 8. */
   private static final int SIZE = 8;

   /** The grid of cells. */
   private Cell[][] grid;

   /** How many rows are in the maze. */
   private int rows;

   /** How many columns are in the maze. */
   private int cols;


   /** Create the internal structure of a maze of a default size. */
   public Maze() {
      this.rows = SIZE;
      this.cols = SIZE;
      this.init(rows, cols);
      //TO DO: REPLACE THIS STUB
   }

   /**
    * Create the internal structure a maze of a specified size. 
    * @param r the desired number of rows in the maze
    * @param c the desired number of columns in the maze 
    */
   public Maze(int r, int c) {
      this.rows = r;
      this.cols = c;
      this.init(rows, cols);
      //TO DO: REPLACE THIS STUB
   }


   /** 
    * Initialize the internal structure for a maze of a specific size.
    * This is a helper method which is called from all class constructors. 
    * @param r the number of rows
    * @param c the number of columns 
    */
   private void init(int r, int c) {
      grid = new Cell[r][c];     //creates maze with default Cells
      
      //return; //TO DO: REPLACE THIS STUB
   }


   /**
    * Create and return one (long) string that
    * contains the row and column dimensions of the maze, then a
    * newline, followed by the string representation of each cell,
    * one row at a time, with each cell separated from the next with
    * one space and each row separated from the next by a newline
    * ('\n').
    * @return the string representation
    */
   public String toString() {
      return "not yet implemented";  //TO DO: REPLACE THIS STUB
   }

   /**
    * Read a maze from a plain text file whose name is supplied as
    * a parameter to this method, and validate the mazes's wall structure.
    * This method assumes the specified file exists.
    * The first line in the text file must contain the number of rows and
    * columns, respectively. Each subsequent line provides the wall
    * information for the cells in a single row, using a 4-character 
    * string ("bit string") in NESW (north-east-south-west) order for
    * each cell. A 1 "bit" indicates the wall exists, a 0 "bit" (or any
    * character other than 1) means no wall.
    * @param s is the external name of the file to read
    * @return true if a valid maze is created, false otherwise
    * @throws IOException if file is not well-formatted
    */
   public boolean readMaze(String s) throws IOException {
      
      FileReader inReader = new FileReader(s);   //open file
      Scanner inFS = new Scanner(inReader);
      
      rows = inFS.nextInt();
      cols = inFS.nextInt();
      
      grid = new Cell[rows][cols];
      
      String[][] gridString = new String[rows][cols];
      
      boolean north = false;
      boolean east = false;
      boolean south = false;
      boolean west = false;
      
      for (int i = 0; i < rows; ++i) {
         for (int j = 0; j < cols; ++j) {
            gridString[i][j] = inFS.next();
         }
         
      }
      
      for (int i = 0; i < rows; ++i) {
         for (int j = 0; j < cols; ++j) {
            
            String curr = gridString[i][j];
            
            if (curr.charAt(0) == '1') {
               north = true;
            }
            else {
               north = false;
            }
         
            if (curr.charAt(1) == '1') {
               east = true;
            }
            else {
               east = false;
            }
         
            if (curr.charAt(2) == '1') {
               south = true;
            }
            else {
               south = false;
            }
         
         
            if (curr.charAt(3) == '1') {
               west = true;
            }
            else {
               west = false;
            }
            
            grid[i][j] = new Cell(north, east, south, west);
         
         
         } 
      }
      
      return validate();
        
        //TO DO: REPLACE THIS STUB
   }

   /**
    * Validate the cells of a maze as being consistent with respect
    * to neighboring internal walls. For example, suppose some cell
    * C has an east wall. Then for the maze to be valid, the cell
    * to C's east must have a west wall. (This method does not consider
    * external walls.) This method does not check for solvability
    * of the maze.
    * @return true if valid, false otherwise
    */
   public boolean validate() {
      boolean valid = true;
      
      
      
      for (int i = 0; i < rows; ++i) {
         for (int j = 0; j < cols; ++j) {
            
            if (grid[i][j].hasNorth() && grid[i][j].getNumber() > cols) {
               if (!grid[i - 1][j].hasSouth()) {
                  valid = false;
                  return valid;
               }
            }
            
            if (grid[i][j].hasSouth() 
               && grid[i][j].getNumber() + cols < rows * cols) {
               if (!grid[i + 1][j].hasNorth()) {
                  valid = false;
                  return valid;
               }
            }
            
            if (grid[i][j].hasEast() 
               && !((grid[i][j].getNumber() + 1) / (i + 1) == cols)) {
               if (!grid[i][j + 1].hasWest()) {
                  valid = false;
                  return valid;
               }
            }
            
            if ((grid[i][j].hasWest()) 
               && !((grid[i][j].getNumber() + cols) / (i + 1) == cols)) {
               if (!grid[i][j - 1].hasEast()) {
                  valid = false;
                  return valid;
               }
            }
            
         }
         
         
         return valid;
         
      }
      
      
      
      return valid;  //TO DO: REPLACE THIS STUB
   }


   /**
    * Return the Cell object stored at the given (row, column) position.
    * This method assumes its arguments describe a legal position.
    * @param r the row position of the Cell in the Maze object
    * @param c the col position of the Cell in the Maze object
    * @return the Cell object that is at the specified position
    */
   public Cell getCellAt(int r, int c) {
      
      return grid[r][c];  //TO DO: REPLACE THIS STUB
   }


   /**
    * Set the contents of a Cell in a given (row, column) position.
    * This method assumes its arguments describe a legal position.
    * @param r the row position of the Cell in the Maze object
    * @param c the col position of the Cell in the Maze object
    * @param d the data String to store at the specified position
    * @return the former contents of the cell
    */
   public String setCellAt(int r, int c, String d) {
      
      return grid[r][c].setData(d);  //TO DO: REPLACE THIS STUB
   }


   /**
    * Return the number of rows in the maze.
    * @return the number of rows in the maze
    */
   public int getNumRows() {
      return this.rows;  //TO DO: REPLACE THIS STUB
   }


   /**
    * Return the number of columns in the maze.
    * @return the number of columns in the maze
    */
   public int getNumCols() {
      return this.cols;  //TO DO: REPLACE THIS STUB
   }

   /**
    * Solve the maze, assuming start in top left cell and finish
    * in bottom right cell. This method changes data values inside
    * explored cells, so that cells which are determined to be part
    * of the final path ("the solution") through the maze will now
    * contain the string P as their data, while cells which 
    * were explored but not selected as part of the solution path 
    * will now contain x as their data. If no complete solution path
    * in the maze exists, no cells' data will be permanently changed
    * to P, but many may now have x. 
    * @return true if solved, false if fails
    */
   public boolean solve() {
      
      return solve(0, 0, rows - 1, cols - 1);  //TO DO: REPLACE THIS STUB
   }
   
   /**
    * Solve the maze from a given starting point to ending cell.
    * This method changes data inside explored cells,
    * so that cells which are part of the final path through the
    * maze contain P as their data, while cells which were explored
    * but not selected as part of the solution path contain x as
    * their data. If no complete solution path in the maze exists, 
    * no cells' data will be permanently changed to P, but many may
    * now have x. 
    * @param srow the start row index
    * @param scol the start col index
    * @param erow the end row index
    * @param ecol the end col index
    * @return true if solved, false otherwise
    */
   public boolean solve(int srow, int scol, int erow, int ecol) {
   
      Cell currentCell = grid[srow][scol];
      
      if (currentCell.getData().equals("P") 
         || currentCell.getData().equals("x")) {
         return false;
      }
      
      currentCell.setData("P");
      
      
      
      if (currentCell.getNumber() == grid[erow][ecol].getNumber()) {
         return true;
      }
      else if (!currentCell.hasNorth()) {
         
         return solve(srow - 1, scol, erow, ecol);
      }
      else if (!currentCell.hasEast()) {
         
         return solve(srow, scol + 1, erow, ecol);
      }
      else if (!currentCell.hasSouth()) {
         
         return solve(srow + 1, scol, erow, ecol);
      }
      else if (!currentCell.hasWest()) {
         
         return solve(srow, scol - 1, erow, ecol);
      }
   
      
      currentCell.setData("x");
      
      return false;  //TO DO: REPLACE THIS STUB
   }

}