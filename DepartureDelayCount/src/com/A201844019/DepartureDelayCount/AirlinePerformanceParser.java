package com.A201844019.DepartureDelayCount;

import org.apache.hadoop.io.Text;

public class AirlinePerformanceParser {
	private int year;
	private int month;
	private int ArriveDelayTime;
	private int departureDelayTime = 0;
	private boolean departureDelayAvallable = true;
	private boolean isCancelledAvailable = false;
	private Text uniqueCarrierCode = new Text();

	

	
	public AirlinePerformanceParser(Text text) {
		try {
			String[] colums = text.toString().split(",");
			this.year = Integer.parseInt(colums[0]);
			this.month = Integer.parseInt(colums[1]);
			uniqueCarrierCode.set(colums[8]);
			if (!colums[14].equals("NA")) {
				this.ArriveDelayTime = Integer.parseInt(colums[14]);
			}

			if (!colums[15].equals("NA")) {
				this.departureDelayTime = Integer.parseInt(colums[15]);
			} else {
				this.departureDelayAvallable = false;
			}
			if (!colums[21].equals("0")) {
				isCancelledAvailable = true;
			}
		} catch (Exception var3) {
			System.out.println("Error parsing a record :" + var3.getMessage());
		}

	}

	public int getYear() {
		return this.year;
	}

	public int getMonth() {
		return this.month;
	}

	public int getDepartureDelayTime() {
		return this.departureDelayTime;
	}

	public int getArriveDelayTime() {
		return this.ArriveDelayTime;
	}

	public boolean isDepartureDelayAvilable() {
		return this.departureDelayAvallable;
	}
	
	public boolean getCancelled() {
		return isCancelledAvailable;
	}
	
	public Text getUniqueCarrierCode() {
		return uniqueCarrierCode;
	}

}
