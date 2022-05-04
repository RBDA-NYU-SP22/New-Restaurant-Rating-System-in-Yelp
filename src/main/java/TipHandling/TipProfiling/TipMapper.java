import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

public class TipMapper extends Mapper<LongWritable, Text, Text, TipSet> {

    private TipSet outputres = new TipSet();

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        try {
            Tip tip = Tip.JsonAnalysis(value.toString());
            Map<String, Object> tipMap = tip.getTipMap();
            for (Map.Entry<String, Object> mpentry: tipMap.entrySet()) {
                switch (mpentry.getKey()) {
                    case "user_id":
                    case "business_id":
//                    case "compliment":
                    case "text":
                        String tmp1 = mpentry.getValue().toString();
                        outputres.setMax(tmp1.length());
                        outputres.setMin(tmp1.length());
                        outputres.setSum(tmp1.length());
                        outputres.setAverage(0);
                        break;
                    case "date":
                        String tmp2 = mpentry.getValue().toString();
                        outputres.setMax(tmp2.length());
                        outputres.setMin(tmp2.length());
                        outputres.setSum(0);
                        outputres.setAverage(0);
                        break;
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
