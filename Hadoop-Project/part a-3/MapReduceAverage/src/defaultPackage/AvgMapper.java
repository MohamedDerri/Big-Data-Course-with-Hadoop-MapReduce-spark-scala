package defaultPackage;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] fields = line.split(" ");
		if (fields.length >= 2) {
            try {
            	if (isValidIpAddress(fields[0])) {
	            	int lastQuantity = Integer.parseInt(fields[fields.length - 1]);
	    			context.write(new Text(fields[0]), new IntWritable(lastQuantity));
            	}
            } catch (NumberFormatException e) {
                // Ignore non-numeric values
            }
        }
	}

    private boolean isValidIpAddress(String ipAddress) {
		// check if the IP address has four parts separated by dots
		String[] parts = ipAddress.split("\\.");
		if (parts.length != 4) {
			return false;
		}

		// check if each part is a valid number between 0 and 255
		for (String part : parts) {
			try {
			int num = Integer.parseInt(part);
			if (num < 0 || num > 255) {
				return false;
			}
			
			} catch (NumberFormatException e) {
				return false;
			}
		}

		return true;
	}
}
