package user;

import com.google.gson.Gson;
import org.apache.commons.io.output.NullWriter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.lang.reflect.Field;

public class UserProfilingMapper
        extends Mapper<LongWritable, Text, NullWritable, UserProfilingTuple> {

    private static final int MISSING = 0;
    private UserProfilingTuple outTuple = new UserProfilingTuple();

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        try {
            User user = User.JSON2User(value.toString());
            outTuple.setCount(1);
            outTuple.setMaxTuple(user);
            outTuple.setMinTuple(user);
            context.write(NullWritable.get(), outTuple);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}