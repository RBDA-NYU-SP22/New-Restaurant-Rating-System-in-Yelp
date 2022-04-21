package user;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

public class UserProfilingMapper
        extends Mapper<LongWritable, Text, Text, UserProfilingTuple> {

    private static final int MISSING = 0;
    private UserProfilingTuple outTuple = new UserProfilingTuple();
    private UserProfilingTuple userIdTuple = new UserProfilingTuple();

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        try {
            User user = User.JSON2User(value.toString());
            Map<String, Object> userMap = user.getUserMap();
            for (Map.Entry<String, Object> me: userMap.entrySet()) {
                // Divided by types
                switch (me.getKey()){
                    // int cases
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
                        outTuple.setMax(Integer.parseInt(me.getValue().toString()));
                        outTuple.setMin(Integer.parseInt(me.getValue().toString()));
                        break;
                    // double cases
                    case "average_stars":
                        outTuple.setMax(Double.parseDouble(me.getValue().toString()));
                        outTuple.setMin(Double.parseDouble(me.getValue().toString()));
                        break;
                    case "friends":
                        if (me.getValue().toString().equals("None")){
                            outTuple.setMax(0);
                            outTuple.setMin(0);
                        }else {
                            String[] friends = me.getValue().toString().split(", ");
                            outTuple.setMax(friends.length);
                            outTuple.setMin(friends.length);
                        }
                        break;
                    case "user_id":
                        userIdTuple.setUserId(true);
                        userIdTuple.setCount(1);
                        context.write(new Text(user.getUser_id()), userIdTuple);
                        break;
                    // String cases
                    case "name":
                    case "yelping_since":
                    case "elite":
                    default:
                        break;

                }
                outTuple.setCount(1);
                context.write(new Text(me.getKey()), outTuple);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}