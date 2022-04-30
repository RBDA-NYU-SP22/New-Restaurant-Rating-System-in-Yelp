package user;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserProcessMapper
        extends Mapper<LongWritable, Text, NullWritable, UserProcessTuple> {

    private static final int MISSING = 0;
    private UserProcessTuple outTuple = new UserProcessTuple();

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
            User user = User.JSON2User(value.toString());
            outTuple.setUserCleaningTuple(user);
            long V = user.getUseful() + user.getCool() + user.getFunny();
            long N = user.getReview_count();
            long cur = new Date().getTime();
            long time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(user.getYelping_since(), new ParsePosition(0)).getTime();
            long T = cur - time;
            double G = 1.5;

            double rate = Math.log10(V / (N+1) * (Math.pow(T + 1, G)+1) * Math.log10(user.getFriendsNumber())+1)+1;

            outTuple.setRate(rate);
            context.write(NullWritable.get(), outTuple);
    }
}