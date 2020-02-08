/*
 * Six, face-down, numbered tiles are selected from twenty-four shuffled tiles.
 
 * The tiles are arranged into two groups: Large Numbers and Small Numbers.
 
 * There are four numbers in the large set { 25 , 50 , 75 , 100 }
 
 * There are twenty numbers in the small set, two each of the numbers 1-10
    {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10}
 
 * One contestant selects as many numbers as desired (unseen) from the 
    large set (between none and all four), and the balance are
    pulled from the small set to make six numbers in total.

 * A random three-digit target number is then chosen by a computer*.

 * The contestants are given 30 seconds to get as close as possible
    to the chosen target by using just the four basic arithmetic 
    operators + - × ÷

 * Not all the digits need to be used.

 * Concatenation of the digits is not allowed 
    (You can’t use a “2” and “2” to make “22”).

 * At no intermediate step in the process can the current running total
    become negative or involve a fraction.

 * Each numbered tile can only be used once in the calculation.

 * 10 points are awarded for correctly getting the exact solution.

 * 7 points are awarded for getting within 5 of the required solution.

 * 5 points are awarded for getting within 10 points of the required solution.
*/

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class CountdownNumbers {
   private final static int nbrsToGenerate = 6;
   private final static Random random = new Random();
   private final static int[] smallNumbers = new int[] { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10 };
   private final static int[] largeNumbers = new int[] { 25, 50, 75, 100 };
   private final static char[] operators = new char[] { '+', '-', '/', '*' };

   public static void main(final String[] args) {
      final int[] nbrs = chooseNumbers(2);
      System.out.println(Arrays.toString(nbrs));
   }

   public static int[] chooseNumbers(final int nbOfLargeNbrs) {
      final int[] chosenNumbers = new int[6]; // allocate memory for 6 integers
      addUniqueNbrs(chosenNumbers, largeNumbers, nbOfLargeNbrs);
      // The rest of the numbers (min 2, max 6) are going to be small numbers
      addUniqueNbrs(chosenNumbers, smallNumbers, nbrsToGenerate - nbOfLargeNbrs);
      return chosenNumbers;
   }

   // This function makes sure that we chose unique indexes from the index range of
   // the passed chooseFrom array since we don't want that a number at a given
   // index is chosen twice
   private static int[] addUniqueNbrs(final int[] nbrsChosen, final int[] chooseFrom, final int hmToChoose) {
      int gen;
      boolean unique;
      // Keep track of which indexes have already been generated
      final List<Integer> alreadyGenerated = new ArrayList<Integer>();

      for(int i = 0; i < hmToChoose; i++){
         unique = false;
         while (!unique){
            gen = random.nextInt(chooseFrom.length);
            if (!alreadyGenerated.contains(gen)){
               alreadyGenerated.add(gen);
               nbrsChosen[i] = chooseFrom[gen];
               unique = true;
            }
         }
      }
      return nbrsChosen;
   }
}