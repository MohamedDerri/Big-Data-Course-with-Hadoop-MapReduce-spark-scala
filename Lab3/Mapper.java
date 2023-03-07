import java.util.ArrayList;
import java.util.List;

public class Mapper {
	List<Pair<String, Integer>> mapped;

	public Mapper() {
		mapped = new ArrayList<>();
	}

	public void map(String rawText) {
		String[] words = rawText.split("[^a-z]+");
		for (String word : words) {
			if (word.length() > 0)
				this.mapped.add(new Pair<String,Integer>(word, 1));
		}
	}
}