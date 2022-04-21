package user;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class UserProfilingReducer
        extends Reducer<Text, UserProfilingTuple, Text, UserProfilingTuple> {
    private UserProfilingTuple result = new UserProfilingTuple();
    @Override
    public void reduce(Text key, Iterable<UserProfilingTuple> values, Context context)
            throws IOException, InterruptedException {
        // Set initial value to null to avoid setting a MIN number or a MAX number
        result.setMin(Double.MAX_VALUE);
        result.setMax(Double.MIN_VALUE);
        result.setCount(0);
        int sum = 0;
        try{
            for (UserProfilingTuple value : values) {
                result.setUserId(value.isUserId());
                // Divided by types
                switch (key.toString()){
                    // number cases
                    case "review_count":
                    case "useful":
                    case "funny":
                    case "cool":
                    case "fans":
                    case "compliment_hot":
                    case "compliment_more":
                    case "compliment_profile":
                    case "compliment_cute":
                    case "compliment_list":
                    case "compliment_note":
                    case "compliment_plain":
                    case "compliment_cool":
                    case "compliment_funny":
                    case "compliment_writer":
                    case "compliment_photos":
                    case "average_stars":
                    case "friends":
                        result.setMax(Math.max(result.getMax(), value.getMax()));
                        result.setMin(Math.min(result.getMin(), value.getMin()));
                        break;
                    // String cases
                    case "user_id":
                    case "name":
                    case "yelping_since":
                    case "elite":
                    default:
                        result.setMax(0);
                        result.setMin(0);
                        break;

                }
                sum += value.getCount();
            }
            result.setCount(sum);
            // Write result if it is not a user_id Or if its count is not 1
            if (!result.isUserId() || result.getCount() != 1){
                context.write(key, result);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
