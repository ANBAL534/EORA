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
                                    // 9 in total
    private String[] defaultKeys = {"ChatLogsPath", 
                                    "IntelChannel", 
                                    "RattingSystem", 
                                    "JumpsNumber", 
                                    "AlarmSound", 
                                    "CheckLocal", 
                                    "CheckShield", 
                                    "Style",
                                    "IncludeClear"};
    
    private String[] defaultValues = {"None", 
                                      "delve.imperium", 
                                      "Q-02UL", 
                                      "3", 
                                      "None", 
                                      "FALSE", 
                                      "FALSE", 
                                      "LIGHT",
                                      "TRUE"};

    FileManager fileManager = new FileManager();
    Shared shared = new Shared();
    
    public String[] getDefaultKeys() {
        return defaultKeys;
    }
    
    public String[] getDefaultValues() {
        return defaultValues;
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
        
        String ordered[] = orderedKeys.toArray(new String[1]);
        return ordered;
        
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
        
        String settingsDefault = "";
        for (int i = 0; i < defaultKeys.length; i++) {
            
            settingsDefault += getDefaultKeys()[i] + "=" + getDefaultValues()[i] + "\n";
            
        }
        
        fileManager.WriteFile("settings.cfg", settingsDefault);
        
    }
    
    public int numberOfSettings(){
        
        String full = fileManager.ReadFile("settings.cfg");
        String[] splitted = full.split("\n");
        
        return splitted.length;
        
    }
    
    //Add the keys that are not pressent in the settings file with default values
    public void regenerateSettings(){
        
        //Create a list of the settings to be added
        boolean toRegenerate[] = new boolean[getDefaultKeys().length];
        String defaultKeys[] = getDefaultKeys();
        String orderedKeys[] = getOrderedKeys();
        for (int i = 0; i < defaultKeys.length; i++) {
            
            boolean regenerate = true;
            
            for (int j = 0; j < orderedKeys.length; j++) {
                
                if(defaultKeys[i].equals(orderedKeys[j])){
                    
                    regenerate = false;
                    
                }
                
            }
            
            toRegenerate[i] = regenerate;
            
        }
        
        //Build the string of the pairs
        String settingsDefault = "";
        String defaultValues[] = getDefaultValues();
        for (int i = 0; i < toRegenerate.length; i++) {
            
            if(toRegenerate[i]){
                
                settingsDefault += defaultKeys[i] + "=" + defaultValues[i] + "\n";
                
            }
            
        }
        
        fileManager.AppendFile("settings.cfg", settingsDefault);
        
    }
    
}
