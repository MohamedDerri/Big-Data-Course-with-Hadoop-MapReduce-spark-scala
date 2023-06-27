import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AmazonRelativeFrequenciesMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text pair = new Text();
	private IntWritable one = new IntWritable(1);

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		String[] record = value.toString().split("\\s+");
		for (int i = 0; i < record.length; i++) {
		String u = record[i];
		for (int j = i + 1; j < record.length && !record[j].equals(u); j++) {
			String v = record[j];
			pair.set(u + "," + v);
			context.write(pair, one);
		}
		pair.set(u + ",*");
		context.write(pair, one);
		}
	}
	}
