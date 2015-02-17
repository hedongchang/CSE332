package writeupExperiment;

import providedCode.Hasher;

public class NewStringHasher2 implements Hasher<String> {
	
	@Override
	public int hash(String s) {
		return s.charAt(0);
	}
}