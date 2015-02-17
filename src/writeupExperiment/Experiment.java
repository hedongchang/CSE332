package writeupExperiment;


public class Experiment {
	
	public static final int NUM_TESTS = 16;
	public static final int NUM_WARMUP = 3;
	public static final String FILE = "hamlet.txt";
	
	public static void main(String[] args) {
		System.out.println("Binary Search Tree");
		System.out.println("insertion sort: " + getAverageRuntime(new String[] {"-b", "-is", FILE}));
		System.out.println("heap sort: " + getAverageRuntime(new String[] {"-b", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-b", "-os", FILE}) + "\n");
		
		System.out.println("AVL Tree");
		System.out.println("insertion sort: " + getAverageRuntime(new String[] {"-a", "-is", FILE}));
		System.out.println("heap sort: " + 	getAverageRuntime(new String[] {"-a", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-a", "-os", FILE}) + "\n");
		
		System.out.println("MoveToFront List");
		System.out.println("insertion sort: " + 
		getAverageRuntime(new String[] {"-m", "-is", FILE}));
		System.out.println("heap sort: " + getAverageRuntime(new String[] {"-m", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-m", "-os", FILE}) + "\n");
		
		System.out.println("Hash Table");
		System.out.println("insertion sort: " + getAverageRuntime(new String[] {"-h", "-is", FILE}));
		System.out.println("heap sort: " + getAverageRuntime(new String[] {"-h", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-h", "-os", FILE}) + "\n");		
		
		System.out.println("Hash Table with NewStringHasher");
		System.out.println("insertion sort: " + getAverageRuntime(new String[] {"-ns", "-is", FILE}));
		System.out.println("heap sort: " + getAverageRuntime(new String[] {"-ns", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-ns", "-os", FILE}) + "\n");
		
		System.out.println("Hash Table with NewStringHasher2");
		System.out.println("insertion sort: " + getAverageRuntime(new String[] {"-ns2", "-is", FILE}));
		System.out.println("heap sort: " + getAverageRuntime(new String[] {"-ns2", "-hs", FILE}));
		System.out.println("other sort: " + getAverageRuntime(new String[] {"-ns2", "-os", FILE}) + "\n");
	}
	
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
