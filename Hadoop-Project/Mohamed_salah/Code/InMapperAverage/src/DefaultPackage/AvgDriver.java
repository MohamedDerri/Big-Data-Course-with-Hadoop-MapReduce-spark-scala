package DefaultPackage;

//import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AvgDriver extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new AvgDriver(), args);
		System.exit(res);
	}

	public int run(String[] args) throws Exception {

		if (args.length != 2) {
			System.err.println("Usage: AvgDriver <input path> <output path>");
			System.exit(-1);
		}

		Configuration conf = getConf();
		Job job = Job.getInstance(conf, "average computation");

		job.setJarByClass(AvgDriver.class);
		job.setMapperClass(AvgMapper.class);
		job.setReducerClass(AvgReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(PairWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		FileSystem fs = FileSystem.newInstance(conf);

		// Delete output directory if already exists
		if (fs.exists(new Path(args[1]))) {
			fs.delete(new Path(args[1]), true);
		}

		int returnValue = job.waitForCompletion(true) ? 0 : 1;
		return returnValue;
	}
}
