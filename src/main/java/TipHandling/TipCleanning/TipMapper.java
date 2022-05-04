package TipHandling.TipCleanning;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

public class TipMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        try {
            Tip tip = Tip.JsonAnalysis(value.toString());
            Map<String, Object> tipMap = tip.getTipMap();
            String out="";
            int count=0;
            for (Map.Entry<String, Object> mpentry: tipMap.entrySet()) {
                switch (mpentry.getKey()) {
                    case "text":
                       String tmp=mpentry.getValue().toString();
                       if (count==0) out+=tmp.length();
                       else out+=";"+tmp.length();
                       count++;
                       break;
		            case "user_id":
                    case "business_id":
                    case "date":
                       //context.write(NullWritable.get(),new Text(mpentry.getValue()+"123"));
		                if (count==0) out+=mpentry.getValue().toString();
                       else out+=";"+mpentry.getValue().toString();
                       count++;
                       break;
                }
                if (count==4) {
                   context.write(NullWritable.get(), new Text(out));
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
