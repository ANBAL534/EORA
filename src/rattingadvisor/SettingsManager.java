/*
 * SettingsManager.java

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

import java.util.ArrayList;

/**
 *
 * @author Anibal
 */
public class SettingsManager {
                                    // 8 in total
    private String[] defaultKeys = {"ChatLogsPath", 
                                    "IntelChannel", 
                                    "RattingSystem", 
                                    "JumpsNumber", 
                                    "AlarmSound", 
                                    "CheckLocal", 
                                    "CheckShield", 
                                    "Style"};

    FileManager fileManager = new FileManager();
    Shared shared = new Shared();
    
    public String[] getDefaultKeys() {
        return defaultKeys;
    }
    
    public String getSetting(String key){
        
        if(fileManager.FileExist("settings.cfg")){
            
            String full = fileManager.ReadFile("settings.cfg");
            
            String[] splitted = full.split("\n");
            int index = -1;
            for (int i = 0; i < splitted.length; i++) {
                
                if(splitted[i].contains(key)){
                
                    index = i;
                    break;
                
                }
                
            }
            
            try {
                
                if(index >= 0){

                    splitted = splitted[index].split("=");
                    return splitted[1];

                }else{

                    return "SETTING NOT FOUND";

                }
                
            } catch (Exception e) {
                
                System.out.println("Out of bounds exception probably");
                System.out.println(e.toString());
                
            }
            
        }else{
            
            shared.getDbUtils().log("Settings file not found");
            return "FILE NOT FOUND";
            
        }
        
        return "ERROR";
        
    }
    
    public String[] getOrderedValues(){
        
        String full = fileManager.ReadFile("settings.cfg");
            
        String[] splitted = full.split("\n");
        ArrayList<String> orderedValues = new ArrayList<String>();
        
        for (int i = 0; i < splitted.length; i++) {

            orderedValues.add(splitted[i].split("=")[1]);

        }
        
        return orderedValues.toArray(new String[orderedValues.size()]);
        
    }
    
    public String[] getOrderedKeys(){
        
        String full = fileManager.ReadFile("settings.cfg");
            
        String[] splitted = full.split("\n");
        ArrayList<String> orderedKeys = new ArrayList<String>();
        
        for (int i = 0; i < splitted.length; i++) {

            orderedKeys.add(splitted[i].split("=")[0]);

        }
        
        return (String[])orderedKeys.toArray();
        
    }
    
    public void setSetting(String key, String value){
        
        SettingsManager settingsManager = new SettingsManager();
        
        String modified = key + "=" + value;
        String full = fileManager.ReadFile("settings.cfg");
        String[] splitted = full.split("\n");
        
        String fullModified = "";
        for (int i = 0; i < splitted.length; i++) {
            
            if(splitted[i].split("=")[0].equals(key)){
                
                fullModified += modified + "\n";
                
            }else{
                
                fullModified += splitted[i] + "\n";
                
            }
            
        }
        
        fullModified = fullModified.trim();
        fileManager.WriteFile("settings.cfg", fullModified);
        
    }
    
    public void addSetting(String key, String value){
        
        //First check if the setting is already written
        if(getSetting(key).equals("SETTING NOT FOUND")){
            
            fileManager.AppendFile("settings.cfg", key + "=" + value + "\n");
            
        }
        
    }
    
    public void setDefaults(){
        
        //Need to change every time we add new settings
        fileManager.WriteFile("settings.cfg",   "ChatLogsPath=None\n" +
                                                "IntelChannel=delve.imperium\n" +
                                                "RattingSystem=Q-02UL\n" +
                                                "JumpsNumber=3\n" +
                                                "AlarmSound=None\n" +
                                                "CheckLocal=FALSE\n" +
                                                "CheckShield=FALSE\n" +
                                                "Style=LIGHT");
        
    }
    
    public int numberOfSettings(){
        
        String full = fileManager.ReadFile("settings.cfg");
        String[] splitted = full.split("\n");
        
        return splitted.length;
        
    }
    
}
