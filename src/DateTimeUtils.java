

import java.util.Date;


public class DateTimeUtils {
    private static final long ONE_HOUR_IN_MS = 3600000;
    private static final long ONE_MIN_IN_MS = 60000;
    private static final long ONE_SEC_IN_MS = 1000;

    public static Date sumTimeToDate(Date date, int hours, int mins, int secs) {
        long hoursToAddInMs = hours * ONE_HOUR_IN_MS;
        long minsToAddInMs = mins * ONE_MIN_IN_MS;
        long secsToAddInMs = secs * ONE_SEC_IN_MS;
        return new Date(date.getTime() + hoursToAddInMs + minsToAddInMs + secsToAddInMs);
    }
    
    
	
	//1 minute = 60 seconds
	//1 hour = 60 x 60 = 3600
	//1 day = 3600 x 24 = 86400
    /**
     * Print out the time difference between the two dates
     * @param startDate
     * @param endDate
     */
	public void printDifference(Date startDate, Date endDate){
	
		//milliseconds
		long different = endDate.getTime() - startDate.getTime();
		
		System.out.println("startDate : " + startDate);
		System.out.println("endDate : "+ endDate);
		System.out.println("different : " + different);
		
		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;
		
		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;
		
		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;
		
		long elapsedSeconds = different / secondsInMilli;
		
		System.out.printf(
		    "%d days, %d hours, %d minutes, %d seconds%n", 
		    elapsedDays,
		    elapsedHours, elapsedMinutes, elapsedSeconds);
		
	
	}
	
}
