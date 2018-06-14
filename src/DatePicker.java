import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

class DatePicker extends JPanel {
    private JComboBox months, days, hours, minutes, seconds;
    
    private String[] monthsArr = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };
    private String[] hoursArr = new String[23];
    private String[] rest = new String[60];
    private ComboBoxModel[] models = new ComboBoxModel[12];
    
    
    public DatePicker() {
        this.setLayout(new GridLayout(1, 5));    
        init();
    }
    
    private void init() {
        months = new JComboBox(monthsArr);
        
        months.addActionListener(new ActionListener () {

            @Override
            public void actionPerformed(ActionEvent e) {
                days.setModel(getDayModel(months.getSelectedIndex()));
            }
            
        });
        
        fillBoxModels();
        fillHours();
        fillTime();
        
        days = new JComboBox(models[0]);
        hours = new JComboBox(hoursArr);
        minutes = new JComboBox(rest);
        seconds = new JComboBox(rest);
        
        setCurrentTime();
        
        this.add(months);
        this.add(days);
        this.add(hours);
        this.add(minutes);
        this.add(seconds);
    }
    
    private void fillHours() {
        for(int i = 1; i <= hoursArr.length; i++) {
            hoursArr[i - 1] = "" + i;
        }
    }
    
    private void fillTime() {
        for(int i = 0; i < rest.length; i++) {
            rest[i] = "" + i;
        }
    }
    
    private void fillBoxModels() {
        for(int i = 0; i < 12; i++) {
            
            YearMonth ymObj = YearMonth.of(2018, i + 1);
            String[] days = new String[ymObj.lengthOfMonth()];
            
            for(int j = 1; j <= ymObj.lengthOfMonth(); j++){
                days[j - 1] = "" + j;
            }
            
            models[i] = new DefaultComboBoxModel(days); 
        }
        
    }
    private ComboBoxModel getDayModel(int month) {
        return models[month];
    }
    
    @SuppressWarnings("deprecation")
    public Date buildDate() {
        int year = 2018;
        int month = months.getSelectedIndex();
        int day = days.getSelectedIndex() + 1;
        int hour = hours.getSelectedIndex() + 1;
        int minute = minutes.getSelectedIndex();
        int second = seconds.getSelectedIndex();
        Calendar d = Calendar.getInstance();
        d.set(year, month, day, hour, minute, second);
        return d.getTime();
    }
    
    public void setCurrentTime() {
        Calendar c = Calendar.getInstance();
        
        months.setSelectedIndex(c.get(Calendar.MONTH));
        days.setSelectedIndex(c.get(Calendar.DAY_OF_MONTH) - 1);
        hours.setSelectedIndex(c.get(Calendar.HOUR_OF_DAY) - 1);
        minutes.setSelectedIndex(c.get(Calendar.MINUTE));
        seconds.setSelectedIndex(c.get(Calendar.SECOND));
    }
    
    
}