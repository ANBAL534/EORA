/*
 * SearcherThread.java

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

/**
 *
 * @author Anibal
 */
public class SearcherThread extends Thread{
    
    Shared shared = new Shared();
    String[] currentReport;
    
    @Override
    public void run(){
        
        /*
        * Report array order:
        *      [0]             [1]              [2]           [3]
        * time in seconds, reporter name, system reported, extra info
        */
        currentReport = shared.getIntelReader().getLastReport();
        
        do{
            
            if(!shared.getIntelReader().isLastReport(currentReport)){
                
                currentReport = shared.getIntelReader().getLastReport();
                
                for (int i = 0; i < shared.getSystemsInRange().length; i++) {
                    //Test if the system written in the report is in range
                    if(currentReport[2].equals(shared.getSystemsInRange()[i])){
                        //if it is, we have to check if we want to launch an alarm
                        //for status/?/clr/clear/etc.
                        if(!shared.includeClear() && !(currentReport[3].toLowerCase().contains("status") || currentReport[3].toLowerCase().contains("pls") || currentReport[3].toLowerCase().contains("?") || currentReport[3].toLowerCase().contains("please") || currentReport[3].toLowerCase().contains("clr") || currentReport[3].toLowerCase().contains("clear"))){
                            
                            new AlertLauncher().launchAlarm(currentReport);
                            shared.getLogTextAreaMainWindow().setText(shared.getLogTextAreaMainWindow().getText() + "\n**********************\nNeutral detected in range!\nReporter: " + currentReport[1] + "\nSystem: " + currentReport[2] + "\nFull Message: \n\"" + currentReport[3] + "\"\n**********************");
                            
                        }
                        
                        if(shared.includeClear()){
                            
                            new AlertLauncher().launchAlarm(currentReport);
                            shared.getLogTextAreaMainWindow().setText(shared.getLogTextAreaMainWindow().getText() + "\n**********************\nNeutral detected in range!\nReporter: " + currentReport[1] + "\nSystem: " + currentReport[2] + "\nFull Message: \n\"" + currentReport[3] + "\"\n**********************");
                            
                        }
                        
                    }
                    
                }
                
                shared.getLogTextAreaMainWindow().setText(shared.getLogTextAreaMainWindow().getText() + "\n**********************\nNeutral detected\nReporter: " + currentReport[1] + "\nSystem: " + currentReport[2] + "\nFull Message: \n\"" + currentReport[3] + "\"\n**********************");
                
            }
            
            shared.getLogTextAreaMainWindow().setCaretPosition(shared.getLogTextAreaMainWindow().getDocument().getLength());
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(SearcherThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }while(shared.isStillSearching());
        
    }
    
}
