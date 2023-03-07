import java.util.ArrayList;
import java.util.List;

public class WordCount {
	private int numSplits;
	private int numReducers;
	private List<Mapper> mappers;
	private List<Reducer> reducers;

	public WordCount(int numSplits, int numReducers) {
		this.numSplits = numSplits;
		this.numReducers = numReducers;
		this.mappers = new ArrayList<>();
		for (int i = 0; i < numSplits; i++)
			this.mappers.add(new Mapper());
		this.reducers = new ArrayList<>();
		for (int i = 0; i < numReducers; i++) 
			this.reducers.add(new Reducer());
	}

    public List<List<Pair<String, Integer>>> partitionAndSort(List<Pair<String, Integer>> data, int numPartitions) {
        List<List<Pair<String, Integer>>> partitions = new ArrayList<>(numPartitions);
        for (int i = 0; i < numPartitions; i++) {
            partitions.add(new ArrayList<>());
        }
        for (Pair<String, Integer> pair : data) {
            int partitionIndex = Math.abs(pair.getKey().hashCode()) % numPartitions;
            partitions.get(partitionIndex).add(pair);
        }
        for (List<Pair<String, Integer>> partition : partitions) {
            partition.sort((a, b) -> a.getKey().compareTo(b.getKey()));
        }
        return partitions;
	}

	public List<Pair<String, Integer>> mergeMappedData() {
		List<Pair<String, Integer>> mappedData = new ArrayList<>();
		for (Mapper mapper : mappers) {
			mappedData.addAll(mapper.mapped);
		}
		return mappedData;
	}

	public void mapShuffleReduce(String[] text) {
		// mapping phase
		for (int i = 0; i < this.numSplits; i++)
			this.mappers.get(i).map(text[i]);

		// shuffling phase
		List<List<Pair<String, Integer>>> partitionedData = partitionAndSort(mergeMappedData(), numReducers);
		System.out.println(partitionedData);
		// calling the reducers 

		for (int i = 0; i < numReducers; i++) {
			Reducer reducer = reducers.get(i);
			reducer.reduce(partitionedData.get(i));
		}

		// final results
		for (int i = 0; i < numReducers; i++) {
			System.out.println("---------------------------------");
			System.out.println("Reducer " + i + ":");
			reducers.get(i).printSumTheGroupedPairs();
		}
	}

}