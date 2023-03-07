import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reducer {
	List<GroupedPair> groupByPair;

	public Reducer() {
		groupByPair = new ArrayList<>();
	}

	public void reduce(List<Pair<String, Integer>> mapped) {
		Collections.sort(mapped, (a, b) -> a.getKey().compareTo(b.getKey()));
		if (mapped.isEmpty())
			return;
		String currentKey = mapped.get(0).getKey();
		List<Integer> values = new ArrayList<>();
		values.add(mapped.get(0).getValue());

		for (int i = 1; i < mapped.size() && !mapped.isEmpty(); i++) {
			if (currentKey.equals(mapped.get(i).getKey()))
				values.add(mapped.get(i).getValue());
			else {
				GroupedPair tempGroupedPair = new GroupedPair(currentKey, values);
				this.groupByPair.add(tempGroupedPair);
				currentKey = mapped.get(i).getKey();
				values = new ArrayList<>();
				values.add(mapped.get(i).getValue());
			}
		}
		GroupedPair lastPair = new GroupedPair(currentKey, values);
		this.groupByPair.add(lastPair);

	}

	public void printGroupedPairs() {
		for (GroupedPair groupPair : this.groupByPair)
			System.out.println(groupPair.toString());
	}

	public void printSumTheGroupedPairs() {
		for (GroupedPair groupPair : this.groupByPair) {
			int sum = 0;
			List<Integer> values = groupPair.getValues();
			for (int value : values)
				sum += value;
			System.out.println("(" + groupPair.getKey() + ", " + sum + ")");
		}
	}
}
