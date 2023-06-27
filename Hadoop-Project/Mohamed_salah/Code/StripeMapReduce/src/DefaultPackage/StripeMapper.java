package DefaultPackage;


import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StripeMapper extends Mapper<LongWritable, Text, Text, MyMapWritable> {

	@Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] fields = line.split(" ");
		MyMapWritable myMap=new MyMapWritable();
		Text tmpText;
		FloatWritable tmpIntWritable;
        try {
        	
        	for(int i=0 ; i<fields.length-1 ; i++){
        		if(!fields[i].equalsIgnoreCase("")){
	        		myMap=new MyMapWritable();
	        		for(int j=i+1 ; j<fields.length ; j++){
	        			if(!fields[j].equalsIgnoreCase("")){
		        			if(fields[i].equals(fields[j])){
		        				break;
		        			}
		        			tmpText=new Text(fields[j]);
		        			if(myMap.containsKey(tmpText)){
		        				tmpIntWritable= (FloatWritable) myMap.get(tmpText);
		        				tmpIntWritable.set(tmpIntWritable.get()+1);
		        				myMap.put(tmpText, tmpIntWritable);
		        			}
		        			else{
		        				myMap.put(tmpText, new FloatWritable(1));
		        			}
	        			}
	        		}
	    			context.write(new Text(fields[i]), myMap);
        		}
        	}
        } catch (NumberFormatException e) {
            // Ignore non-numeric values
        }
        
	}
}
