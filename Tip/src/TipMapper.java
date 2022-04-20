package tip;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

public class TipMapper extends Mapper<LongWritable, Text, Text, TipSet> {

    private static final int MISSING = 0;
    private TipSet outputres = new TipSet();

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        try {
            Tip tip = Tip.JsonAnalysis(value.toString());
            Map<String, Object> tipMap = tip.getTipMap();
            for (Map.Entry<String, Object> mpentry: tipMap.entrySet()) {

                switch (mpentry.getKey()) {
                    case "compliment_count":
                        outputres.setMax(Integer.parseInt(me.getValue().toString()));
                        outputres.setMin(Integer.parseInt(me.getValue().toString()));
                        break;
                    case "user_id":
                    case "business_id":
                    case "text_content":
                    case "date_time":
                    default:
                        break;
                }
                outputres.setCount(1);
                context.write(new Text(mpentry.getKey()), outputres);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}