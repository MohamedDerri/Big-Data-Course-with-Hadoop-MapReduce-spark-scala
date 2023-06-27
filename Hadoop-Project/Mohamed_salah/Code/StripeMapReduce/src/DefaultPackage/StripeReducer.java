package DefaultPackage;


import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.util.Map.Entry;


public class StripeReducer  extends Reducer<Text, MyMapWritable, Text, MyMapWritable> {

 
	public void reduce(Text key, Iterable<MyMapWritable> values, Context context) throws IOException, InterruptedException {
		MyMapWritable result = new MyMapWritable();
		float count=0;
		FloatWritable tmp= new FloatWritable();
		FloatWritable tmp2= new FloatWritable();
		Text myText= new Text();
		// Calculate the sum and count for the current IP address
		for (MyMapWritable pair : values) {
			for(@SuppressWarnings("rawtypes") Entry entry : pair.entrySet()){
				myText=(Text)entry.getKey();
				tmp=(FloatWritable) entry.getValue();
				count+=tmp.get();
				
				if(result.containsKey(myText)){
					tmp2=(FloatWritable) result.get(entry.getKey());
					tmp.set(tmp.get()+tmp2.get());
					result.put(myText, tmp);
				}
				else{
					result.put(myText, tmp);
				}
			}
		}

		// Calculate the average and set the output value
		for (@SuppressWarnings("rawtypes") Entry entry : result.entrySet()) {
            myText = (Text) entry.getKey();
            tmp = (FloatWritable) entry.getValue();
            tmp.set( tmp.get() / count);
            result.put(myText, tmp);
        }
        context.write(key, result);

	}
}






