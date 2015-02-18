/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */

package writeupExperiment;

import providedCode.Hasher;

/**
 * StringHasher can generate a hash code that is got from a given string. The hash code is calculated 
 * by a particular hash function. This hash code can be used when inserting data into a hash table.
 * @author caijuan & dongchang he
 */
public class NewStringHasher2 implements Hasher<String> {
	
	/** Returns a hash code that is generated from a given string s. 
	 * @param s: a given string
	 * @return a hash code integer
	 */
	@Override
	public int hash(String s) {
		return s.charAt(0);
	}
}