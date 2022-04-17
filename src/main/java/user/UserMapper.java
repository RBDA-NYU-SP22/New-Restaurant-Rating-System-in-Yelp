package user;
import java.io.IOException;

import com.google.gson.Gson;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UserMapper
        extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final int MISSING = 0;
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        try {
            Gson g = new Gson();
            User user = g.fromJson(value.toString(), User.class);
            String user_id = user.getUser_id();
            int reviewCount = user.getReview_count();
            context.write(new Text(user_id), new IntWritable(reviewCount));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}