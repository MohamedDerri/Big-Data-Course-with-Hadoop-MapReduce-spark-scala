package DefaultPackage;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgReducer extends Reducer<Text, PairWritable, Text, FloatWritable> {

	private FloatWritable result = new FloatWritable();

	public void reduce(Text key, Iterable<PairWritable> values, Context context) throws IOException, InterruptedException {
		float sum = 0;
		int count = 0;
			// Calculate the sum and count for the current IP address
			for (PairWritable pair : values) {
				sum += pair.getFirst();
				count += pair.getSecond();
			}
		
			// Calculate the average and set the output value
			float average = sum / count;
			result.set(average);
		
			// Write the output
			context.write(key, result);
	}
}
