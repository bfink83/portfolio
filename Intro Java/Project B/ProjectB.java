import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;

/** Project B, EN.500.132 Bootcamp: Java, Intersession 2020.
* This program implements a basic perceptron.
* @author Bailey Finkelberg
* JHED: bfinkel3
* Date: 1/15/20
*/

public class ProjectB {

   //Declare matrix operation methods
   
   /**Method to Sum two same-length arrays.
   * @param firstArray first array to be summed
   * @param secondArray second array to be summed
   * @return arraySum summed array
   */
   public static double[] arraySum(double[] firstArray, double[] secondArray) {
   
      double[] arraySum = new double[firstArray.length]; //initialize array
      
      for (int i = 0; i < firstArray.length; ++i) {
         arraySum[i] = firstArray[i] + secondArray[i];
      }
   
      return arraySum;
   }
   
   /**Method to take the Dot product of two same-length arrays.
   * @param firstArray first array to be dotted
   * @param secondArray second array to be dotted
   * @return dotProd double dot product of the two arrays
   */
   public static double arrayDot(double[] firstArray, double[] secondArray) {
      
      double dotProd = 0;  //initialize double
      
      for (int i = 0; i < firstArray.length; ++i) {
         dotProd = dotProd + (firstArray[i] * secondArray[i]);
      }
      
      return dotProd;
   }
   
   /**Method to multiply an array by a constant.
   * @param firstArray array to be multiplied
   * @param givenConstant constant to multiply the array by
   * @return arrayMult multiplied array
   */
   public static double[] arrayMult(double[] firstArray, double givenConstant) {
   
      double[] arrayMult = new double[firstArray.length];   //initialize array
      
      for (int i = 0; i < firstArray.length; ++i) {
         arrayMult[i] = firstArray[i] * givenConstant;
      }
      
      return arrayMult;
   }
   
   //Create arrays X,Y,Z
   
   /**Method to create an NxM 2D array containing the feature values
   * from "training.txt".
   * @param fileName string literal of name of file
   * @param length file length
   * @return arrayX array containing the feature values
   * @throws IOException FileNotFoundException if file is not in same folder
   */
   public static double[][] getFeatValsArray(String fileName, int length)
      throws IOException {
   
      double[][] arrayX = new double[length][4];
      
      FileInputStream inStream = new FileInputStream(fileName);   //open file
      Scanner inFS = new Scanner(inStream);  //create scanner object
      
      for (int i = 0; i < length; ++i) {  //iterates thru each col then-
         for (int j = 0; j < 4; ++j) {    //moves to next row
            arrayX[i][j] = inFS.nextDouble(); 
         }
         
         int authRes = inFS.nextInt(); //scan past result after-
                                       //features are scanned
      }
      
      inStream.close(); //close file   
      
      return arrayX;
   }
   
   /**Method to create an array of length N containing authenticity results
   * from "training.txt".
   * @param fileName string literal of name of file
   * @param length file length
   * @return arrayY array containing authenticity results
   * @throws IOException FileNotFoundException if file is not in same folder
   */
   public static int[] getAuthResArray(String fileName, int length)
      throws IOException {
      
      int[] arrayY = new int[length];
      
      FileInputStream inStream = new FileInputStream(fileName);
      Scanner inFS = new Scanner(inStream);
      
      for (int i = 0; i < length; ++i) {
         double feature = inFS.nextDouble();  
         feature = inFS.nextDouble();  //scan past features to get to result
         feature = inFS.nextDouble();
         feature = inFS.nextDouble();
         
         arrayY[i] = inFS.nextInt();   //auth res is only int per line
         
      }
      
      inStream.close(); //close file
      
      return arrayY;
      
   }
   
   /**Method to convert given classifications (0,1) to new class. (-1,1).
   * Separate from getAuthResArray method b/c predict.txt is already converted
   * @param arrayY 1D array with results to be converted
   * @return arrayY converted results array
   */
   public static int[] convertResArray(int[] arrayY) {
      
      for (int i = 0; i < arrayY.length; ++i) { 
      
         if (arrayY[i] == 0) {   //converts each class. to new class.
            arrayY[i] = 1;
         }
         else if (arrayY[i] == 1) {
            arrayY[i] = -1;
         }
      }
      
      return arrayY;
   }

   
   /**Method to determine weights vector using perceptron algorithm.
   * Outputs each iteration to "weights.txt".
   * @param arrayX 2D array with feature values from training
   * @param arrayY 1D array with authenticity results from training
   * @param fileName string literal of name of file to output to
   * @return arrayW array containing final value of weights
   * @throws IOException FileNotFoundException if file is not in same folder 
   */
   public static double[] getWeightVector(double[][] arrayX, int[] arrayY, 
      String fileName) throws IOException {
      
      double[] arrayW = {0.0, 0.0, 0.0, 0.0};   //initialize weight vector
      int classify = 0;
      
      FileOutputStream outStream = new FileOutputStream(fileName);   //open file
      PrintWriter outFS = new PrintWriter(outStream); //create PrintWriter obj
      
      for (int i = 0; i < arrayX.length; ++i) {
         
         double dotProduct = arrayDot(arrayW, arrayX[i]);
         
         if (dotProduct >= 0) {
            classify = 1;
         }
         else if (arrayDot(arrayW, arrayX[i]) < 0) {
            classify = -1;
         }
         
         if (classify != arrayY[i]) { //increment weight if result doesn't match
            arrayW = arraySum(arrayW, arrayMult(arrayX[i], arrayY[i]));
         }
         
         for (int j = 0; j < 4; ++j) {    //output to file
            outFS.printf("%.5f ", arrayW[j]);
         }
         
         outFS.println("");
         
         outFS.flush();    //flush so PrintWriter can output all values
      }
      
      outStream.close();
      
      return arrayW;
   
   }
   
   /**Method to predict the class of the data in "validate.txt".
   * Outputs results and feature data to new file, "predict.txt"
   * @param arrayW weight vector returned from getWeightVector method
   * @param arrayX feature values array "X" returned after running
   * @param fileName string literal of name of file to output to
   * @throws IOException FileNotFoundException if file is not in same folder
   * getFeatureValuesArray() method with "validate.txt"
   */
   public static void predictClass(double[] arrayW, double[][] arrayX, 
      String fileName) throws IOException {
      
      int classify = 0;
      
      FileOutputStream outStream = new FileOutputStream(fileName);
      PrintWriter outFS = new PrintWriter(outStream);
      
      for (int i = 0; i < arrayX.length; ++i) {    //predict results
         if (arrayDot(arrayW, arrayX[i]) >= 0) {   //using perceptron algorithm
            classify = 1;
         }
         else if (arrayDot(arrayW, arrayX[i]) < 0) {
            classify = -1;
         }
         
         for (int j = 0; j < 4; j++) {
            outFS.print(arrayX[i][j] + " ");
         }
         
         outFS.println(classify + "");
         
         outFS.flush();
      }
      
      outStream.close();
   
   }
   
   /**Method to compare "predict.txt" and "validate.txt" authenticity results.
   * Prints number of correct and incorrect predictions.
   * @param givenResults array of given results, returned 
   * from getAuthResultsArray() method using "validate.txt"
   * @param predResults array of predicted results, returned from
   * getAuthResultsArray() using "predict.txt"
   */
   public static void compResults(int[] givenResults, int[] predResults) {
      int numCorrect = 0;
      int numIncorrect = 0;
      double percentCor = 0.0;
      
      for (int i = 0; i < givenResults.length; ++i) {
         
         if (givenResults[i] == predResults[i]) { //compare predicted and 
            numCorrect = numCorrect + 1;          //given results and
         }                                        //increments corresponding val
         else if (givenResults[i] != predResults[i]) {
            numIncorrect = numIncorrect + 1;
         } 
      }
      
      percentCor = ((double) numCorrect / (double) givenResults.length) * 100;
      
      
      //print success rate
      System.out.println("Correct Predictions: " + numCorrect);   
      System.out.println("Incorrect Predictions: " + numIncorrect);  
      System.out.println("Percent Correct: %" + percentCor);
   }
   
   /** Main method.
   * @param args not used
   * @throws IOException FileNotFoundException if file is not in same folder
   */
   public static void main(String[] args) throws IOException {
      //initialize constants (final)
      final int trainLength = 1048;
      final int validLength = 324;
      final int predictLength = 324;
      
      final String trainName = "training.txt";
      final String validName = "validate.txt";
      final String weightName = "weights.txt";
      final String predictName = "predict.txt";
           
      int[] arrayY = getAuthResArray(trainName, trainLength);
      arrayY = convertResArray(arrayY);
      
      int[] validateArrayY = getAuthResArray(validName, validLength);
      validateArrayY = convertResArray(validateArrayY);
      
      double[][] arrayX = getFeatValsArray(trainName, trainLength);
      
      double[][] validateArrayX = getFeatValsArray(validName, validLength);
      
      double[] weightVector = getWeightVector(arrayX, arrayY, weightName);
      
      predictClass(weightVector, validateArrayX, predictName);
      
      int[] predictArrayY = getAuthResArray(predictName, predictLength);
      
      compResults(validateArrayY, predictArrayY);
   }

}