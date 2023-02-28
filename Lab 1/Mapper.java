import java.util.ArrayList;
import java.util.List;

public class Mapper {
	List<Pair<String, Integer>> pairs;

	public Mapper() {
		pairs = new ArrayList<>();
	}

	public void mapper(String rawText) {
		String[] words = rawText.split("[^a-z]+");
		for (String word : words) {
			boolean found = false;
			for (Pair<String, Integer> pair : pairs) {
				if (pair.getKey().equals(word)) {
					pair.setValue(pair.getValue() + 1);
					found = true;
					break;
				}
			}
			if (!found)
				pairs.add(new Pair<>(word, 1));
		}
	}
}
