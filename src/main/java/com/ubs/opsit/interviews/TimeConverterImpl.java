package com.ubs.opsit.interviews;

import java.util.stream.Stream;

public class TimeConverterImpl implements TimeConverter {

	@Override
	public String convertTime(String aTime) {
		int[] parts = Stream.of(aTime.split(":")).mapToInt(Integer::parseInt).toArray();
		return (getSeconds(parts[2])+"\r\n"+ getTopHours(parts[0])+"\r\n"+getBottomHours(parts[0])+"\r\n"+getTopMinutes(parts[1])+"\r\n"+getBottomMinutes(parts[1]));
	}
	protected String getSeconds(int number) {
		return (number % 2 == 0) ? TimeConstants.Y.toString(): TimeConstants.O.toString();		 
	}

	protected String getTopHours(int number) {
		return getOnOff(4, getTopNumberOfOnSigns(number));
	}

	protected String getBottomHours(int number) {
		return getOnOff(4, number % 5);
	}

	protected String getTopMinutes(int number) {
		return getOnOff(11, getTopNumberOfOnSigns(number), TimeConstants.Y).replaceAll(TimeConstants.YYY.toString(),
				TimeConstants.YYR.toString());
	}

	protected String getBottomMinutes(int number) {
		return getOnOff(4, number % 5, TimeConstants.Y);
	}

	private String getOnOff(int lamps, int onSigns) {
		return getOnOff(lamps, onSigns, TimeConstants.R);
	}

	private String getOnOff(int lamps, int onSigns, TimeConstants y) {
		String out = "";
 		
		for (int i = 0; i < onSigns; i++) {
			out += y;
		}
		for (int i = 0; i < (lamps - onSigns); i++) {
			out += TimeConstants.O;
		}
		return out;
	}

	private int getTopNumberOfOnSigns(int number) {
		return (number - (number % 5)) / 5;
	}

}
