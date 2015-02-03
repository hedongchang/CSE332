package main;
import java.io.IOException;

import phaseA.*;
import providedCode.*;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {

	
	// TODO: Replace this comment with your own as appropriate.
	// You may modify this method if you want.
    private static void countWords(String file, DataCounter<String> counter) {
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
    
    
    // TODO: Replace this comment with your own as appropriate.
    // Implement a method that returns an array of DataCount objects
    // containing each unique word.  If generics confuse you, write
    // non-generic version first and then adjust it.
 	private static <E> DataCount<E>[] getCountsArray(DataCounter<E> counter) {
 		@SuppressWarnings("unchecked")
		DataCount<E>[] array = (DataCount<E>[]) new DataCount[counter.getSize()];
 		SimpleIterator<DataCount<E>> itr = counter.getIterator();
 		for (int i = 0; i < counter.getSize(); i++) {
 			array[i] = itr.next();
 		}
 		return array;
 	}
    
 	
    // IMPORTANT: Used for grading. Do not modify the printing *format*!
    // Otherwise you may modify this method (its signature, or internals) 
    // if you want.
    private static void printDataCount(DataCount<String>[] counts) {
    	for (DataCount<String> c : counts) {
            System.out.println(c.count + "\t" + c.data);
        }
    }
    
    
    /** 
     *  TODO: Replace this comment with your own as appropriate.  Edit
 	 *  this method (including replacing the dummy parameter
 	 *  checking below) to process all parameters as shown in the
 	 *  spec.
 	 */
    public static void main(String[] args) {
        if (args.length != 3) {
        	System.err.println("Incorrect number of arguments");
        	System.exit(1);
        }
        String dataType = args[0];
        
        DataCounter<String> data = null;
        if (dataType.equals("-b")) {
        	data = new BinarySearchTree<String>(new StringComparator());
        } else if (dataType.equals("-a")) {
        	data = new AVLTree<String>(new StringComparator());
        } else if (dataType.equals("-m")) {
        	data = new MoveToFrontList<String>(new StringComparator());
        } else {
        	System.err.println("To be implemented");
        	System.exit(1);
        }
        countWords(args[args.length - 1], data);
        DataCount<String>[] arr = getCountsArray(data);
        if (args[1].equals("-is")) {
        	Sorter.insertionSort(arr, new DataCountStringComparator());
        } else if (args[1].equals("-hs")) {
        	Sorter.heapSort(arr, new DataCountStringComparator());
        } else {
        	System.err.println("To be implemented");
        	System.exit(1);
        }
        printDataCount(arr);
    }
}
