import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Alerts extends JFrame{
	private JPanel alertStudent;
	JTextField alert;
	JButton exit;
	boolean pressed;
	
	int skippedAlert;
	
	
	public Alerts() {
		pressed = false;
		this.setSize(400, 250);
		setup();
		this.add(alertStudent);	
		skippedAlert = -1;
	}
	
	public int getSkippedAlert() {
		return skippedAlert;
	}

	private void setup(){
		alertStudent = new JPanel();
		alert = new JTextField();
		exit = new JButton("Close");
	}
	
	public boolean checkPressed()
	{
		return pressed;
	}
	
	public JPanel display(String msg, Color c){	
		if(!checkPressed())
		{
			alert.setText(msg);
	
			alertStudent.add(alert);
			alertStudent.setBackground(c);
			exit.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent e) {
					stopAlerting();	
					pressed = true;
				}
			});
			
			incSkippedAlert();
			alertStudent.add(exit);
			this.setVisible(true);
			return alertStudent;
		}
		
		return null;
	}
	
	private void incSkippedAlert()
	{
		skippedAlert++;
	}
	
	private void stopAlerting()
	{
		pressed = true;
		this.setVisible(false);
		alertStudent.setVisible(false);	
	}
}