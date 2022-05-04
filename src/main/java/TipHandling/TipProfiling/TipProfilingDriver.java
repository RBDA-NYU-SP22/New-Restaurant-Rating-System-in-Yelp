package TipHandling.TipProfiling;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TipProfilingDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("Profile:");
        Job job = Job.getInstance();
        job.setJarByClass(Tip.class);
        job.setJobName("Profile");
        job.setNumReduceTasks(1);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(TipMapper.class);
        job.setCombinerClass(TipReducer.class);
        job.setReducerClass(TipReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(TipSet.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
