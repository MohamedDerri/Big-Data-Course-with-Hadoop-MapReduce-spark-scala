package DefaultPackage;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgMapper extends Mapper<LongWritable, Text, Text, PairWritable> {
	private HashMap<String, PairWritable> ipMap;

	@Override
	public void setup(Context context) throws IOException, InterruptedException {
		ipMap = new HashMap<String, PairWritable>();
	}

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] fields = line.split(" ");

		try{
			// is the ip address valid
			if (isValidIpAddress(fields[0])) {
	
				int lastQuantity = Integer.parseInt(fields[fields.length - 1]);
	
				// Check if IP address exists in the HashMap
				if (ipMap.containsKey(fields[0])) {
					PairWritable pair = ipMap.get(fields[0]);
					pair.setFirst(pair.getFirst() + lastQuantity);
					pair.setSecond(pair.getSecond() + 1);
					ipMap.put(fields[0], pair);
				} else {
					ipMap.put(fields[0], new PairWritable(lastQuantity, 1));
				}
			}
		} catch (NumberFormatException e) {
			
		}
	}

	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {
		for (String ip : ipMap.keySet()) {
			context.write(new Text(ip), ipMap.get(ip));
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