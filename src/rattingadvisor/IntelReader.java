/*
 * IntelReader.java

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

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Anibal
 */
public class IntelReader {
    
    //Variables
    private Shared shared = new Shared();
    
    private final String intelFile;
    private String charInfoSource;
    
    private ArrayList<Integer> reportTime;
    private ArrayList<String> reporter;
    private ArrayList<String> systemReported;
    private ArrayList<String> extraInfo;
    private String[] starSystems;
    
    public IntelReader(String intelFilePath){
        
        intelFile = intelFilePath;
        charInfoSource = "";
        
        reportTime = new ArrayList<Integer>();
        reporter = new ArrayList<String>();
        systemReported = new ArrayList<String>();
        extraInfo = new ArrayList<String>();
        
        if(starSystems != null)
            updateIntelArrays();
        
    }
    
    public String getCharInfoSource() {
        return charInfoSource;
    }
    
    public void setStarSystems(String[] systems){
        
        starSystems = systems;
        
    }
    
    public void updateIntelArrays(){
        
        /*
        * Report example:
        * ﻿[ 2016.07.31 11:13:33 ] Some One > E-FIC0  Another One nv
        * ﻿[ 2016.07.31 11:13:34 ] Some One > Another One nv E-FIC0
        */
        
        FileManager fileManager = new FileManager();
        
        String raw;
        String[] rawSplitted;
        String[] nameSplitted;
        
        raw = fileManager.ReadFile(intelFile);
        raw = fileManager.ISOtoUTF(raw);
        
        //Erase the arrays as we rewrite them completely
        reportTime = new ArrayList<Integer>();
        reporter = new ArrayList<String>();
        systemReported = new ArrayList<String>();
        extraInfo = new ArrayList<String>();
        
        rawSplitted = raw.split("\n");
        nameSplitted = rawSplitted[10].split(" ");//In the 10th line there is the char name
        //As the name is also splited by spaces, from the 10th(16th in Linux) space there is the name
        for (int i = 10; i < nameSplitted.length; i++)
            charInfoSource += nameSplitted[i] + " ";
        
        try {
            
            //The first 16(26 in Linux) lines are static and not useful
            for (int i = 14; i < rawSplitted.length-3; i++) {
            
                //The odd lines are always empty
                if((i%2)==0){

                    int time = 0;
                    String splitted;
                    String[] splittedText;
                    String[] halved;
                    String report;

                    //Get the time of the report
                    halved = rawSplitted[i].split("]");
                    splitted = halved[0];

                    halved = splitted.split(" ");
                    splitted = halved[2];

                    halved = splitted.split(":");
                    time += Integer.parseInt(halved[0]);
                    time = (time*60)*60;
                    time += (Integer.parseInt(halved[1])*60);
                    time += Integer.parseInt(halved[2]);
                    reportTime.add(time);
                    //End get the time of the report

                    //Get the reporter
                    halved = rawSplitted[i].split(" ] ");
                    splitted = halved[1];
                    halved = splitted.split(" > ");
                    reporter.add(halved[0]);
                    //End get the reporter

                    //Get the reported System
                    for (int j = 0; j < starSystems.length-1; j++) {

                        if(halved[1].contains(starSystems[j])){

                            systemReported.add(starSystems[j]);
                            break;

                        }

                    }

                    //If in the below for there is no coincidende, we add message to the report
                    if(systemReported.size() < reporter.size())
                        systemReported.add("System not in map");
                    //End get the reported system

                    extraInfo.add(halved[1]);//We add the original report

                }

            }
            
        } catch (Exception e) {
            
            shared.getDbUtils().log("\n**ERROR READING INTEL LOG FILE - RESTART EVE ONLINE**");
            shared.getDbUtils().log("**This is a EVE Online's problem writting log files. \nRestart at least one EVE client and search for a new char's session**");
            
            final Thread sound = new MediaPlayer(shared.getAlarmSoundPath());
            sound.start();
            JOptionPane.showMessageDialog(null, "ERROR READING INTEL LOG FILE - RESTART EVE ONLINE\nMore info at Log panel.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            sound.stop();
            
            throw new Error("ERROR READING INTEL LOG FILE - RESTART EVE ONLINE");
            
        }
        
    }
    
    //Returns the last reported item in the Intel Channel
    public String[] getLastReport(){
        
        /*
        * Report array order:
        *      [0]             [1]              [2]           [3]
        * time in seconds, reporter name, system reported, extra info
        */
        
        String[] lastReport = new String[4];
        
        updateIntelArrays();
        
        try {
            lastReport[0] = reportTime.get(reportTime.size()-1) + "";
            lastReport[1] = reporter.get(reporter.size()-1);
            lastReport[2] = systemReported.get(systemReported.size()-1);
            lastReport[3] = extraInfo.get(extraInfo.size()-1);
        } catch (Exception e) {
            
            shared.getDbUtils().log("**ERROR GETTING THE LAST REPORT - RESTART THE PROGRAM**");
            shared.getDbUtils().log("It's being investigated. Search for a new char's session.");
            
        }
        
        return lastReport;
        
    }
    
    //Tells if the report supplied is the latest reported in intel
    public boolean isLastReport(String[] report){

        int comparationTime;
        
        updateIntelArrays();
        
        try {
            
            comparationTime = Integer.parseInt(report[0]);
            
            if(comparationTime == reportTime.get(reportTime.size()-1))
                return true;
            return false;
            
        } catch (Exception e) {
           shared.getDbUtils().log("**ERROR PARSING TIME FROM LAST REPORT - RESTART THE PROGRAM**");
           shared.getDbUtils().log("It's being investigated. Restart the application.");
        }
        
        return false;
        
    }
  
}
