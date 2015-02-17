package phaseB;
import phaseA.GArrayStack;
import providedCode.*;


/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. You may implement any kind of HashTable discussed in class; the
 *    only restriction is that it should not restrict the size of the
 *    input domain (i.e., it must accept any key) or the number of
 *    inputs (i.e., it must grow as necessary).
 * 2. You should use this implementation with the -h option.
 * 3. Your HashTable should rehash as appropriate (use load factor as
 *    shown in the class).
 * 4. To use your HashTable for WordCount, you will need to be able to
 *    hash strings. Implement your own hashing strategy using charAt
 *    and length. Do NOT use Java's hashCode method.
 * 5. HashTable should be able to grow at least up to 200,000. We are
 *    not going to test input size over 200,000 so you can stop
 *    resizing there (of course, you can make it grow even larger but
 *    it is not necessary).
 * 6. We suggest you to hard code the prime numbers. You can use this
 *    list: http://primes.utm.edu/lists/small/100000.txt NOTE: Make
 *    sure you only hard code the prime numbers that are going to be
 *    used. Do NOT copy the whole list!
 * TODO: Develop appropriate JUnit tests for your HashTable.
 */
public class HashTable<E> extends DataCounter<E> {
	public static final int[] PRIMES = {37, 73, 149, 307, 683, 1279, 2423, 5113, 
		10039, 20393, 50333, 105913, 199961};
	
	public static final double LOAD_FACTOR = 0.75;
	
	private HashNode[] items;
	private Comparator<? super E> comparator;
	private Hasher<E> hasher;
	private int capacity;
	private int primeCount;
	
	private class HashNode {
		private E data;
		private int count;
		private HashNode next;
		
		public HashNode(E data, int count, HashNode next) {
			this.data = data;
			this.count = count;
			this.next = next;
		}
	}
	@SuppressWarnings("unchecked")
	public HashTable(Comparator<? super E> c, Hasher<E> h) {
		comparator = c;
		hasher = h;
		primeCount = 0;
		items = (HashNode[]) new HashTable.HashNode[PRIMES[0]];
		capacity = 0;
	}
	
	/**
	 * incCount increments the count of a bucket if it exists, otherwise
	 * it creates a new bucket and adds it to the HashTable. Rehashes the
	 * table if the load factor exceeds .75
	 * @param data is the element that the count will be incremented on
	 */
	@Override
	public void incCount(E data) {
		double loadFactor = (double) capacity / items.length;
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
		capacity++;
	}
	
	private void reHash() {
		primeCount++;
		int length2 = PRIMES[primeCount];
		@SuppressWarnings("unchecked")
		HashNode[] newItems = (HashNode[]) new HashTable.HashNode[length2];
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
	
	@Override
	public int getSize() {
		return capacity;
	}
	
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

	@Override
	public SimpleIterator<DataCount<E>> getIterator() {
		return new SimpleIterator<DataCount<E>>() {  
			GArrayStack<HashNode> stack = new GArrayStack<HashNode>();
			{
				for (int i = 0; i < items.length; i++) {
					if (items[i] != null) {
						stack.push(items[i]);
					}
				}
			}
		    public boolean hasNext() {
		    	return !stack.isEmpty();
		    }
		    public DataCount<E> next() {
		       if(!hasNext()) {
		        	throw new java.util.NoSuchElementException();
		        }
		        HashNode next = stack.pop();
		        if (next.next != null) {
		        	stack.push(next.next);
		        }
		        return new DataCount<E>(next.data, next.count);
		    }
		};
	}

	public int tableLength() {
		return items.length;
	}
}
