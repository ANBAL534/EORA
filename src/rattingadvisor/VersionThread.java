/*
 * VersionThread.java

    Copyright (C) 2017  Anibal Muñoz Calero

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Anibal
 */
public class VersionThread extends Thread{
    
    JTextArea logTextArea;
    int currentVersion;
    JPanel mainPanel;
    
    public VersionThread(int currentVersion, JTextArea logTextArea, JPanel mainPanel){
        
        this.logTextArea = logTextArea;
        this.mainPanel = mainPanel;
        this.currentVersion = currentVersion;
        
    }
    
    @Override
    public void run(){
        
        try {
            
            //Look for new versions
            int newVersion;
            String uri = "http://anibal.grupoedin.com/EORA/current.ver";
            URL versionUrl;
            versionUrl = new URL(uri);
            File versionDest = new File("current.ver");
            FileUtils.copyURLToFile(versionUrl, versionDest);

            //Read the current version information
            BufferedReader br = new BufferedReader(new FileReader(versionDest));
            newVersion = Integer.parseInt(br.readLine());
            br.close();
            
            //Delete version file
            versionDest.delete();
            
            //Test if it's needed to call the updater and call it
            if(newVersion > currentVersion){
                
                //0 if accepted
                int result = JOptionPane.showConfirmDialog(mainPanel, "New version of EVE Online Ratting Advisor is available.\nDo you want to update?", "New version available", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == 0){
                    
                    String[] arguments = {"java", "-jar", "EORA Updater.jar"};
                    Runtime.getRuntime().exec(arguments);
                    System.exit(0);
                    
                }else{
                    
                    new DbUtils("EVEMap.db", logTextArea).log("You are using a DEPRECATED version of Ratting Advisor.");
                    
                }
                
            }else{
                    
                new DbUtils("EVEMap.db", logTextArea).log("You are using the latest version of Ratting Advisor.");
                new AfterUpdateCleaner().clean();
                    
            }
            
        } catch (Exception e) {
            new DbUtils("EVEMap.db", logTextArea).log("The program have been unable of getting the latest version of Ratting Advisor.");
        }
        
    }

}
