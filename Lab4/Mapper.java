import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {
    Map<String, Integer> wordCounts;

    public Mapper() {
        wordCounts = new HashMap<>();
    }

    public void map(String rawText) {
        String[] words = rawText.split("[^a-z]+");
        for (String word : words) {
            if (word.length() > 0) {
                if (wordCounts.containsKey(word)) {
                    int count = wordCounts.get(word);
                    wordCounts.put(word, count + 1);
                } else {
                    wordCounts.put(word, 1);
                }
            }
        }
    }

    public List<Pair<String, Integer>> getResults() {
        List<Pair<String, Integer>> results = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            results.add(new Pair<String, Integer>(entry.getKey(), entry.getValue()));
        }
        return results;
    }
}
