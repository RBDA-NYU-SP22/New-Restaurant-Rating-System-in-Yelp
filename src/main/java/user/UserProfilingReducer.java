package user;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.Field;

public class UserProfilingReducer
        extends Reducer<NullWritable, UserProfilingTuple, NullWritable, UserProfilingTuple> {
    private UserProfilingTuple result = new UserProfilingTuple();
    @Override
    public void reduce(NullWritable key, Iterable<UserProfilingTuple> values, Context context)
            throws IOException, InterruptedException {
        // Set initial value to null to avoid setting a MIN number or a MAX number
        result.setMinTuple(null);
        result.setMaxTuple(null);
        result.setCount(0);
        int sum = 0;
        try{
            for (UserProfilingTuple value : values) {
                if (result.getMaxTuple() == null){
                    result.setMaxTuple(value.getMaxTuple());
                }else {
                    // The first function accepts Integers, and the second one accepts Double
                    // Maybe we can implement a third one to process String, but it is not necessary for now
                    result.setMaxTuple(UserProfilingTuple.mapFunc(value.getMaxTuple(), result.getMaxTuple(), Math::max, Math::max));
                }
                if (result.getMinTuple() == null){
                    result.setMinTuple(value.getMinTuple());
                }else {
                    result.setMinTuple(UserProfilingTuple.mapFunc(value.getMinTuple(), result.getMinTuple(), Math::min, Math::min));
                }
                sum += value.getCount();
            }
            result.setCount(sum);
            context.write(NullWritable.get(), result);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
