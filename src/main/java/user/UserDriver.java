package user;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class UserDriver {
    public static void run(String[] args) throws Exception {
        System.out.println("running user");
        Job job = Job.getInstance();
        job.setJarByClass(User.class);
        job.setJobName("Max Review Count");
        job.setNumReduceTasks(1);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(UserProfilingMapper.class);
//        job.setCombinerClass(User.class);
        job.setReducerClass(UserProfilingReducer.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(UserProfilingTuple.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
