package user;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class UserProcessTuple implements Writable {
    private String user_id;
    private int review_count;
    private String yelping_since;
    private int friends;
    private int fans;
    private double average_stars;
    private double rate;

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public String getYelping_since() {
        return yelping_since;
    }

    public void setYelping_since(String yelping_since) {
        this.yelping_since = yelping_since;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public double getAverage_stars() {
        return average_stars;
    }

    public void setAverage_stars(double average_stars) {
        this.average_stars = average_stars;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(user_id);
        dataOutput.writeInt(review_count);
        dataOutput.writeUTF(yelping_since);
        dataOutput.writeInt(friends);
        dataOutput.writeInt(fans);
        dataOutput.writeDouble(average_stars);
        dataOutput.writeDouble(rate);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        user_id = dataInput.readUTF();
        review_count = dataInput.readInt();
        yelping_since = dataInput.readUTF();
        friends = dataInput.readInt();
        fans = dataInput.readInt();
        average_stars = dataInput.readDouble();
        rate = dataInput.readDouble();
    }
    public void setUserCleaningTuple (User user){
        this.user_id = user.getUser_id();
        this.review_count = user.getReview_count();
        this.friends = user.getFriendsNumber();
        this.fans = user.getFans();
        this.average_stars = user.getAverage_stars();
    }
    @Override
    public String toString(){
        return String.format("%s;%s;%s;%s;%s;%s",
                user_id,
                review_count,
                rate,
                friends,
                fans,
                average_stars
        );
    }
}
