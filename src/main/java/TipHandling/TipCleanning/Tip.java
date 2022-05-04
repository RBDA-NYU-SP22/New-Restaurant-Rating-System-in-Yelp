import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Tip {
    private String user_id;
    private String business_id;
    private String text;
    private String date;
    private int compliment;

    public String getUser_id() {
        return user_id;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public int getCompliment() {
        return compliment;
    }


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCompliment(int compliment) {
        this.compliment = compliment;
    }

    public static Tip JsonAnalysis(String text){
        Gson g = new Gson();
        return g.fromJson(text, Tip.class);
    }

    public static Field[] getFields(){
        return Tip.class.getDeclaredFields();
    }

    //This is taught by Xiaohan Wu(xw2278)
    public Map<String, Object> getTipMap() throws IllegalAccessException {
        Map<String, Object> mp = new HashMap<String, Object>();
        Field[] fields = Tip.getFields();
        for (int i = 0; i < fields.length; i++) {
            Object obj = fields[i].get(this);
            mp.put(fields[i].getName(), obj);
        }
        return mp;
    }
}
