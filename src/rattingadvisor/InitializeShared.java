/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rattingadvisor;

import java.io.File;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Anibal
 */
public class InitializeShared {
    
    public InitializeShared(JTextArea log, JTextField rattingSystem, JSpinner jumps){
        
        //Populate Shared Variables
        Shared shared = new Shared();
        IntelFileFinder intelFinder = new IntelFileFinder();
        FileManager fileManager = new FileManager();
        String raw = fileManager.ReadFile("settings.cfg");//Read the settings file
        String[] variables = raw.split("\n");
        String[] value;
        String[] orderedValues = new String[7];
        for (int i = 0; i < variables.length; i++) {
            
            value = variables[i].split("=");
            orderedValues[i] = value[1];
            
        }
        
        //SET Default values
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
        shared.setMapDbPath("Deklein.db");
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
        //End Populate Shared Variables
        
        //Set Default Values for the MainWindow TextAreas
        rattingSystem.setText(shared.getRattingSystemName());
        jumps.setValue(shared.getMaxJumpsNumber());
        //END Set Default Values for the MainWindow TextAreas
        
        //Launch the GetSystems Thread
        Thread getSystemsThread = new GetSystemsThread();
        getSystemsThread.start();
        
    }
    
}
