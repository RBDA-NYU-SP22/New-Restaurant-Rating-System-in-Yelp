package Review;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ReviewMapper
    extends Mapper<LongWritable, Text, Text, Text> {

  private static final int MISSING = 9999;

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    String jsondata = value.toString();
    jsondata = jsondata.replace("{", "");
    jsondata = jsondata.replace("}","");
    String jparts[] = jsondata.split(",\"");
    String finalResult = "";

    for(String jvalue: jparts){
      jvalue = jvalue.replace("\"","");
      String tuple[] = jvalue.split(":");
      if(tuple.length!=2) {
        context.write(new Text(tuple[0]), new Text(""));
      }else{
        //check if the value is out of bound(i.e 0.0<=stars<=5.0, useful>=0, funny>=0, cool>=0). If it is, then skip writing it to the context. 
        if(tuple[0] == "stars"){
          if(Double.parseDouble(tuple[1])<0 || Double.parseDouble(tuple[1])>5) continue;
        }else{
          if (tuple[0] == "useful" || tuple[0] == "funny" || tuple[0] == "cool") {
            if (Integer.parseInt((tuple[1])) < 0) continue;
          }
        }

        context.write(new Text(tuple[0]), new Text(tuple[1]));
      }
    }


  }
}
