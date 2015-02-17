package writeupExperiment;

import providedCode.Hasher;

public class NewStringHasher implements Hasher<String> {
	
	@Override
	public int hash(String s) {
		return s.charAt(0);
	}
}
