// CSE 332 Project 2 Phase B
// 2/17/2015
// DONGCHANG HE & JUAN CAI
package phaseB;
import providedCode.Hasher;

/**
 * StringHasher can generate a hash code that is got from a given string. The hash code is calculated by a particular hash
 * function. This hash code can be used when inserting data into a hash table.
 * @author caijuan & dongchang he
 */
public class StringHasher implements Hasher<String> {
	
	/** Returns a hash code that is generated from a given string s. 
	 * @param s: a given string
	 * @return a hash code integer
	 */
	@Override
	public int hash(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			result = result + 31 * s.charAt(i);
		}
		return result;
	}
}