/*
 * Shared.java

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

import javax.swing.JTextArea;

/**
 *
 * This class if intended to be as a joint point between the window clases and
 * the logic ones, also for the threads to take and share information.
 * 
 * @author Anibal
 */
public class Shared {
    
    //Shared Variables
    private static volatile String chatLogsPath;
    private static volatile String mapDbPath;
    private static volatile String intelChannelName;
    private static volatile String rattingSystemName;
    private static volatile int maxJumpsNumber;
    private static volatile String alarmSoundPath;
    private static volatile boolean checkLocal;
    private static volatile boolean checkShield;
    private static volatile JTextArea logTextArea;
    private static volatile FileManager fileManager;
    private static volatile MapLogic mapLogic;
    private static volatile IntelReader intelReader;
    private static volatile DbUtils dbUtils;
    private static volatile String[] systemsInRange;
    private static volatile boolean stillSearching;
    private static volatile boolean keepsGettingSystems;
    private static volatile String[] allSystems;
    //End Shared Variables

    public String[] getAllSystems() {
        return allSystems;
    }

    public void setAllSystems(String[] allSystems) {
        Shared.allSystems = allSystems;
    }

    public boolean isKeepsGettingSystems() {
        return keepsGettingSystems;
    }

    public void setKeepsGettingSystems(boolean aKeepsGettingSystems) {
        keepsGettingSystems = aKeepsGettingSystems;
    }

    public DbUtils getDbUtils() {
        return dbUtils;
    }

    public void setDbUtils(DbUtils aDbUtils) {
        
        dbUtils = aDbUtils;
        
        //Open DataBase
        getDbUtils().newOpenDb();
        
    }

    public boolean isStillSearching() {
        return stillSearching;
    }

    public void setStillSearching(boolean aKeepSearching) {
        stillSearching = aKeepSearching;
    }

    public String getChatLogsPath() {
        return chatLogsPath;
    }

    public String getMapDbPath() {
        return mapDbPath;
    }

    public String getIntelChannelName() {
        return intelChannelName;
    }

    public String getRattingSystemName() {
        return rattingSystemName;
    }

    public int getMaxJumpsNumber() {
        return maxJumpsNumber;
    }

    public String getAlarmSoundPath() {
        return alarmSoundPath;
    }

    public boolean isCheckLocal() {
        return checkLocal;
    }

    public boolean isCheckShield() {
        return checkShield;
    }

    public JTextArea getLogTextArea() {
        return logTextArea;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public MapLogic getMapLogic() {
        return mapLogic;
    }

    public IntelReader getIntelReader() {
        return intelReader;
    }

    public void setChatLogsPath(String chatLogsPath) {
        this.chatLogsPath = chatLogsPath;
    }

    public void setMapDbPath(String mapDbPath) {
        this.mapDbPath = mapDbPath;
    }

    public void setIntelChannelName(String intelChannelName) {
        this.intelChannelName = intelChannelName;
    }

    public void setRattingSystemName(String rattingSystemName) {
        this.rattingSystemName = rattingSystemName;
    }

    public void setMaxJumpsNumber(int maxJumpsNumber) {
        this.maxJumpsNumber = maxJumpsNumber;
    }

    public void setAlarmSoundPath(String alarmSoundPath) {
        this.alarmSoundPath = alarmSoundPath;
    }

    public void setCheckLocal(boolean checkLocal) {
        this.checkLocal = checkLocal;
    }

    public void setCheckShield(boolean checkShield) {
        this.checkShield = checkShield;
    }

    public void setLogTextArea(JTextArea logTextArea) {
        this.logTextArea = logTextArea;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void setMapLogic(MapLogic mapLogic) {
        this.mapLogic = mapLogic;
    }

    public void setIntelReader(IntelReader intelReader) {
        this.intelReader = intelReader;
    }

    public String[] getSystemsInRange() {
        return systemsInRange;
    }

    public void setSystemsInRange(String[] systemsInRange) {
        this.systemsInRange = systemsInRange;
    }
    
}
