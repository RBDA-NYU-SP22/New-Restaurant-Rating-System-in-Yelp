package tip;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TipSet implements Writable {
    private double max;
    private double min;
    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max){
        this.max = max;
    }

    public void setMax(int max){
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min){
        this.min = min;
    }

    public void setMin(int min){
        this.min = min;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException{
        dataOutput.writeDouble(max);
        dataOutput.writeDouble(min);
        dataOutput.writeLong(count);
    }
    @Override
    public void readFields(DataInput dataInput) throws IOException{
        max = dataInput.readDouble();
        min = dataInput.readDouble();
        count = dataInput.readLong();
    }
    @Override
    public String toString(){
        return String.format("%10s %10s %10s", max, min, count);
    }

}