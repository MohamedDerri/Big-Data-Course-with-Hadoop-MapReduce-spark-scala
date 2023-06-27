//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.hadoop.io.DoubleWritable;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Reducer;
//
//public class AmazonRelativeFrequenciesReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {
//	private Text pair = new Text();
//	private final static DoubleWritable relativeFreq = new DoubleWritable();
//
//	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//		int sum = 0;
//		Map<String, Integer> countMap = new HashMap<>();
//		for (IntWritable value : values) {
//			sum += value.get();
//			String[] pairSplit = key.toString().split(",");
////			String product = pairSplit[0];
//			String otherProduct = pairSplit[1];
//			if (countMap.containsKey(otherProduct)) {
//				countMap.put(otherProduct, countMap.get(otherProduct) + value.get());
//			} else {
//				countMap.put(otherProduct, value.get());
//			}
//		}
//
//		for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
//			String otherProduct = entry.getKey();
//			double relativeFrequency = (double)entry.getValue() / sum;
//			pair.set(key.toString());
//			relativeFreq.set(relativeFrequency);
//			context.write(pair, relativeFreq);
//		}
//	}
//}
import java.io.IOException;


import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AmazonRelativeFrequenciesReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {
   /* private Text pair = new Text();
    private final static DoubleWritable relativeFreq = new DoubleWritable();
    private int sum = 0;
    Map<Text, Integer> countMap = new HashMap<>();
    
    

    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        String[] pairSplit = key.toString().split(",");
        String otherProduct = pairSplit[1];
        for (IntWritable value : values) {

            count += value.get();
        }

        countMap.put(key, count);

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            double relativeFrequency = (double)entry.getValue() / sum;
            pair.set(key.toString());
            relativeFreq.set(relativeFrequency);
            context.write(pair, relativeFreq);
        }
    }
    
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        sum = 0;
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // optional: you can do some final processing here
    } */
	
	    private int sum = 0;
	    private final static DoubleWritable relativeFreq = new DoubleWritable();

	    @Override
	    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
	        int countSum = 0;
	        String[] pairSplit = key.toString().split(",");
	        String otherProduct = pairSplit[1];
	        for (IntWritable value : values) {
	            countSum += value.get();
	        }
	        if (otherProduct.equals("*")) {
	            sum = countSum;
	            return;
	        }
            double relativeFrequency = (double)countSum / sum;
            relativeFreq.set(relativeFrequency);
	        context.write(key, relativeFreq);
	    }

	    @Override
	    protected void setup(Context context) throws IOException, InterruptedException {
	        sum = 0;
	    }

	    @Override
	    protected void cleanup(Context context) throws IOException, InterruptedException {
	        // optional: you can do some final processing here
	    }    
    

}
