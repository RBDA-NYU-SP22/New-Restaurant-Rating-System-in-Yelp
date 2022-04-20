package tip;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TipReducer
        extends Reducer<Text, TipSet, Text, TipSet> {
    private TipSet outputres = new TipSet();
    @Override
    public void reduce(Text key, Iterable<TipSet> values, Context context)
            throws IOException, InterruptedException {
        outputres.setMin(Double.MAX_VALUE);
        outputres.setMax(Double.MIN_VALUE);
        outputres.setCount(0);
        int sum = 0;
        try{
            for (TipSet value : values) {

                switch (key.toString()) {
                    case "compliment_count" -> {
                        outputres.setMax(Math.max(outputres.getMax(), outputres.getMax()));
                        outputres.setMin(Math.min(outputres.getMin(), outputres.getMin()));
                    }

                    case "user_id", "business_id", "text_content", "date_time", default -> {
                        outputres.setMax(0);
                        outputres.setMin(0);
                    }
                }
                sum += value.getCount();
            }
            outputres.setCount(sum);
            context.write(key, outputrest);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}