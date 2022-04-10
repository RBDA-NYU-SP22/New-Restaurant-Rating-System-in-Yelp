package user;
import java.io.IOException;

import com.google.gson.Gson;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserMapper
        extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final int MISSING = 0;
    private  String pattern = "\\{\\\"user_id\\\"\\:\\\"(.*?)\\\"\\,.*\\\"review_count\\\"\\:(.*?)\\,.*\\}" ;

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
//        TODO: parse json in a graceful way
//        Gson g = new Gson();
//        User user = g.fromJson(value.toString(), User.class);
        String line = value.toString();
        Matcher m = Pattern.compile(pattern).matcher(line);
        m.find();
        String user_id = m.group(1);
        int reviewCount = Integer.parseInt(m.group(2));
//        String user_id = user.getUser_id();
//        int reviewCount = user.getReview_count();
        context.write(new Text(user_id), new IntWritable(reviewCount));
    }
}