/*
 *  GetSystemsThread.java

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author Anibal
 */
public class GetSystemsThread extends Thread{
    
    Shared shared = new Shared();
    
    public void run(){
        
        ArrayList<String> starSystems = new ArrayList<String>();
        String[] lastRow = new String[11];
        
        shared.getDbUtils().log("Loading systems from the database...");
        shared.setKeepsGettingSystems(true);
        
        int i = 1;
        do{
            
            lastRow = shared.getDbUtils().getRowFromId(i);
            starSystems.add(lastRow[0]);
            i++;
            
        }while (lastRow[0] != null);
        
        shared.getDbUtils().log("Loading of systems from the database finished.");
        
        shared.setAllSystems(starSystems.toArray(new String[starSystems.size()-1]));
        shared.setKeepsGettingSystems(false);
        
        //Set all systems
        if(new File(shared.getChatLogsPath()).exists()){
            
            shared.getIntelReader().setStarSystems(shared.getAllSystems());
            shared.getIntelReader().updateIntelArrays();
            shared.getDbUtils().log("\nReading Intel from " + shared.getIntelReader().getCharInfoSource() + "'s session.\nDo not close that session or search for a new one.");
            
        }else{
            
            shared.getDbUtils().log("\n**********************************************\nERROR: CHATLOGS NOT FOUND. PROGRAM WON'T WORK\n**********************************************");
            
        }
        
    }
    
}
