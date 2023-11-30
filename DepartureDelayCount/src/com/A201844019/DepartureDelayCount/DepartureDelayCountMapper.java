package com.A201844019.DepartureDelayCount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DepartureDelayCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private static final IntWritable outputValue = new IntWritable(1);
	private Text outputKey = new Text();

	public void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		AirlinePerformanceParser parser = new AirlinePerformanceParser(value);
		
		if (parser.getCancelled() ==true) {
			context.write(outputKey, outputValue);
		}
		/*this.outputKey.set(parser.getYear() + "," + parser.getMonth());
		if (parser.isDepartureDelayAvilable() && parser.getDepartureDelayTime() > 0) {
			this.outputKey.set("D," + parser.getYear() + "," + parser.getMonth());
			context.write(this.outputKey, outputValue);
		}

		if (parser.isDepartureDelayAvilable() && parser.getArriveDelayTime() > 0) {
			this.outputKey.set("A," + parser.getYear() + "," + parser.getMonth());
			context.write(this.outputKey, outputValue);
		}*/

	}
}
