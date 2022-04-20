package tip;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Tip {
    private String user_id;
    private String business_id;
    private String text_content;
    private String date_time;
    private int compliment_count;

    public String getUser_id() {
        return user_id;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public String getText_content() {
        return text_content;
    }

    public String getDate_time() {
        return date_time;
    }

    public int getCompliment_count() {
        return compliment_count;
    }


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public void setCompliment_count(int compliment_count) {
        this.compliment_count = compliment_count;
    }

    public String toJsonText(){
        Gson g = new Gson();
        return g.toJson(this);
    }

    public static Tip JsonAnalysis(String text){
        Gson g = new Gson();
        return g.fromJson(text, Tip.class);
    }

    public static Field[] getFields(){
        return Tip.class.getDeclaredFields();
    }

    public Map<String, Object> getTipMap() throws IllegalAccessException {
        Map<String, Object> r = new HashMap<String, Object>();
        Field[] fields = Tip.getFields();
        for (int i = 0; i < fields.length; i++) {
            Object v = fields[i].get(this);
            r.put(fields[i].getName(), v);
        }
        return r;
    }
}