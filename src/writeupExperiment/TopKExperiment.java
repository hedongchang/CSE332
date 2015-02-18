/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */
package writeupExperiment;

import main.Sorter;
import phaseA.StringComparator;
import phaseB.*;
import providedCode.DataCount;
import providedCode.DataCountStringComparator;
import providedCode.DataCounter;

/**
 * Compute runtime of top k sorts and merge sort with different values of k
 */
public class TopKExperiment {
	
	public static final int NUM_TESTS = 16;
	// the number of tests
	public static final int NUM_WARMUP = 3;
	// the number of warm of tests
	public static final String FILE = "hamlet.txt";
	// the file name of input
	
	public static void main(String[] args) {
		DataCounter<String> counter = new HashTable<String>(new StringComparator(),
				new StringHasher());
	    WordCount.countWords(FILE, counter);
	    DataCount<String>[] array = WordCount.getCountsArray(counter);
	    for (int i = 1; i <= array.length; i = i + 25) {
	    	// compute average time of top k sort with a k value of i
			System.out.println("top " + i + ": " + getAverageRuntime(new HashTable<String>
			(new StringComparator(), new StringHasher()), i));
			
	    	// compute average time of merge sort
			System.out.println("merge sort: " + getAverageRuntime(new HashTable<String>
			(new StringComparator(), new StringHasher()), -1));
		}
	}
	
	/**
	 * Get average run time of a sort
	 * @param counter the DataCounter used in the sort
	 * @param k the number of elements that need to be sorted
	 * @return the average run time of a word count
	 */
	private static double getAverageRuntime(DataCounter<String> counter, int k) {
	    double totalTime = 0;
	    for (int i=0; i< NUM_TESTS; i++) {
	      WordCount.countWords(FILE, counter);
	      DataCount<String>[] array = WordCount.getCountsArray(counter);
	      long startTime = System.currentTimeMillis();
	      if (k == -1) {
	    	  Sorter.otherSort(array, new DataCountStringComparator());
	      } else {
	    	  Sorter.topKSort(array, new DataCountTopKComparator(), k);
	      }
	      long endTime = System.currentTimeMillis();
	      if (NUM_WARMUP <= i) {                    // Throw away first NUM_WARMUP runs to exclude JVM warmup
	        totalTime += (endTime - startTime);
	      }
	    }
	    return totalTime / (NUM_TESTS - NUM_WARMUP);  // Return average runtime.
	  }
}
