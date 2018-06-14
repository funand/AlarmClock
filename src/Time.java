import java.text.DateFormat;
import java.util.*;
import java.util.*;
import java.util.GregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Time {
		
	DateFormat df;
	Date d;
	GregorianCalendar now;
	
	public Time() {
		now = new GregorianCalendar();
		df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		d = new Date();
	}
		
	public int minutes(){
		return now.getTime().getMinutes();
	}//for calculating alert recommendation
	
	public void setDateTime(Date date){
		now.setTimeInMillis(date.getTime());
	}
	
	public void setToCurrentTime(){			
		now.setTimeInMillis(System.currentTimeMillis());
	}

	private Date getDateMilis(Time time){
		Date k = new Date();
		k.setTime(time.getMilli());
		return k;
	}
	
	long getMilli(){
		return now.getTimeInMillis();
	}
	
	public boolean greater(Time time)
	{
		return (getMilli() > time.getMilli());		
	}
		
	@Override
	public String toString() {	
		return "Time is " + df.format(now.getTime()) + ".";
	}
}
