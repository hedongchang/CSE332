package writeupExperiment;

import main.Sorter;
import phaseA.StringComparator;
import phaseB.*;
import providedCode.DataCount;
import providedCode.DataCountStringComparator;
import providedCode.DataCounter;

public class TopKExperiment {
	
	public static final int NUM_TESTS = 16;
	public static final int NUM_WARMUP = 3;
	public static final String FILE = "hamlet.txt";
	
	public static void main(String[] args) {
		DataCounter<String> counter = new HashTable<String>(new StringComparator(),
				new StringHasher());
	    WordCount.countWords(FILE, counter);
	    DataCount<String>[] array = WordCount.getCountsArray(counter);
	    for (int i = 1; i <= array.length; i = i + 50) {

			System.out.println("top " + i + ": " + getAverageRuntime(new HashTable<String>
			(new StringComparator(), new StringHasher()), i));
		}
		System.out.println("\nmerge sort: " + getAverageRuntime(new HashTable<String>
		(new StringComparator(), new StringHasher()), -1));
	}
	
	
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
