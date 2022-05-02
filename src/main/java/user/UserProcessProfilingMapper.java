package user;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

public class UserProcessProfilingMapper
        extends Mapper<LongWritable, Text, Text, UserProfilingTuple> {

    private static final int MISSING = 0;
    private UserProfilingTuple outTuple = new UserProfilingTuple();
    private UserProfilingTuple userIdTuple = new UserProfilingTuple();

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String[] values = value.toString().split(";");
        String[] fields = {"user_id", "review_count", "rate", "friends"};
        for (int i=1; i < 4; i ++){
            outTuple.setMax(Double.parseDouble(values[i]));
            outTuple.setMin(Double.parseDouble(values[i]));
            context.write(new Text(fields[i]), outTuple);
        }
    }
}