// CSE 332 Project 2 Phase B
// 2/17/2015
// DONGCHANG HE & JUAN CAI
package phaseB;
import providedCode.*;

/**
 * This program implements a hash table that can store data. We store data to the front of a list of hash node on a 
 * a particular position of the hash table. We use our hash function to generate the position that we will put data in. 
 * @author dongchang he & caijuan
 */
public class HashTable<E> extends DataCounter<E> {
	
	// PRIMES is a constant, a collection of numbers that indicate the capacity of the hash table. 
	// The capacity of table will be increased to the next capacity number in this collection every
	// time we rehash it. 
	public static final int[] PRIMES = {37, 73, 149, 307, 683, 1279, 2423, 5113, 
		10039, 20393, 50333, 105913, 199961};
	
	// LOAD_FACTOR is a constant indicates the data percentage of a capacity that the capacity can handle. 
	// We will increase the capacity of the table to the next level in PRIMES if the current data percentage
	// is larger than the LOAD_FACTOR. 
	public static final double LOAD_FACTOR = 0.75;
	
	// items: The collection contains all the hash nodes of the hash table. 
	private HashNode[] items;
	// comparator: A comparator that can compare the value of items
	private Comparator<? super E> comparator;
	// hasher: a hash function
	private Hasher<E> hasher;
	// size: the current number of items in table
	private int size;
	// primeCount: the index of PRIMES collection that indicates the current index of capacity of the hash table.
	//             It starts with 0 and cannot exceed the length - 1 of PRIMES. 
	private int primeCount;
	
	/**
	 * HashNode is a node in a position of the hash table and can connect to other nodes that are in that position. 
	 * Each node stores the passed in data and also the count numbers of that data. Every time a new data put in the table,
	 * the count of it will be 1 at the beginning, and will increase later if users put the same data to the table. 
	 * @author caijuan & dongchang he
	 */
	private class HashNode {
		// data: the data stored in the node
		private E data;
		// count: the count numbers of that data
		private int count;
		// next: get the next node that connect to the current node
		private HashNode next;
		
		/**
		 * Constructs a new hash node with given data, count numbers, and put it in the front of the linked list in
		 * its position of the table by connecting it to the given next node.
		 * @param data
		 * @param count
		 * @param next
		 */
		public HashNode(E data, int count, HashNode next) {
			this.data = data;
			this.count = count;
			this.next = next;
		}
	}
	
	
	/**
	 * Constructs a hash table with given comparator and hash function. 
	 * @requires the initial primeCount to be 0. (set the table to the initial capacity) & the size to be 0.
	 * @param c: a comparator
	 * @param h: a hash function
	 */
	@SuppressWarnings("unchecked")
	public HashTable(Comparator<? super E> c, Hasher<E> h) {
		comparator = c;
		hasher = h;
		primeCount = 0;
		items = (HashNode[]) new HashTable.HashNode[PRIMES[primeCount]];
		size = 0;
	}
	
	/**
	 * incCount increments the count of a bucket if it exists, otherwise
	 * it creates a new bucket and adds it to the HashTable. Rehashes the
	 * table if the load factor exceeds .75
	 * @param data is the element that the count will be incremented on
	 * @modifies items
	 * @effects insert a new data to the items table or increase the count value an existed data in items table by one.
	 * @modifies size
	 * @effects increase the size by one
	 */
	@Override
	public void incCount(E data) {
		double loadFactor = (double) size / items.length;
		if (loadFactor > LOAD_FACTOR && primeCount < PRIMES.length) {
			reHash();
		}
		int hash = hasher.hash(data) % items.length;
		HashNode bucket = items[hash];
		while (bucket != null) {
			if (comparator.compare(bucket.data, data) == 0) {
				bucket.count++;
				return;
			}
			bucket = bucket.next;
		}
		items[hash] = new HashNode(data, 1, items[hash]);
		size++;
	}
	
	/**
	 * Rehash the table by increasing the capacity of the table to the next level in PRIMES.
	 * @modifies primeCount
	 * @effects increase the primeCount by one
	 * @modifies items
	 * @effects increase the size of the items to the next capacity level 
	 *          with index of the new primeCount in PRIMES and copy all the previous nodes in the old table
	 *          to the new table.
	 */
	private void reHash() {
		primeCount++;
		int newLength = PRIMES[primeCount];
		@SuppressWarnings("unchecked")
		HashNode[] newItems = (HashNode[]) new HashTable.HashNode[newLength];
		for (int i = 0; i < items.length; i++) {
			HashNode node = items[i];
			while (node != null) {
				int hashCode = hasher.hash(node.data);
				int index = hashCode % newItems.length;
				newItems[index] = new HashNode(node.data, node.count, newItems[index]);
				node = node.next;
			}
		}
		items = newItems;
	}
	
	/** Gets the size of the whole table
	 * @return the current number of nodes in the table.
	 */
	@Override
	public int getSize() {
		return size;
	}
	
	/** Gets the count value of a data. 
	 * @param data
	 * @return the count value of the data in the table; If the data isn't in the table, return 0.
	 */
	@Override
	public int getCount(E data) {
		int hashCode = hasher.hash(data);
		int index = hashCode % items.length;
		HashNode current = items[index];
		while (current != null) {
			int cmp = comparator.compare(current.data, data);
			if (cmp == 0) {
				return current.count;
			}
			current = current.next;
		}
		return 0;
	}

	/**
	 * Returns an iterator for this hash table.
	 * @return an iterator that can iterate over all of the nodes in this table.
	 */
	@Override
	public SimpleIterator<DataCount<E>> getIterator() {
		return new SimpleIterator<DataCount<E>>() {  
			HashNode current  = items[0]; 
			int size = getSize();
			int index = 0;
			
		    public boolean hasNext() {
		    	return size != 0;
		    }
		    public DataCount<E> next() {
		       if(!hasNext()) {
		        	throw new java.util.NoSuchElementException();
		        }
		        while (index < items.length) {
		        	if (current != null) {
		        		size--;
		        		HashNode result = current;
		        		current = current.next;
		        		return new DataCount<E>(result.data, result.count);
		        	}
		        	index++;
		        	current = items[index];
 		        }
		        return null;
		    }
		};
	}

	/** Gets the current capacity of the whole table. 
	 * @return the current length (capacity) of the whole table
	 */
	public int tableLength() {
		return items.length;
	}
}