import java.util.Date;

public class Alarm {
	Time toWakeUp;
	Time goToSleep;
	Time currentTime;
	boolean asleep;
	
	public Alarm() {
		this.currentTime = new Time();
		this.toWakeUp = new Time();
		this.goToSleep = new Time(); 
	}

	public void setCurrentTime(Time currentTime) {
		this.currentTime = currentTime;
	}
	
	public Time getCurrent(){
		return currentTime;
	}
	
	public Time getToWakeUp() {
		return toWakeUp;
	}

	public void setToWakeUp(Date date) {
		this.toWakeUp.setDateTime(date);
	}
	
	public void timeToSleep(Date date){
		this.goToSleep.setDateTime(date);
	}
	
	public Time getTimeToSleep() {
		return goToSleep;
	}

	public String toString() {
		return "Time is: " + currentTime.toString()+" You are to wake up at "+ toWakeUp.toString();
	}

	
}
