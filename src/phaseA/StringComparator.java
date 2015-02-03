/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */

package phaseA;
import providedCode.*;


/**
 * Compare the two strings alphabetically first. Then, compare them based on their length. 
 * It returns a positive number if the first string is larger than the second string; returns
 * 0 if they are the same; returns a negative number if the first string is smaller than the 
 * second string.
 */
public class StringComparator implements Comparator<String>{
	
	/** Compare the two strings alphabetically first and then in length.
	 * @param Two strings s1 and s2.
	 * @return If the alphabetic order of s1 is bigger than s2, return 1;
	 *         if the alphabetic order of s1 is smaller than s2, return 2;
	 *         other than them, return the difference of size of s1 minus size of s2. 
	 **/
	@Override
	public int compare(String s1, String s2) {
		for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
			char ch1 = s1.charAt(i);
			char ch2 = s2.charAt(i);
			if (ch1 > ch2) {
				return 1;
			} else if (ch1 < ch2) {
				return -1;
			}
		}
		return s1.length() - s2.length();
	}
}
