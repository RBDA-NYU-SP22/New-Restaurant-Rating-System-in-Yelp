package user;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class UserProcessProfilingReducer
        extends Reducer<Text, UserProfilingTuple, Text, UserProfilingTuple> {
    private UserProfilingTuple result = new UserProfilingTuple();
    @Override
    public void reduce(Text key, Iterable<UserProfilingTuple> values, Context context)
            throws IOException, InterruptedException {
        // Set initial value to null to avoid setting a MIN number or a MAX number
        result.setMin(Double.MAX_VALUE);
        result.setMax(Double.MIN_VALUE);
        result.setCount(0);
        for (UserProfilingTuple value: values){
            result.setMax(Math.max(result.getMax(), value.getMax()));
            result.setMin(Math.min(result.getMin(), value.getMin()));
        }
        context.write(key, result);
    }
}
