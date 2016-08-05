/*
 * MapLogic.java

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

/**
 *
 * @author Anibal
 */
public class MapLogic {
    
    String[][] map;//100x100 is the standar map size
    FileManager fileManager = new FileManager();
    
    public MapLogic(String mapPath){
        
        //Variables
        String raw;
        String[] splittedRaw;
        String[][] finalRaw = new String[100][100];
        
        /*
        * Map load process:
        *
        * The map file is a one-line system names chain splited by spaces.
        * The map file is 10,000 words long, they need to be sepparated and 
        * reorganized in order to use the systemSearcher method.
        *
        * The file will be splited and saved in the 100x100 array by lines.
        */
        raw = fileManager.ReadFile(mapPath);
        splittedRaw = raw.split(" ");
        
        int x = 0;
        int y = 0;
        for (int i = 0; i < splittedRaw.length; i++) {
            
            finalRaw[y][x] = splittedRaw[i];
            
            if(x == finalRaw.length-1){
                
                x = -1;
                y++;
                
                if(y == finalRaw.length){
                    
                    break;
                    
                }
                
            }
            
            x++;
            
        }
        
        map = finalRaw;
        
    }
    
    public String[] mapSearcher(String startSystem, int jumpsMax){
        
        //Variables
        ArrayList<String> notSearchedYet = new ArrayList<String>();
        ArrayList<String> searched = new ArrayList<String>();
        ArrayList<String> inRange = new ArrayList<String>();
        String[] finalInRange;
        int xOrigin = 0, yOrigin = 0;
        int jumpsAway = 0;
        
        /*
        * Map Search process:
        *
        * As a simple idea the process is, given a map, we will take all the
        * possible routes around the starting system until we get as far as
        * the system limit says, saving all the systems we visit in a "in range"
        * list which later we will compare with the intel.
        *
        * The way of programming that is a bit more complex.
        * as a staring point, we have 3 lists, one for the pending for search 
        * systems, other for the searched ones, and the last for the in range
        * systems.
        * We need a not searched yet because we have to save all the systems 
        * connected to the origin point (if a system is connected to other 6 we
        * have to save that 6 systems for latter exploration), then we
        */
        notSearchedYet.add(startSystem + "/" + jumpsAway);
        
        while(jumpsAway <= jumpsMax){
            
            //Search for the origin coordinates
            for (int i = 0; i < map.length; i++) {
                
                for (int j = 0; j < map.length; j++) {
                    
                    if(map[i][j].equals(notSearchedYet.get(0).split("/")[0])){
                        
                        xOrigin = j;
                        yOrigin = i;
                        jumpsAway = Integer.parseInt(notSearchedYet.get(0).split("/")[1]);
                        
                    }
                    
                }
                
            }
            
            if(map[yOrigin][xOrigin].startsWith("EKS")){
                
                //Search for the systems around the origin point in a X shape
                if(!map[yOrigin+1][xOrigin+1].equals("EMPTY") && !searched.contains(map[yOrigin+1][xOrigin+1])){//UP-RIGHT

                    if(map[yOrigin+1][xOrigin+1].startsWith("ANCLA") || map[yOrigin+1][xOrigin+1].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin+1][xOrigin+1] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin+1][xOrigin+1] + "/" + (jumpsAway+1));

                    }

                }

                if(!map[yOrigin-1][xOrigin-1].equals("EMPTY") && !searched.contains(map[yOrigin-1][xOrigin-1])){//DOWN-LEFT

                    if(map[yOrigin-1][xOrigin-1].startsWith("ANCLA") || map[yOrigin-1][xOrigin-1].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin-1][xOrigin-1] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin-1][xOrigin-1] + "/" + (jumpsAway+1));

                    }

                }

                if(!map[yOrigin+1][xOrigin-1].equals("EMPTY") && !searched.contains(map[yOrigin+1][xOrigin-1])){//UP-LEFT

                    if(map[yOrigin+1][xOrigin-1].startsWith("ANCLA") || map[yOrigin+1][xOrigin-1].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin+1][xOrigin-1] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin+1][xOrigin-1] + "/" + (jumpsAway+1));

                    }

                }

                if(!map[yOrigin-1][xOrigin+1].equals("EMPTY") && !searched.contains(map[yOrigin-1][xOrigin+1])){//DOWN-RIGHT

                    if(map[yOrigin-1][xOrigin+1].startsWith("ANCLA") || map[yOrigin-1][xOrigin+1].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin-1][xOrigin+1] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin-1][xOrigin+1] + "/" + (jumpsAway+1));

                    }

                }
                
            }else{
                
                //Search for the systems around the origin point in all directions
                if(!map[yOrigin+1][xOrigin].equals("EMPTY") && !searched.contains(map[yOrigin+1][xOrigin])){//UP

                    /*
                    * As "ANCLA" is a special system for technical reasons, we doesn't
                    * take it into account in the jumps calculations.
                    */
                    if(map[yOrigin+1][xOrigin].startsWith("ANCLA") || map[yOrigin+1][xOrigin].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin+1][xOrigin] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin+1][xOrigin] + "/" + (jumpsAway+1));

                    }

                }
                if(!map[yOrigin-1][xOrigin].equals("EMPTY") && !searched.contains(map[yOrigin-1][xOrigin])){//DOWN

                    if(map[yOrigin-1][xOrigin].startsWith("ANCLA") || map[yOrigin-1][xOrigin].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin-1][xOrigin] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin-1][xOrigin] + "/" + (jumpsAway+1));

                    }

                }
                if(!map[yOrigin][xOrigin+1].equals("EMPTY") && !searched.contains(map[yOrigin][xOrigin+1])){//RIGHT

                    if(map[yOrigin][xOrigin+1].startsWith("ANCLA") || map[yOrigin][xOrigin+1].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin][xOrigin+1] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin][xOrigin+1] + "/" + (jumpsAway+1));

                    }

                }

                if(!map[yOrigin][xOrigin-1].equals("EMPTY") && !searched.contains(map[yOrigin][xOrigin-1])){//LEFT

                    if(map[yOrigin][xOrigin-1].startsWith("ANCLA") || map[yOrigin][xOrigin-1].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin][xOrigin-1] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin][xOrigin-1] + "/" + (jumpsAway+1));

                    }

                }

                if(!map[yOrigin+1][xOrigin+1].equals("EMPTY") && !searched.contains(map[yOrigin+1][xOrigin+1])){//UP-RIGHT

                    if(map[yOrigin+1][xOrigin+1].startsWith("ANCLA") || map[yOrigin+1][xOrigin+1].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin+1][xOrigin+1] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin+1][xOrigin+1] + "/" + (jumpsAway+1));

                    }

                }

                if(!map[yOrigin-1][xOrigin-1].equals("EMPTY") && !searched.contains(map[yOrigin-1][xOrigin-1])){//DOWN-LEFT

                    if(map[yOrigin-1][xOrigin-1].startsWith("ANCLA") || map[yOrigin-1][xOrigin-1].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin-1][xOrigin-1] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin-1][xOrigin-1] + "/" + (jumpsAway+1));

                    }

                }

                if(!map[yOrigin+1][xOrigin-1].equals("EMPTY") && !searched.contains(map[yOrigin+1][xOrigin-1])){//UP-LEFT

                    if(map[yOrigin+1][xOrigin-1].startsWith("ANCLA") || map[yOrigin+1][xOrigin-1].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin+1][xOrigin-1] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin+1][xOrigin-1] + "/" + (jumpsAway+1));

                    }

                }

                if(!map[yOrigin-1][xOrigin+1].equals("EMPTY") && !searched.contains(map[yOrigin-1][xOrigin+1])){//DOWN-RIGHT

                    if(map[yOrigin-1][xOrigin+1].startsWith("ANCLA") || map[yOrigin-1][xOrigin+1].startsWith("EKS")){

                        notSearchedYet.add(map[yOrigin-1][xOrigin+1] + "/" + (jumpsAway));

                    }else{

                        notSearchedYet.add(map[yOrigin-1][xOrigin+1] + "/" + (jumpsAway+1));

                    }

                }
                
            }
                
            
            //As we have already populated the notsearchedyet list, se mark the 
            //origin system as searched and in range and remove it from notsearchedyet
            if(notSearchedYet.get(0).startsWith("ANCLA") || notSearchedYet.get(0).startsWith("EKS")){
                
                searched.add(notSearchedYet.get(0));
                notSearchedYet.remove(0);
                
            }else{
                
                searched.add(notSearchedYet.get(0));
                inRange.add(notSearchedYet.get(0));
                notSearchedYet.remove(0);
                
            }
            
        }
        
        //Return a standar array with the in range systems
        finalInRange = inRange.toArray(new String[inRange.size()-1]);
        return finalInRange;
        
    }
    
}
