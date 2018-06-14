import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

interface Ibuilder {	
	void displayPanel();

	void buildBtns();
	void buildPickers();
	void bldAlarm();
	void buildReport();
	JPanel getProduct();
}

class buildAlarm implements Ibuilder{
	
	JPanel container, alarmSection, report;
	DatePicker wakeUp, sleepReminder;
	SleepPattern sleepPattern;
	String date;
	Time display;  
	Timer test;
	JButton alarm, remindBtn, reportBtn, aboutTOSleep, wokenUp;
	

	public void displayPanel() 
	{
		JTextField defaultTime = new JTextField("Current "+display.toString());		
		defaultTime.setEditable(false);
		container.add(defaultTime);
	}

	public void bldAlarm()
	{	
		alarmSection = new JPanel();
		alarmSection.setLayout(new GridLayout(3, 2));
		
		buildBtns();
		buildPickers();
		sleepPattern = new SleepPattern();
		
		alarmSection.add(alarm);//
		alarmSection.add(wakeUp);//
		alarmSection.add(remindBtn);//
		alarmSection.add(sleepReminder);//
		alarmSection.add(aboutTOSleep);//
		alarmSection.add(wokenUp);//
	}	
	
	public void buildBtns()
	{
		alarm = new JButton("Set Wake Up Time");
		remindBtn = new JButton("Set reminder for sleep: ");
		aboutTOSleep = new JButton("Record Time(Going to sleep)");
		wokenUp= new JButton("Record (Woken Up)");
		
		alarm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				sleepPattern.currentTime();
				sleepPattern.setAlarm(wakeUp.buildDate());	
			}
		});
		
		remindBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sleepPattern.setReminder(sleepReminder.buildDate());
				sleepPattern.run();	
			}
		});
		
		aboutTOSleep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sleepPattern.goingToSleep();
			}
		});
		
		wokenUp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sleepPattern.wokeUpAt();
			}
		});
	}
	
	public void buildPickers()
	{
		wakeUp = new DatePicker();
		sleepReminder = new DatePicker();
	}
	
	public void buildReport()
	{
		report = new JPanel();
		report.setLayout(new GridLayout(1, 1));
		reportBtn = new JButton("Provide report");
		reportBtn.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent e) 
			{
				sleepPattern.store();
				sleepPattern.report();
			}
		});
		
		report.add(reportBtn);
	}
	
	public void setup(){
		container = new JPanel();
		container.setLayout(new GridLayout(3, 1));
		
		display = new Time();
		test = new Timer(0, null);
		display.setToCurrentTime();
		
		displayPanel();
		
		alarmSection = new JPanel();
		bldAlarm();
		buildReport();
		container.add(alarmSection);
		container.add(report);
	}

	@Override
	public JPanel getProduct() {
		setup();
		buildBtns();
		buildPickers();
		bldAlarm();
		buildReport();
		return container;
	}
	
}