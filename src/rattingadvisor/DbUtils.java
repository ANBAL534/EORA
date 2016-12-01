/*
 *  DbUtils.java

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

import java.sql.*;
import javax.swing.JTextArea;

/**
 *
 * @author Anibal
 */
public class DbUtils {
    
    JTextArea textArea;
    String dbName;
    Connection conn;
    Shared shared;

    public DbUtils(String databaseName, JTextArea logArea) {
    
        textArea = logArea;
        dbName = databaseName;
        conn = null;
        
        try {
            
            Class.forName("org.sqlite.JDBC");
            
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    
    }
    
    public void log(String log){
        
        textArea.append("\n" + log);
        textArea.setCaretPosition(textArea.getDocument().getLength());
        
    }
    
    public void closeDb(){
        
        try {
            
            conn.close();
            
        } catch (SQLException ex) {
            log(ex.getMessage());
        }
        
    }
    
    public void newOpenDb(){
        
        boolean exists;
        Statement stmt = null;
        
        try {
            
            exists = new FileManager().FileExist(dbName);
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            stmt = conn.createStatement();
            log("Opened " + dbName + " database successfully.");
            
            if(!exists){//If db didn't exist we create the tables
                
                String sql = "CREATE TABLE DEKLEIN "
                        + "(ID INTEGER PRIMARY KEY   AUTOINCREMENT,"
                        + "ORIGIN       TEXT     NOT NULL,"
                        + "CONNECTION1  TEXT,"
                        + "CONNECTION2  TEXT,"
                        + "CONNECTION3  TEXT,"
                        + "CONNECTION4  TEXT,"
                        + "CONNECTION5  TEXT,"
                        + "CONNECTION6  TEXT,"
                        + "CONNECTION7  TEXT,"
                        + "CONNECTION8  TEXT,"
                        + "CONNECTION9 TEXT,"
                        + "CONNECTION10 TEXT)";
                
                //TODO: Add more regions in the future
                
                stmt.executeUpdate(sql);
                log("Region tables created successfully in the new empty database.");
                
            }
            
            stmt.close();
            
        } catch (SQLException ex) {
            log(ex.getMessage());
        }
        
    }
    
    public int getIdFromOrigin(String systemName){
        
        int id = -1;
        
        try {
            
            Statement stmt = null;
            
            stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT * FROM DEKLEIN;");
            
            while (rs.next()) {
                
                if(rs.getString("ORIGIN").equals(systemName)){
                
                    id = rs.getInt("ID");
                    break;
                
                }
                
            }
            
            stmt.close();
                    
        } catch (SQLException ex) {
            log(ex.getMessage());
        }
        
        return id;
        
    }
    
    public String getSystemFromId(int id){
        
        String systemName = "";
        
        try {
            
            Statement stmt = null;
            
            stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT * FROM DEKLEIN;");
            
            while (rs.next()) {
                
                if(rs.getInt("ID") == id){
                
                    systemName = rs.getString("ORIGIN");
                    break;
                
                }
                
            }
            
            stmt.close();
                    
        } catch (SQLException ex) {
            log(ex.getMessage());
        }
        
        return systemName;
        
    }
    
    public void addSaveToDb(String origin, String[] connections){
        
        try{
        
            Statement stmt = null;

            if(getIdFromOrigin(origin) == -1){//If there is no entry insert the values

                stmt = conn.createStatement();
                String sql = "INSERT INTO DEKLEIN (ORIGIN,CONNECTION1,CONNECTION2,CONNECTION3,CONNECTION4,CONNECTION5,CONNECTION6,CONNECTION7,CONNECTION8,CONNECTION9,CONNECTION10) "
                        + "VALUES ('" + origin + "','" + connections[0] + "','" + connections[1] + "','" + connections[2] + "','" + connections[3] + "','" + connections[4] + "','" + connections[5] + "','" + connections[6] + "','" + connections[7] + "','" + connections[8] + "','" + connections[9] + "');";
                
                stmt.executeUpdate(sql);
                stmt.close();
                
                log("Connections of " + origin + " newly created in the database successfully.");
                
            }else{//If there is the entry update the values

                int id = getIdFromOrigin(origin);
                
                stmt = conn.createStatement();
                
                String sql = "UPDATE DEKLEIN set CONNECTION1='" + connections[0] + "' where ID=" + id;
                stmt.executeUpdate(sql);
                sql = "UPDATE DEKLEIN set CONNECTION2='" + connections[1] + "' where ID=" + id;
                stmt.executeUpdate(sql);
                sql = "UPDATE DEKLEIN set CONNECTION3='" + connections[2] + "' where ID=" + id;
                stmt.executeUpdate(sql);
                sql = "UPDATE DEKLEIN set CONNECTION4='" + connections[3] + "' where ID=" + id;
                stmt.executeUpdate(sql);
                sql = "UPDATE DEKLEIN set CONNECTION5='" + connections[4] + "' where ID=" + id;
                stmt.executeUpdate(sql);
                sql = "UPDATE DEKLEIN set CONNECTION6='" + connections[5] + "' where ID=" + id;
                stmt.executeUpdate(sql);
                sql = "UPDATE DEKLEIN set CONNECTION7='" + connections[6] + "' where ID=" + id;
                stmt.executeUpdate(sql);
                sql = "UPDATE DEKLEIN set CONNECTION8='" + connections[7] + "' where ID=" + id;
                stmt.executeUpdate(sql);
                sql = "UPDATE DEKLEIN set CONNECTION9='" + connections[8] + "' where ID=" + id;
                stmt.executeUpdate(sql);
                sql = "UPDATE DEKLEIN set CONNECTION10='" + connections[9] + "' where ID=" + id;
                stmt.executeUpdate(sql);
                
                stmt.close();
                
                log("Connections of " + origin + " updated in the database successfully.");

            }

        }catch(Exception e){
            log(e.getMessage());
        }
        
    }
    
    public String[] getRowFromOrigin(String systemName){
        
        String[] result = new String[11];
        int id = getIdFromOrigin(systemName);
        
        try {
            
            Statement stmt = null;
            
            stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT * FROM DEKLEIN;");
            
            while (rs.next()) {
                
                if(rs.getInt("ID") == id){
                
                    result[0] = rs.getString("ORIGIN");
                    result[1] = rs.getString("CONNECTION1");
                    result[2] = rs.getString("CONNECTION2");
                    result[3] = rs.getString("CONNECTION3");
                    result[4] = rs.getString("CONNECTION4");
                    result[5] = rs.getString("CONNECTION5");
                    result[6] = rs.getString("CONNECTION6");
                    result[7] = rs.getString("CONNECTION7");
                    result[8] = rs.getString("CONNECTION8");
                    result[9] = rs.getString("CONNECTION9");
                    result[10] = rs.getString("CONNECTION10");
                    //log("Connections data from " + systemName + " found.");
                    break;
                
                }
                
            }
            
            stmt.close();
                    
        } catch (SQLException ex) {
            log(ex.getMessage());
        }
        
        if(id == -1)
            log("Connections data from " + systemName + " NOT found.");
        
        return result;
        
    }
    
    public String[] getRowFromId(int id){
        
        String[] result = new String[11];
        
        try {
            
            Statement stmt = null;
            
            stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT * FROM DEKLEIN;");
            
            while (rs.next()) {
                
                if(rs.getInt("ID") == id){
                
                    result[0] = rs.getString("ORIGIN");
                    result[1] = rs.getString("CONNECTION1");
                    result[2] = rs.getString("CONNECTION2");
                    result[3] = rs.getString("CONNECTION3");
                    result[4] = rs.getString("CONNECTION4");
                    result[5] = rs.getString("CONNECTION5");
                    result[6] = rs.getString("CONNECTION6");
                    result[7] = rs.getString("CONNECTION7");
                    result[8] = rs.getString("CONNECTION8");
                    result[9] = rs.getString("CONNECTION9");
                    result[10] = rs.getString("CONNECTION10");
                    //log("Connections data from ID: " + id + " (" + result[0] + ") found.");
                    break;
                
                }
                
            }
            
            stmt.close();
                    
        } catch (SQLException ex) {
            log(ex.getMessage());
        }
        
        if(id == -1)
            log("Connections data from ID: " + id + "(" + result[0] + ") NOT found.");
        
        return result;
        
    }
    
}
