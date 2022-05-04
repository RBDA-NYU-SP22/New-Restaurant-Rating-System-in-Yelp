package TipHandling.TipProfiling;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TipSet implements Writable {
    private long max;
    private long min;
    private long sum;
    private long count;
    private double average;

    public long getMax() {
        return max;
    }

    public long getMin() {
        return min;
    }

    public long getSum() {
        return sum;
    }

    public long getCount() {
        return count;
    }

    public double getAverage() {
        return average;
    }

    public void setMax(long max){
        this.max = max;
    }

    public void setMin(long min){
        this.min = min;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException{
        dataOutput.writeLong(max);
        dataOutput.writeLong(min);
        dataOutput.writeLong(count);
        dataOutput.writeLong(sum);
        dataOutput.writeDouble(average);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException{
        max = dataInput.readLong();
        min = dataInput.readLong();
        count = dataInput.readLong();
        sum = dataInput.readLong();
        average = dataInput.readDouble();
    }

    @Override
    public String toString(){
        return String.format("%6s %6s %6s %6s %6s", max, min, sum, count, average);
    }

}
