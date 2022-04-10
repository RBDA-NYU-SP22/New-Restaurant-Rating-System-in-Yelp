package user;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class User {
    private String user_id;
    private String name;
    private int review_count;
    private String yelping_since;
    private int useful;
    private int funny;
    private int cool;
    private int elite;
    private String friends;
    private int fans;
    private int average_stars;
    private int compliment_hot;
    private int compliment_more;
    private int compliment_profile;
    private int compliment_cute;
    private int compliment_list;
    private int compliment_note;
    private int compliment_plain;
    private int compliment_cool;
    private int compliment_funny;
    private int compliment_writer;
    private int compliment_photos;

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public int getReview_count() {
        return review_count;
    }

    public String getYelping_since() {
        return yelping_since;
    }

    public int getUseful() {
        return useful;
    }

    public int getFunny() {
        return funny;
    }

    public int getCool() {
        return cool;
    }

    public int getElite() {
        return elite;
    }

    public String getFriends() {
        return friends;
    }

    public int getFans() {
        return fans;
    }

    public int getAverage_stars() {
        return average_stars;
    }

    public int getCompliment_hot() {
        return compliment_hot;
    }

    public int getCompliment_more() {
        return compliment_more;
    }

    public int getCompliment_profile() {
        return compliment_profile;
    }

    public int getCompliment_cute() {
        return compliment_cute;
    }

    public int getCompliment_list() {
        return compliment_list;
    }

    public int getCompliment_note() {
        return compliment_note;
    }

    public int getCompliment_plain() {
        return compliment_plain;
    }

    public int getCompliment_cool() {
        return compliment_cool;
    }

    public int getCompliment_funny() {
        return compliment_funny;
    }

    public int getCompliment_writer() {
        return compliment_writer;
    }

    public int getCompliment_photos() {
        return compliment_photos;
    }


    public static void run(String[] args) throws Exception {
        System.out.println("running user");
        Job job = Job.getInstance();
        job.setJarByClass(User.class);
        job.setJobName("Max Review Count");
        job.setNumReduceTasks(1);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(UserMapper.class);
//        job.setCombinerClass(User.class);
        job.setReducerClass(UserReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
