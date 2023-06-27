import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AmazonRelativeFrequenciesReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {
	private Text pair = new Text();
	private final static DoubleWritable relativeFreq = new DoubleWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		Map<String, Integer> countMap = new HashMap<>();
		for (IntWritable value : values) {
			
			String[] pairSplit = key.toString().split(",");
			String product = pairSplit[0];
			String otherProduct = pairSplit[1];
			if (otherProduct.contains("*")) {
				sum += value.get();
			if (countMap.containsKey(otherProduct)) {
				countMap.put(otherProduct, countMap.get(otherProduct) + value.get());
			} else {
				countMap.put(otherProduct, value.get());
			}
		}

		for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
			String otherProduct = entry.getKey();
			double relativeFrequency = (double)entry.getValue() / sum;
			pair.set(key.toString());
			relativeFreq.set(relativeFrequency);
			context.write(pair, relativeFreq);
		}
	}
}
