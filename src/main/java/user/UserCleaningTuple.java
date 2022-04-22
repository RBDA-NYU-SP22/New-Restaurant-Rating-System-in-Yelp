package user;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class UserCleaningTuple implements Writable {
    private String user_id;
//    private String name;
    private int review_count;
//    private String yelping_since;
    private int useful;
    private int funny;
    private int cool;
//    private String elite;
    private String friends;
    private int fans;
    private double average_stars;
    private int compliment_hot;
    private int compliment_more;
    private int compliment_profile;
    private int compliment_cute;
    private int compliment_list;
    private int compliment_note;
    private int compliment_plain;
    private int compliment_cool;
    private int compliment_funny;
    private int compliment_writer;
    private int compliment_photos;
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(user_id);
        dataOutput.writeInt(review_count);
        dataOutput.writeInt(useful);
        dataOutput.writeInt(funny);
        dataOutput.writeInt(cool);
        dataOutput.writeUTF(friends);
        dataOutput.writeInt(fans);
        dataOutput.writeDouble(average_stars);
        dataOutput.writeInt(compliment_hot);
        dataOutput.writeInt(compliment_more);
        dataOutput.writeInt(compliment_profile);
        dataOutput.writeInt(compliment_cute);
        dataOutput.writeInt(compliment_list);
        dataOutput.writeInt(compliment_note);
        dataOutput.writeInt(compliment_plain);
        dataOutput.writeInt(compliment_cool);
        dataOutput.writeInt(compliment_funny);
        dataOutput.writeInt(compliment_writer);
        dataOutput.writeInt(compliment_photos);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        user_id = dataInput.readUTF();
        review_count = dataInput.readInt();
        useful = dataInput.readInt();
        funny = dataInput.readInt();
        cool = dataInput.readInt();
        friends = dataInput.readUTF();
        fans = dataInput.readInt();
        average_stars = dataInput.readDouble();
        compliment_hot = dataInput.readInt();
        compliment_more = dataInput.readInt();
        compliment_profile = dataInput.readInt();
        compliment_cute = dataInput.readInt();
        compliment_list = dataInput.readInt();
        compliment_note = dataInput.readInt();
        compliment_plain = dataInput.readInt();
        compliment_cool = dataInput.readInt();
        compliment_funny = dataInput.readInt();
        compliment_writer = dataInput.readInt();
        compliment_photos = dataInput.readInt();
    }
    public void setUserCleaningTuple (User user){
        this.user_id = user.getUser_id();
        this.review_count = user.getReview_count();
        this.useful = user.getUseful();
        this.funny = user.getFunny();
        this.cool = user.getCool();
        this.friends = user.getFriends().equals("None") ? "" : user.getFriends();
        this.fans = user.getFans();
        this.average_stars = user.getAverage_stars();
        this.compliment_hot = user.getCompliment_hot();
        this.compliment_more = user.getCompliment_more();
        this.compliment_profile = user.getCompliment_profile();
        this.compliment_cute = user.getCompliment_cute();
        this.compliment_list = user.getCompliment_list();
        this.compliment_note = user.getCompliment_note();
        this.compliment_plain = user.getCompliment_plain();
        this.compliment_cool = user.getCompliment_cool();
        this.compliment_funny = user.getCompliment_funny();
        this.compliment_writer = user.getCompliment_writer();
        this.compliment_photos = user.getCompliment_photos();
    }
    @Override
    public String toString(){
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                user_id,
                review_count,
                useful,
                funny,
                cool,
                friends,
                fans,
                average_stars,
                compliment_hot,
                compliment_more,
                compliment_profile,
                compliment_cute,
                compliment_list,
                compliment_note,
                compliment_plain,
                compliment_cool,
                compliment_funny,
                compliment_writer,
                compliment_photos
                );
    }
}
