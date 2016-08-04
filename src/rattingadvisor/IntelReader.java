package rattingadvisor;

import java.util.ArrayList;

/**
 *
 * @author Anibal
 */
public class IntelReader {
    
    //Variables
    private final String intelFile;
    private String charInfoSource;
    
    private ArrayList<Integer> reportTime;
    private ArrayList<String> reporter;
    private ArrayList<String> systemReported;
    private ArrayList<String> extraInfo;
    private String[] starSystems;
    
    public IntelReader(String intelFilePath, String mapPath){
        
        intelFile = intelFilePath;
        charInfoSource = "";
        
        reportTime = new ArrayList<Integer>();
        reporter = new ArrayList<String>();
        systemReported = new ArrayList<String>();
        extraInfo = new ArrayList<String>();
        
        starSystems = getSystems(mapPath);
        
        updateIntelArrays();
        
    }
    
    public String getCharInfoSource() {
        return charInfoSource;
    }
    
    private void updateIntelArrays(){
        
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
        nameSplitted = rawSplitted[10].split(" ");//In the 8th line there is the char name
        //As the name is also splited by spaces, from the 10th space there is the name
        for (int i = 10; i < nameSplitted.length; i++)
            charInfoSource += nameSplitted[i] + " ";

        //The first 26 lines are static and not useful
        for (int i = 26; i < rawSplitted.length-3; i++) {
            
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
                for (int j = 0; j < starSystems.length; j++) {
                    
                    if(halved[1].contains(starSystems[j]))
                        systemReported.add(starSystems[j]);
                    
                }
                
                //If in the below for there is no coincidende, we add message to the report
                if(systemReported.size() != reporter.size())
                    systemReported.add("System not in map");
                //End get the reported system
                
                extraInfo.add(halved[1]);//We add the original report
                
            }
            
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
        
        lastReport[0] = reportTime.get(reportTime.size()-1) + "";
        lastReport[1] = reporter.get(reporter.size()-1);
        lastReport[2] = systemReported.get(systemReported.size()-1);
        lastReport[3] = extraInfo.get(extraInfo.size()-1);
        
        return lastReport;
        
    }
    
    //Tells if the report supplied is the latest reported in intel
    public boolean isLastReport(String[] report){

        int comparationTime;
        
        updateIntelArrays();
        comparationTime = Integer.parseInt(report[0]);
        
        if(comparationTime == reportTime.get(reportTime.size()-1))
            return true;
        return false;
        
    }
    
    private String[] getSystems(String mapPath){
        
        /*
        * Go through the map file and add to an array all the non-empty systems
        */
        
        FileManager fileManager = new FileManager();
        
        String raw;
        String[] rawSplitted;
        ArrayList<String> starSystems = new ArrayList<String>();
        
        raw = fileManager.ReadFile(mapPath);
        raw = fileManager.ISOtoUTF(raw);
        
        rawSplitted = raw.split(" ");
        
        for (int i = 0; i < rawSplitted.length; i++) {
            
            if(!rawSplitted[i].equals("EMPTY"))
                starSystems.add(rawSplitted[i]);
            
        }
        
        return starSystems.toArray(new String[starSystems.size()-1]);
        
    }
    
}
