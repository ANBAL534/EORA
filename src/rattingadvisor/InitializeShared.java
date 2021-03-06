/*
 * InitializeShared.java

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

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Anibal
 */
public class InitializeShared extends Thread{
    
    JTextArea log;
    JTextField rattingSystem;
    JSpinner jumps;
    
    public InitializeShared(JTextArea log, JTextField rattingSystem, JSpinner jumps){
        
        this.log = log;
        this.rattingSystem = rattingSystem;
        this.jumps = jumps;
        
    }
    
    @Override
    public void run(){
        
        try {
            
            //Populate Shared Variables
            Shared shared = new Shared();
            IntelFileFinder intelFinder = new IntelFileFinder();
            FileManager fileManager = new FileManager();
            SettingsManager settingsManager = new SettingsManager();
            
            String[] orderedValues = settingsManager.getOrderedValues();

            //SET Default values
            if(settingsManager.getSetting("IntelChannel").equals("GOTG_Intel")){
                //If the intel channel still beign the old one we reset the settings file
                settingsManager.setDefaults();
            }
            
            //Add all the settings that may be missing in the file
            settingsManager.regenerateSettings();
            
            if(orderedValues[0].equals("None"))
                orderedValues[0] = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "EVE" + File.separatorChar + "logs" + File.separatorChar + "Chatlogs";
            if(Integer.parseInt(orderedValues[3]) < 1)
                orderedValues[3] = "3";
            if(orderedValues[4].equals("None"))
                orderedValues[4] = "pilarManAwaken.wav";
            //END set default values

            shared.setChatLogsPath(orderedValues[0]);
            shared.setIntelChannelName(orderedValues[1]);
            shared.setRattingSystemName(orderedValues[2]);
            shared.setMaxJumpsNumber(Integer.parseInt(orderedValues[3]));
            shared.setAlarmSoundPath(orderedValues[4]);
            shared.setCheckLocal(false);
            shared.setCheckShield(false);
            shared.setMapDbPath("EVEMap.db");
            shared.setDbUtils(new DbUtils(shared.getMapDbPath(), log));
            shared.setLogTextAreaMainWindow(log);
            shared.setRattingSystemTextMainWindow(rattingSystem);
            shared.setMaxJumpsMainWindow(jumps);
            shared.setFileManager(new FileManager());
            shared.setMapLogic(new MapLogic());
            shared.setIntelReader(new IntelReader(intelFinder.pathToLastIntelFile(shared.getChatLogsPath(), shared.getIntelChannelName())));
            shared.setStillSearching(true);
            shared.setSystemsInRange(shared.getMapLogic().mapSearcher(shared.getRattingSystemName(), shared.getMaxJumpsNumber()));
            shared.setKeepsGettingSystems(false);
            shared.setStopTimerMusic(new ArrayList<Boolean>());
            shared.setIncludeClear(Boolean.valueOf(settingsManager.getSetting("IncludeClear")));
            //End Populate Shared Variables

            //Set Default Values for the MainWindow TextAreas
            rattingSystem.setText(shared.getRattingSystemName());
            jumps.setValue(shared.getMaxJumpsNumber());
            //END Set Default Values for the MainWindow TextAreas

            //Launch the GetSystems Thread
            Thread getSystemsThread = new GetSystemsThread();
            getSystemsThread.start();
            
            while (getSystemsThread.isAlive());
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "It seems that the instalation of the program is incomplete. (Initialization error)\nOne or more files are missing in the folder.\n\nPress OK to exit.", "Installation Error", JOptionPane.OK_OPTION);
            System.out.println(e.toString());
            System.exit(-1);
            
        }
        
    }
    
}
