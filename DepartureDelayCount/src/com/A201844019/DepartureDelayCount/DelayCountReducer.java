package com.A201844019.DepartureDelayCount;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class DelayCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();
	private MultipleOutputs<Text, IntWritable> mos;

	public void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		this.mos = new MultipleOutputs(context);
	}

	public void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		Text outputKey = new Text();
		String[] colums = key.toString().split(",");
		outputKey.set(colums[1] + "," + colums[2]);
		int sum;
		IntWritable value;
		Iterator var8;
		if (colums[0].equals("D")) {
			sum = 0;

			for (var8 = values.iterator(); var8.hasNext(); sum += value.get()) {
				value = (IntWritable) var8.next();
			}

			this.result.set(sum);
			this.mos.write("departure", key, this.result);
		} else {
			sum = 0;

			for (var8 = values.iterator(); var8.hasNext(); sum += value.get()) {
				value = (IntWritable) var8.next();
			}

			this.result.set(sum);
			this.mos.write("arrival", key, this.result);
		}

	}

	public void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		this.mos.close();
	}
}
