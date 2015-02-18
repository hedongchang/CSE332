/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */
package writeupExperiment;
import java.io.IOException;

import main.Sorter;
import phaseA.*;
import phaseB.DataCountTopKComparator;
import phaseB.HashTable;
import phaseB.StringHasher;
import providedCode.*;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {

	
	/** Count words from a given file and put the result into a list.
	 * @param file: the file need to count words.
	 * @param counter: the list that contains the words with their count number.
	 *  @effects count words and put the words and their count number into a list.
	 */
    public static void countWords(String file, DataCounter<String> counter) {
        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        }catch (IOException e) {
            System.err.println("Error processing " + file + " " + e);
            System.exit(1);
        }
    }
    
    
    /** Return the current words list.
     * @param counter: the current words list.
     * @effects Iterate over the words list and return it. 
     * @return a list with unique words.
     */
 	public static <E> DataCount<E>[] getCountsArray(DataCounter<E> counter) {
 		@SuppressWarnings("unchecked")
		DataCount<E>[] array = (DataCount<E>[]) new DataCount[counter.getSize()];
 		SimpleIterator<DataCount<E>> itr = counter.getIterator();
 		for (int i = 0; i < counter.getSize(); i++) {
 			array[i] = itr.next();
 		}
 		return array;
 	}    
    
    /** 
     *  Reads users' inputs, create a data type to store the word counts,
     *  and implement a sorting algorithm to sort the word counts accordingly.
 	 */
    public static void main(String[] args) {
    	// check whether the input is valid
        if (args.length != 3 && args.length != 4) {
        	System.err.println("Incorrect number of arguments");
        	System.exit(1);
        }
        if (args.length == 4 && !args[1].equals("-k")) {
        	System.err.println("Only top k sort can have 4 arguments");
        	System.exit(1);
        }
        // determine the data type to be used
        String dataType = args[0];      
        DataCounter<String> data = null;
        if (dataType.equals("-b")) {
        	data = new BinarySearchTree<String>(new StringComparator());
        } else if (dataType.equals("-a")) {
        	data = new AVLTree<String>(new StringComparator());
        } else if (dataType.equals("-m")) {
        	data = new MoveToFrontList<String>(new StringComparator());
        } else if (dataType.equals("-h")) {
        	data = new HashTable<String>(new StringComparator(), new StringHasher());
        } else if (dataType.equals("-ns")) {
        	data = new HashTable<String>(new StringComparator(), new NewStringHasher());
        } else if (dataType.equals("-ns2")) {
        	data = new HashTable<String>(new StringComparator(), new NewStringHasher2());
        } else {
        	System.err.println("To be implemented");
        	System.exit(1);
        }
        // count the words and store them using the data type chosen above
        countWords(args[args.length - 1], data);
        DataCount<String>[] arr = getCountsArray(data);
        if (args[1].equals("-is")) {
        	Sorter.insertionSort(arr, new DataCountStringComparator());
        } else if (args[1].equals("-hs")) {
        	Sorter.heapSort(arr, new DataCountStringComparator());
        } else if (args[1].equals("-os")) {
        	Sorter.otherSort(arr, new DataCountStringComparator());
        } else if (args[1].equals("-k")) {
        	int k = Integer.parseInt(args[2]);
        	Sorter.topKSort(arr, new DataCountTopKComparator(), k);
        } else {
        	System.err.println("Illegal sorter name");
        	System.exit(1);
        }
    }
}
