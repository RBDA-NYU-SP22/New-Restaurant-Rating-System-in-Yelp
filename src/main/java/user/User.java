package user;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String user_id;
    private String name;
    private int review_count;
    private String yelping_since;
    private int useful;
    private int funny;
    private int cool;
    private String elite;
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

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public int getReview_count() {
        return review_count;
    }

    public String getYelping_since() {
        return yelping_since;
    }

    public int getUseful() {
        return useful;
    }

    public int getFunny() {
        return funny;
    }

    public int getCool() {
        return cool;
    }

    public String getElite() {
        return elite;
    }

    public String getFriends() {
        return friends;
    }

    public int getFans() {
        return fans;
    }

    public double getAverage_stars() {
        return average_stars;
    }

    public int getCompliment_hot() {
        return compliment_hot;
    }

    public int getCompliment_more() {
        return compliment_more;
    }

    public int getCompliment_profile() {
        return compliment_profile;
    }

    public int getCompliment_cute() {
        return compliment_cute;
    }

    public int getCompliment_list() {
        return compliment_list;
    }

    public int getCompliment_note() {
        return compliment_note;
    }

    public int getCompliment_plain() {
        return compliment_plain;
    }

    public int getCompliment_cool() {
        return compliment_cool;
    }

    public int getCompliment_funny() {
        return compliment_funny;
    }

    public int getCompliment_writer() {
        return compliment_writer;
    }

    public int getCompliment_photos() {
        return compliment_photos;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public void setYelping_since(String yelping_since) {
        this.yelping_since = yelping_since;
    }

    public void setUseful(int useful) {
        this.useful = useful;
    }

    public void setFunny(int funny) {
        this.funny = funny;
    }

    public void setCool(int cool) {
        this.cool = cool;
    }

    public void setElite(String elite) {
        this.elite = elite;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public void setAverage_stars(double average_stars) {
        this.average_stars = average_stars;
    }

    public void setCompliment_hot(int compliment_hot) {
        this.compliment_hot = compliment_hot;
    }

    public void setCompliment_more(int compliment_more) {
        this.compliment_more = compliment_more;
    }

    public void setCompliment_profile(int compliment_profile) {
        this.compliment_profile = compliment_profile;
    }

    public void setCompliment_cute(int compliment_cute) {
        this.compliment_cute = compliment_cute;
    }

    public void setCompliment_list(int compliment_list) {
        this.compliment_list = compliment_list;
    }

    public void setCompliment_note(int compliment_note) {
        this.compliment_note = compliment_note;
    }

    public void setCompliment_plain(int compliment_plain) {
        this.compliment_plain = compliment_plain;
    }

    public void setCompliment_cool(int compliment_cool) {
        this.compliment_cool = compliment_cool;
    }

    public void setCompliment_funny(int compliment_funny) {
        this.compliment_funny = compliment_funny;
    }

    public void setCompliment_writer(int compliment_writer) {
        this.compliment_writer = compliment_writer;
    }

    public void setCompliment_photos(int compliment_photos) {
        this.compliment_photos = compliment_photos;
    }

    public String toJsonText(){
        Gson g = new Gson();
        return g.toJson(this);
    }

    public static User JSON2User(String text){
        Gson g = new Gson();
        return g.fromJson(text, User.class);
    }

    public static Field[] getFields(){
        return User.class.getDeclaredFields();
    }

    public Map<String, Object> getUserMap() throws IllegalAccessException {
        Map<String, Object> r = new HashMap<String, Object>();
        Field[] fields = User.getFields();
        for (int i = 0; i < fields.length; i++) {
            Object v = fields[i].get(this);
            r.put(fields[i].getName(), v);
        }
        return r;
    }
}
