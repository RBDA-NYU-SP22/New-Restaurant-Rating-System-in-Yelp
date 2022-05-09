package Review;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReviewReducer
    extends Reducer<Text, Text, NullWritable, Text> {
  
  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
    

    int sum = 0;
    for (Text value : values) {
      sum++;
    }
    String output = key.toString() + " " + sum;
    context.write(NullWritable.get(), new Text(output));
  }
}
