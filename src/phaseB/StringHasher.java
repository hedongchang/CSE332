package phaseB;
import providedCode.Hasher;


public class StringHasher implements Hasher<String> {
	
	@Override
	public int hash(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			result = result + 31 * s.charAt(i);
		}
		return result;
	}
}
