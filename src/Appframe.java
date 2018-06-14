import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class Appframe extends JFrame{
	private static Appframe StudentAlarm;
	
	private Appframe(){
		this.setSize(500, 500);
		buildAlarm pnl = new buildAlarm();		
		this.add(pnl.getProduct());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	public static Appframe getFrame(){
		if(StudentAlarm == null){
			StudentAlarm = new Appframe();
		}
		return StudentAlarm;
	}
}
