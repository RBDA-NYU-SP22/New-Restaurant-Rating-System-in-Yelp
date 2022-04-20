package user;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

public class UserCleaningMapper
        extends Mapper<LongWritable, Text, NullWritable, UserCleaningTuple> {

    private static final int MISSING = 0;
    private UserCleaningTuple outTuple = new UserCleaningTuple();

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
            User user = User.JSON2User(value.toString());
            outTuple.setUserCleaningTuple(user);
            context.write(NullWritable.get(), outTuple);
    }
}