package phaseB;

import java.io.IOException;

import providedCode.*;
import phaseA.*;


/**
 * TODO: REPLACE this comment with your own as appropriate.
 * 
 * This should be done using a *SINGLE* iterator. This means only 1
 * iterator being used in Correlator.java, *NOT* 1 iterator per
 * DataCounter (You should call dataCounter.getIterator() just once in
 * Correlator.java).
 * Hint: Take advantage of DataCounter's method.
 * 
 * Although you can share argument processing code with WordCount, it
 * will be easier to copy & paste it from WordCount and modify it here
 * - it is up to you. Since WordCount does not have states, making
 * private method public to share with Correlator is OK. In general,
 * you are not forbidden to make private method public, just make sure
 * it does not violate style guidelines.
 * 
 * Make sure WordCount and Correlator do not print anything other than
 * what they are supposed to print (e.g. do not print timing info, input size). To
 * avoid this, copy these files into package writeupExperiment and
 * change it there as needed for your write-up experiments. 
 */
public class Correlator {
	
	private static final double UPPER = 0.01;
	private static final double LOWER = 0.0001;
	
    public static void main(String[] args) {
    	double variance = 0.0;        
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
    	
    	
    	int length1 = countFrequency("hamlet.txt", counter1);
    	int length2 = countFrequency("the-new-atlantis.txt", counter2);
    	
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
