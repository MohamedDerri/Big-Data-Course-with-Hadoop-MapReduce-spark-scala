continue this code ! 
import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgReducer extends Reducer<Text, SumCountPair, Text, FloatWritable> {

	private FloatWritable result = new FloatWritable();

	public void reduce(Text key, Iterable<SumCountPair> values, Context context) throws IOException, InterruptedException {
		float sum = 0;
		int count = 0;
			// Calculate the sum and count for the current IP address
			for (SumCountPair pair : values) {
				sum += pair.getSum().get();
				count += pair.getCount().get();
			}
		
			// Calculate the average and set the output value
			float average = sum / count;
			result.set(average);
		
			// Write the output
			context.write(key, result);
	}
}
