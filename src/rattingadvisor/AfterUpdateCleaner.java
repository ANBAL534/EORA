/*
 * AfterUpdateCleaner.java

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
import org.apache.commons.io.*;

/**
 *
 * @author Anibal
 */
public class AfterUpdateCleaner {
    
    Shared shared = new Shared();
    
    public void clean(){
        
        File updateCacheRootFolder = new File("UpdateCache/");
        
        try {
            
            FileUtils.deleteDirectory(updateCacheRootFolder);
            new File("UpdateCache").mkdir();
            
        } catch (Exception e) {
            shared.getDbUtils().log("Error cleaning update installation resources...");
        }
        
    }
    
}
