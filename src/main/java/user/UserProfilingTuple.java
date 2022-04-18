package user;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;
import java.util.function.BinaryOperator;

public class UserProfilingTuple implements Writable {
    private User maxTuple;
    private User minTuple;

    private long count;
//    private long[] counts;
    private Map<String, Long> counts;

    public Map<String, Long> getCounts() {
        return counts;
    }

    // set counts with one map
    public void setCounts(Map<String, Long> counts) {
        this.counts = counts;
    }
//     set one count in counts with index
    public void setCounts(String attribute, long count) {
        this.counts.put(attribute, count);
    }
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public User getMaxTuple() {
        return maxTuple;
    }

    public void setMaxTuple(User maxTuple) {
        this.maxTuple = maxTuple;
    }

    public User getMinTuple() {
        return minTuple;
    }

    public void setMinTuple(User minTuple) {
        this.minTuple = minTuple;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException{
        dataOutput.writeUTF(maxTuple.toJsonText());
        dataOutput.writeUTF(minTuple.toJsonText());
        dataOutput.writeLong(count);
    }
    @Override
    public void readFields(DataInput dataInput) throws IOException{
        maxTuple = User.JSON2User(dataInput.readUTF());
        minTuple = User.JSON2User(dataInput.readUTF());
        count = dataInput.readLong();
    }
    @Override
    public String toString(){
        return String.format("%-25s %10d\n","count", count)
                + String.format("%-25s %10d %10d \n", "review_count",minTuple.getReview_count(), maxTuple.getReview_count())
                + String.format("%-25s %10d %10d \n", "useful",minTuple.getUseful(), maxTuple.getUseful())
                + String.format("%-25s %10d %10d \n", "funny",minTuple.getFans(), maxTuple.getFans())
                + String.format("%-25s %10d %10d \n", "cool",minTuple.getCool(), maxTuple.getCool())
                + String.format("%-25s %10d %10d \n", "fans",minTuple.getFans(), maxTuple.getFans())
                + String.format("%-25s %10f %10f \n", "average_stars",minTuple.getAverage_stars(), maxTuple.getAverage_stars())
                + String.format("%-25s %10d %10d \n", "compliment_hot",minTuple.getCompliment_hot(), maxTuple.getCompliment_hot())
                + String.format("%-25s %10d %10d \n", "compliment_more",minTuple.getCompliment_more(), maxTuple.getCompliment_more())
                + String.format("%-25s %10d %10d \n", "compliment_profile",minTuple.getCompliment_profile(), maxTuple.getCompliment_profile())
                + String.format("%-25s %10d %10d \n", "compliment_cute",minTuple.getCompliment_cute(), maxTuple.getCompliment_cute())
                + String.format("%-25s %10d %10d \n", "compliment_list",minTuple.getCompliment_list(), maxTuple.getCompliment_list())
                + String.format("%-25s %10d %10d \n", "compliment_note",minTuple.getCompliment_note(), maxTuple.getCompliment_note())
                + String.format("%-25s %10d %10d \n", "compliment_plain",minTuple.getCompliment_plain(), maxTuple.getCompliment_plain())
                + String.format("%-25s %10d %10d \n", "compliment_cool",minTuple.getCompliment_cool(), maxTuple.getCompliment_cool())
                + String.format("%-25s %10d %10d \n", "compliment_funny",minTuple.getCompliment_funny(), maxTuple.getCompliment_funny())
                + String.format("%-25s %10d %10d \n", "compliment_writer",minTuple.getCompliment_writer(), maxTuple.getCompliment_writer())
                + String.format("%-25s %10d %10d \n", "compliment_photos",minTuple.getCompliment_photos(), maxTuple.getCompliment_photos())
                ;
    }

    public static User mapFunc(User u1, User u2, BinaryOperator<Integer> func1, BinaryOperator<Double> func2) throws Exception {
        User r = new User();
        r.setReview_count(func1.apply(u1.getReview_count(), u2.getReview_count()));
        r.setUseful(func1.apply(u1.getUseful(), u2.getUseful()));
        r.setFunny(func1.apply(u1.getFunny(), u2.getFunny()));
        r.setCool(func1.apply(u1.getCool(), u2.getCool()));
        r.setFans(func1.apply(u1.getFans(), u2.getFans()));
        r.setAverage_stars(func2.apply(u1.getAverage_stars(), u2.getAverage_stars()));
        r.setCompliment_hot(func1.apply(u1.getCompliment_hot(), u2.getCompliment_hot()));
        r.setCompliment_more(func1.apply(u1.getCompliment_more(), u2.getCompliment_more()));
        r.setCompliment_profile(func1.apply(u1.getCompliment_profile(), u2.getCompliment_profile()));
        r.setCompliment_cute(func1.apply(u1.getCompliment_cute(), u2.getCompliment_cute()));
        r.setCompliment_list(func1.apply(u1.getCompliment_list(), u2.getCompliment_list()));
        r.setCompliment_note(func1.apply(u1.getCompliment_note(), u2.getCompliment_note()));
        r.setCompliment_plain(func1.apply(u1.getCompliment_plain(), u2.getCompliment_plain()));
        r.setCompliment_cool(func1.apply(u1.getCompliment_cool(), u2.getCompliment_cool()));
        r.setCompliment_funny(func1.apply(u1.getCompliment_funny(), u2.getCompliment_funny()));
        r.setCompliment_writer(func1.apply(u1.getCompliment_writer(), u2.getCompliment_writer()));
        r.setCompliment_photos(func1.apply(u1.getCompliment_photos(), u2.getCompliment_photos()));
        return r;
    }

}
