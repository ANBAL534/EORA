/*
 * TimerThread.java

    EVE Online Ratting Advisor
    Copyright (C) 2016  Anibal Muñoz Calero

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package rattingadvisor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Anibal
 */
public class TimerThread extends Thread{
    
    Shared shared = new Shared();
    
    int hour;
    int minute;
    int second;
    String id;
    JTextField hourField;
    JTextField minuteField;
    JTextField secondField;
    JFrame frame;
    int stopId;
    
    public TimerThread(int hour, int minute, int second, String id, JTextField hourField, JTextField minuteField, JTextField secondField, JFrame frame, int stopId){
        
        //Initialize variables
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.id = id;
        this.hourField = hourField;
        this.minuteField = minuteField;
        this.secondField = secondField;
        this.frame = frame;
        this.stopId = stopId;
        
    }
    
    public void setVisualTime(){
        
        //Set variables in the visual window's objects
        hourField.setText(hour + "");
        minuteField.setText(minute + "");
        secondField.setText(second + "");
        
    }
    
    public void launchAlarmTimer(){
        
        //We launch the sound and block the thread until Stop button is pressed
        shared.getDbUtils().log("Musica Maestro");
        final Thread sound = new MediaPlayer(shared.getAlarmSoundPath());
        
        sound.start();
        
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setAutoRequestFocus(true);
        
        while (shared.getStopTimerMusic().get(stopId) == false) {
            
            //Blocked
            
        }
        
        sound.stop();
        
    }
    
    @Override
    public void run(){
        /*
        A tipical countdown timer, pausing each cicle a second and showing 
        the new time until the stop variable is set to true.
        */
        while (shared.getStopTimerMusic().get(stopId) == false) {
            
            second--;
            
            if(second < 0){
                
                minute--;
                second = 59;
                
            }
                
            if(minute < 0){
                
                hour--;
                minute = 59;
                
            }
            
            if(hour < 0){
                
                hour = 0;
                minute = 0;
                second = 0;
                launchAlarmTimer();
                break;
                
            }
            
            setVisualTime();
            
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
}
