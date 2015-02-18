// CSE 332 Project 2 Phase B
// 2/17/2015
// DONGCHANG HE & JUAN CAI
package phaseB;

import java.io.IOException;

import providedCode.*;
import phaseA.*;


/**
 * This program can compare the two files to see how many overlaps are in these two files. 
 * @author caijuan & dongchang he
 */
public class Correlator {
	
	// If the frequency of the word exceeds UPPER, we ignore it.
	private static final double UPPER = 0.01;
	// If the frequency of the word is less than LOWER, we ignore it.
	private static final double LOWER = 0.0001;
	
    public static void main(String[] args) {
    	if (args.length != 3) {
    		System.err.println("Incorrect number of arguments");
        	System.exit(1);
    	}
    	double variance = 0.0;        
    	String file1 = args[1];
    	String file2 = args[2];
    	DataCounter<String> counter1 = null;
    	DataCounter<String> counter2 = null;
    	String dataType = args[0];
    	
    	if (dataType.equals("-b")) {
    		counter1 = new BinarySearchTree<String>(new StringComparator());
    		counter2 = new BinarySearchTree<String>(new StringComparator());
    	} else if (dataType.equals("-a")) {
    		counter1 = new AVLTree<String>(new StringComparator());
    		counter2 = new AVLTree<String>(new StringComparator());
    	} else if (dataType.equals("-m")) {
    		counter1 = new MoveToFrontList<String>(new StringComparator());
    		counter2 = new MoveToFrontList<String>(new StringComparator());
    	} else if (dataType.equals("-h")) {
    		counter1 = new HashTable<String>(new StringComparator(), new StringHasher());
    		counter2 = new HashTable<String>(new StringComparator(), new StringHasher());
    	}
    	
    	
    	int length1 = countFrequency(file1, counter1);
    	int length2 = countFrequency(file2, counter2);
    	
    	SimpleIterator<DataCount<String>> itr = counter1.getIterator();
    	while (itr.hasNext()) {
    		String word = itr.next().data;
    		int count1 = counter1.getCount(word);
    		int count2 = counter2.getCount(word);
    		if (count1 > 0 && count2 > 0) {
    			double frequency1 = (double) count1 / length1;
    			double frequency2 = (double) count2 / length2;
    			if (frequency1 >= LOWER && frequency1 <= UPPER &&
    					frequency2 >= LOWER && frequency2 <= UPPER) {
	    			double diff = frequency1 - frequency2;
	    			variance = variance + Math.pow(diff, 2);
    			}
    		}
    	}
    	
    	System.out.println(variance);
    }
    
    /**
     * Counts the frequency of the word in a given file. 
     * @param fileName: the name of the file
     * @param counter
     * @return the count number of the word in that file.
     */
    private static int countFrequency(String fileName, DataCounter<String> counter) {
		int count = 0;
    	try {
			FileWordReader input = new FileWordReader(fileName);
			String word = input.nextWord();
			while (word != null) {
				counter.incCount(word);
				word = input.nextWord();
				count++;
			}
		} catch (IOException e) {
			System.err.println("Cannot process " + fileName + " " + e);
            System.exit(1);
		}
    	return count;
    }
}