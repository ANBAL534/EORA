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
    
    private Shared shared = new Shared();
    
    public String[] mapSearcher(final String startSystem, final int jumpsMax){
        
        //Variables
        ArrayList<String> notSearchedYetJumps = new ArrayList<String>();
        ArrayList<String> inRange = new ArrayList<String>();
        String[] finalInRange;
        int jumpsAway = 0;
        
        /*
        * Map Search process:
        *
        * As a simple idea the process is, given a map, we will take all the
        * possible routes around the starting system until we get as far as
        * the system limit says, saving all the systems we visit in a "in range"
        * list which later we will compare with the intel.
        */
        notSearchedYetJumps.add(startSystem + "/" + jumpsAway);
        
        while(jumpsAway <= jumpsMax){
            
            //Get all connections of the origin
            final String[] row = shared.getDbUtils().getRowFromOrigin(notSearchedYetJumps.get(0).split("/")[0]);
            
            //Add all connections to the noSearchedYet list if they are not yet there
            for (int i = 1; i < row.length; i++) {//Jump over origin
                
                
                
            }
            
        }
        
        //Return a standar array with the in range systems
        finalInRange = inRange.toArray(new String[inRange.size()-1]);
        return finalInRange;
        
    }
    
}
