// Importing libraries
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
//import org.apache.hadoop.mapreduce.Mapper.Context;


public class WCMapper extends MapReduceBase implements Mapper<LongWritable,
												Text, Text, IntWritable> {
	private Map<String, Integer> counts;
	private OutputCollector<Text, IntWritable> output;
	
	@Override
	public void configure(JobConf job) {
	    // Initialize the map in the configure method
	    counts = new HashMap<String, Integer>();
	}
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
	    // Split the input line into words
	    String[] words = value.toString().split(" ");
	
	    // Increment the count for each word in the map
	    for (String word : words) {
	        Integer count = counts.get(word);
	        if (count == null) {
	            count = 0;
	        }
	        count++;
	        counts.put(word, count);
	    }
	
	    // Store the output collector for later use
	    this.output = output;
	}
	
	@Override
	public void close() throws IOException {
	    // Emit the intermediate results using the output collector
	    for (Map.Entry<String, Integer> entry : counts.entrySet()) {
	        output.collect(new Text(entry.getKey()), new IntWritable(entry.getValue()));
	    }
	}

}
