package rattingadvisor;

import java.util.ArrayList;

/**
 *
 * @author Anibal
 */
public class IntelReader {
    
    public final String intelFile;
    public String charInfoSource;
    private ArrayList<Integer> reportTime;
    private ArrayList<String> reporter;
    private ArrayList<String> systemReported;
    private ArrayList<String> extraInfo;
    
    public IntelReader(String intelFilePath){
        
        intelFile = intelFilePath;
        charInfoSource = "";
        
        reportTime = new ArrayList<Integer>();
        reporter = new ArrayList<String>();
        systemReported = new ArrayList<String>();
        extraInfo = new ArrayList<String>();
        
    }
    
    public void updateIntelArrays(){
        
        FileManager fileManager = new FileManager();
        
        String raw;
        String[] rawSplitted;
        String[] nameSplitted;
        
        raw = fileManager.ReadFile(intelFile);
        raw = fileManager.ISOtoUTF(raw);
        
        rawSplitted = raw.split("\n");
        nameSplitted = rawSplitted[16].split(" ");//In the 8th line there is the char name
        //As the name is also splited by spaces, from the 10th space there is the name
        for (int i = 10; i < nameSplitted.length; i++)
            charInfoSource += nameSplitted[i] + " ";

        //The first 26 lines are static and not useful
        for (int i = 26; i < rawSplitted.length; i++) {
            
            //The odd lines are always empty
            if((i%2)==0){
                
                int time = 0;
                String rest;
                String y;

                String[] x = rawSplitted[i].split("]");
                y = x[0];

                x = y.split(" ");
                y = x[2];

                x = y.split(":");
                time += Integer.parseInt(x[0]);
                time = (time*60)*60;
                time += (Integer.parseInt(x[1])*60);
                time += Integer.parseInt(x[2]);
                
            }
            
        }
        
    }
    
    //Returns the last reported item in the Intel Channel
    public String[] getLastReport(){
        
        
        
    }
    
    //Tells if the report supplied is the latest reported in intel
    public boolean isLastReport(String[] report){
        
        
        
    }
    
}
