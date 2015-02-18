/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */

package writeupExperiment;

/**
 * Compute runtime of every combination of DataCounter and sorting algorithms
 */
public class Experiment {
	
	public static final int NUM_TESTS = 16;
	// the number of tests
	public static final int NUM_WARMUP = 3;
	// the number of warm-up tests
	public static final String FILE = "hamlet.txt";
	// file name of the input
	
	public static void main(String[] args) {	
		// Get average run time of binary search tree with all sorting algorithms
		System.out.println("Binary Search Tree");
		System.out.println("insertion sort: " + getAverageRuntime(new String[] {"-b", "-is", FILE}));
		System.out.println("heap sort: " + getAverageRuntime(new String[] {"-b", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-b", "-os", FILE}) + "\n");
		
		// Get average run time of AVL tree list with all sorting algorithms
		System.out.println("AVL Tree");
		System.out.println("insertion sort: " + getAverageRuntime(new String[] {"-a", "-is", FILE}));
		System.out.println("heap sort: " + 	getAverageRuntime(new String[] {"-a", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-a", "-os", FILE}) + "\n");
		
		// Get average run time of move to front list with all sorting algorithms
		System.out.println("MoveToFront List");
		System.out.println("insertion sort: " + 
		getAverageRuntime(new String[] {"-m", "-is", FILE}));
		System.out.println("heap sort: " + getAverageRuntime(new String[] {"-m", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-m", "-os", FILE}) + "\n");
		
		// Get average run time of hash table with original string hasher and all sorting algorithms
		System.out.println("Hash Table");
		System.out.println("insertion sort: " + getAverageRuntime(new String[] {"-h", "-is", FILE}));
		System.out.println("heap sort: " + getAverageRuntime(new String[] {"-h", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-h", "-os", FILE}) + "\n");		
		
		// Get average run time of hash table with alternative string hasher and all sorting algorithms
		System.out.println("Hash Table with NewStringHasher");
		System.out.println("insertion sort: " + getAverageRuntime(new String[] {"-ns", "-is", FILE}));
		System.out.println("heap sort: " + getAverageRuntime(new String[] {"-ns", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-ns", "-os", FILE}) + "\n");
		
		// Get average run time of hash table with alternative string hasher and all sorting algorithms
		System.out.println("Hash Table with NewStringHasher2");
		System.out.println("insertion sort: " + getAverageRuntime(new String[] {"-ns2", "-is", FILE}));
		System.out.println("heap sort: " + getAverageRuntime(new String[] {"-ns2", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-ns2", "-os", FILE}) + "\n");
	}
	
	/**
	 * Get average run time of a word count
	 * @param args
	 * @return the average run time of a word count
	 */
	public static double getAverageRuntime(String[] args) {
	    double totalTime = 0;
	    for (int i=0; i< NUM_TESTS; i++) {
	      long startTime = System.currentTimeMillis();
	      WordCount.main(args);
	      long endTime = System.currentTimeMillis();
	      if (NUM_WARMUP <= i) {                    // Throw away first NUM_WARMUP runs to exclude JVM warmup
	        totalTime += (endTime - startTime);
	      }
	    }
	    return totalTime / (NUM_TESTS - NUM_WARMUP);  // Return average runtime.
	  }
}
