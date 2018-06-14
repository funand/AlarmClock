import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.*;

public class SleepPattern {
	Alarm alarm;
	Timer runTime;
	Time sleptAt, wokeUp;
	ArrayList<String> collectedData;
	Alerts color, reminder, wakeUp, showReport;
	StoredTxTFile dataStorage;

	public SleepPattern()
	{
		alarm = new Alarm();
		sleptAt = new Time();
		wokeUp  = new Time();
		color = new Alerts();
		reminder = new Alerts();
		wakeUp = new Alerts();
		showReport = new Alerts();
		collectedData = new ArrayList<String>();
		runTime = new Timer();
		dataStorage =  new StoredTxTFile();
	}

	public void currentTime()
	{
		Time time = new Time();
		time.setToCurrentTime();
		alarm.setCurrentTime(time);
	}

	public void goingToSleep()
	{
		sleptAt.setToCurrentTime();
	}//3

	private boolean overSlept()
	{
		if(sleptAt.d.after(alarm.toWakeUp.d))
			return true;
		else
			return false;
	}

	public void wokeUpAt(){
		wokeUp.setToCurrentTime();
	}//4

	public void setAlarm(Date date) {
		alarm.setToWakeUp(date);
	}//1

	public void setReminder(Date date){
		alarm.timeToSleep(date);
	}//2

	public void recommendation(){

		Time currently = new Time();
		currently.setToCurrentTime();

		long sleeping = alarm.getTimeToSleep().getMilli();
		long current = currently.getMilli();

		if(Math.abs(current-sleeping)>300000 && Math.abs(current-sleeping)<600000){//using milliseconds
			color.display("Sleep in 5 minutes", Color.YELLOW);		//yellow Alert
		}

		else if(Math.abs(current-sleeping)<300000){
			color.display("Sleep Now!!!", Color.RED);//red Alert
		}

	}//5

	public void run(){
		runTime.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				currentTime();
					recommendation();
					remindStudent();
				alarmStudent();
			}
		}, 0, 6000);  //60000 miliseconds = 1 minutes
	}

	public void remindStudent(){
		Time k = new Time();
		k.setToCurrentTime();
		Time reminderTime = alarm.getTimeToSleep();
		if (k.greater(reminderTime)){
			reminder.display("REMINDER::SLEEP NOW!!!!", Color.GREEN);
		}
	}


	public void alarmStudent(){
		Time current = new Time();
		current.setToCurrentTime();

		Time toAwake  = alarm.getToWakeUp();
		if (current.greater(toAwake)){
			wakeUp.display("Wake UP NOW!!!!", Color.BLUE);
		}
	}

	public void store(){
		collectedData.add("Current: " + alarm.getCurrent() );
		collectedData.add("Wake Up Time: " + alarm.getToWakeUp());
		collectedData.add("Reminded to Sleep: "+alarm.getTimeToSleep());
		collectedData.add("Slept at: "+ sleptAt);
		collectedData.add("WokeUp At: " + wokeUp);
		dataStorage.store(collectedData);
	}

	public void report()
	{
		showReport.display("Ignored recommendation alerts: " + (color.getSkippedAlert())
					+ "\n Skipped reminder alerts: " + (reminder.getSkippedAlert())
					+ "\n Skipped wake up alerts " + (wakeUp.getSkippedAlert()), Color.cyan);
	}
}
