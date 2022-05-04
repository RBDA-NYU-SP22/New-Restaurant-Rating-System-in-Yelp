import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TipReducer
        extends Reducer<Text, TipSet, Text, TipSet> {
    private TipSet outputres = new TipSet();
    @Override
    public void reduce(Text key, Iterable<TipSet> values, Context context)
            throws IOException, InterruptedException {
        outputres.setMin(Integer.MAX_VALUE);
        outputres.setMax(0);
        outputres.setSum(0);
        outputres.setAverage(0);
        outputres.setCount(0);
        long tot_count = 0;
        long tot_sum = 0;
        for (TipSet value : values) {
            switch (key.toString()) {

                case "user_id":
                case "business_id":
                //case "compliment":
                case "text":
                    outputres.setMax(Math.max(outputres.getMax(), value.getMax()));
                    outputres.setMin(Math.min(outputres.getMin(), value.getMin()));
                    break;
                case "date":
                    outputres.setMax(Math.max(outputres.getMax(), value.getMax()));
                    outputres.setMin(Math.min(outputres.getMin(), value.getMin()));
                    outputres.setSum(0);
                    outputres.setAverage(0);
                    break;
                default:
                    break;
            }
            tot_count += value.getCount();
            tot_sum += value.getSum();
        }
        outputres.setCount(tot_count);
        outputres.setSum(tot_sum);
        outputres.setAverage(tot_sum/tot_count);
        if (!(key.toString().equals("compliment"))) context.write(key, outputres);
    }
}
