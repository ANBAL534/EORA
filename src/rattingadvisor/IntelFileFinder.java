package rattingadvisor;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author administrador
 */
public class IntelFileFinder {
    
    public String pathToLastIntelFile(String pathToChatLogDir, String intelChannelName){
        
        //Variables
        File dir;
        File[] logs;
        ArrayList<String> intelLogFiles;
        ArrayList<String> intelLogDates;
        Shared shared;
        String[] nameSplitted;
        int greatest;
        int indexOfGreatest;
        String lastFileName;
        
        shared = new Shared();
        dir = new File(pathToChatLogDir);
        intelLogFiles = new ArrayList<String>();
        intelLogDates = new ArrayList<String>();
        
        if(!dir.isDirectory()) 
            shared.getLogTextArea().setText(shared.getLogTextArea().getText() + "\nInvalid chat log directory, check the path in settings.");
        
        logs = dir.listFiles();//Get a list with all the chat logs
        for (int i = 0; i < logs.length; i++) {
            
            //Only save the ones with the Intel file name
            if(logs[i].getName().startsWith(intelChannelName))
                intelLogFiles.add(logs[i].getName());
            
        }
        
        //Erase the file extension of the list
        for (int i = 0; i < intelLogFiles.size(); i++) {
            
            intelLogFiles.get(i).substring(0, intelLogFiles.size()-5);
            
        }
        
        //Split the chanels names from its dates
        for (int i = 0; i < intelLogFiles.size(); i++) {
            
            nameSplitted = intelLogFiles.get(i).split("_");
            intelLogDates.add(nameSplitted[1] + nameSplitted[2]);
            
        }
        
        greatest = Integer.parseInt(intelLogDates.get(0));
        indexOfGreatest = 0;
        //Get the greatest date
        for (int i = 0; i < intelLogDates.size(); i++) {
            
            if(greatest < Integer.parseInt(intelLogDates.get(i))){
            
                greatest = Integer.parseInt(intelLogDates.get(i));
                indexOfGreatest = i;
            
            }
            
        }
        
        lastFileName = pathToChatLogDir + "\\" + intelLogFiles.get(indexOfGreatest) + ".txt";
        return lastFileName;
        
    }
    
}
