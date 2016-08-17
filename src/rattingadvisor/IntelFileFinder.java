/*
 * IntelFileFinder.java

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
        long greatest;
        int indexOfGreatest;
        String lastFileName;
        
        shared = new Shared();
        dir = new File(pathToChatLogDir);
        intelLogFiles = new ArrayList<String>();
        intelLogDates = new ArrayList<String>();
        
        if(!dir.isDirectory()) {
        
            shared.getLogTextArea().setText(shared.getLogTextArea().getText() + "\nInvalid chat log directory, check the path in settings.");
            return "";
        
        }
        
        logs = dir.listFiles();//Get a list with all the chat logs
        for (int i = 0; i < logs.length; i++) {
            
            //Only save the ones with the Intel file name
            if(logs[i].getName().startsWith(intelChannelName))
                intelLogFiles.add(logs[i].getName());
            
        }
        
        //Erase the file extension of the list
        for (int i = 0; i < intelLogFiles.size(); i++) {
            
            intelLogFiles.set(i, intelLogFiles.get(i).substring(0, intelLogFiles.get(i).length()-4));
            
        }
        
        //Split the chanels names from its dates
        for (int i = 0; i < intelLogFiles.size(); i++) {
            
            nameSplitted = intelLogFiles.get(i).split("_");
            intelLogDates.add(nameSplitted[nameSplitted.length-2] + nameSplitted[nameSplitted.length-1]);
            
        }
        
        greatest = Long.parseLong(intelLogDates.get(0));
        indexOfGreatest = 0;
        //Get the greatest date
        for (int i = 0; i < intelLogDates.size(); i++) {
            
            if(greatest < Long.parseLong(intelLogDates.get(i))){
            
                greatest = Long.parseLong(intelLogDates.get(i));
                indexOfGreatest = i;
            
            }
            
        }
        
        lastFileName = pathToChatLogDir + "/" + intelLogFiles.get(indexOfGreatest) + ".txt";
        return lastFileName;
        
    }
    
}
