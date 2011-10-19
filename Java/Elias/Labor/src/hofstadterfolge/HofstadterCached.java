package hofstadterfolge;

import java.util.HashMap;
import java.util.Map;

public class HofstadterCached extends Hofstadter {
	
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
	
	public int Q(int n) {
		if(map.containsKey(n))
			return map.get(n);
		else {
			int result = super.Q(n);
			map.put(n, result);
			return result;
		}
	}
}
