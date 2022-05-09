package Review;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Review{

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: Review Ingestion <input path> <output path>");
      System.exit(-1);
    }
    
    Job job = Job.getInstance();
    job.setJarByClass(Review.class);
    job.setJobName("Review Ingestion");

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.setMapperClass(ReviewMapper.class);
    // job.setCombinerClass(MaxTemperatureReducer.class);
    job.setReducerClass(ReviewReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    job.setNumReduceTasks(1);

    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
