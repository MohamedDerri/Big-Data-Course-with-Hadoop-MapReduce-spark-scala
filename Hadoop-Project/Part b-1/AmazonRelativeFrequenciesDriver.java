public class AmazonRelativeFrequenciesDriver {
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new AmazonRelativeFrequenciesDriver(), args);
		System.exit(res);
	}

	public int run(String[] args) throws Exception {

		if (args.length != 2) {
			System.err.println("Usage: AmazonRelativeFrequenciesDriver <input path> <output path>");
			System.exit(-1);
		}

		Configuration conf = getConf();
		Job job = Job.getInstance(conf, "average computation");

		job.setJarByClass(AmazonRelativeFrequenciesDriver.class);
		job.setMapperClass(AmazonRelativeFrequenciesMapper.class);
		job.setReducerClass(AmazonRelativeFrequenciesMapper.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);

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
