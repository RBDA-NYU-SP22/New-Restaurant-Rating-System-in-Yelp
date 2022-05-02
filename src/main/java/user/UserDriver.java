package user;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class UserDriver {
    public static void runProfiling(String[] args) throws Exception {
        System.out.println("Profiling user data...");
        Job job = Job.getInstance();
        job.setJarByClass(User.class);
        job.setJobName("User Data Profiling");
        job.setNumReduceTasks(1);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(UserProfilingMapper.class);
        job.setCombinerClass(UserProfilingReducer.class);
        job.setReducerClass(UserProfilingReducer.class);
        job.setNumReduceTasks(1);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(UserProfilingTuple.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static void runCleaning(String[] args) throws Exception {
        System.out.println("Cleaning user data...");
        Job job = Job.getInstance();
        job.setJarByClass(User.class);
        job.setJobName("User Data Cleaning");
        job.setNumReduceTasks(0);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(UserCleaningMapper.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(UserProfilingTuple.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static void runProcessing(String[] args) throws Exception {
        System.out.println("Processing user data...");
        Job job = Job.getInstance();
        job.setJarByClass(User.class);
        job.setJobName("User Data Processing");
        job.setNumReduceTasks(0);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]+ "result"));

        job.setMapperClass(UserProcessMapper.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(UserProcessTuple.class);
        job.waitForCompletion(true);

        // A simple profiling of the result data
        System.out.println("Profiling result data...");
        Job profileJob = Job.getInstance();
        profileJob.setJarByClass(User.class);
        profileJob.setJobName("Result data profiling");
        profileJob.setNumReduceTasks(0);
        FileInputFormat.addInputPath(profileJob, new Path(args[1] + "result"));
        FileOutputFormat.setOutputPath(profileJob, new Path(args[1] + "profile"));

        profileJob.setMapperClass(UserProcessProfilingMapper.class);
        profileJob.setCombinerClass(UserProcessProfilingReducer.class);
        profileJob.setReducerClass(UserProcessProfilingReducer.class);
        profileJob.setNumReduceTasks(1);

        profileJob.setOutputKeyClass(Text.class);
        profileJob.setOutputValueClass(UserProfilingTuple.class);
        System.exit(profileJob.waitForCompletion(true) ? 0 : 1);
    }
}

