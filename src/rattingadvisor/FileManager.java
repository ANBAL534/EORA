package rattingadvisor;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * In this class there is all the methods that are called when introducting
 * a command, every method is a command with all the logic of that command
 * inside.
 * 
 * Method List:
 * 
 *  - NewFile(String filePath) -> in charge of creating a new file file if none 
 * exists in the fileName supplied
 * 
 *  - ReadFile(String filePath) -> in charge of of reading and returning as a String
 * the text read from a file file
 * 
 *  - WriteFile(String filePath, String data) -> in charge of writting the text 
 * supplied to a file file also supplied
 * 
 *  - AppendFile(String filePath, String data) -> in charge of appending the text 
 * supplied to a file file also supplied
 *
 * @author Anibal
 */
public class FileManager {
    
    public void NewFile(String filePath){
        
        try {
            
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            
        } catch (IOException ex) {
            System.out.println("**Error creating a new file (IOException)**");
        }
        
    }
    
    public String ReadFile(String filePath){
        
        String line;
        String archivo = "";
        BufferedReader br = null;
        
        try {
            
            br = new BufferedReader(new FileReader(filePath));
            while((line = br.readLine()) != null){
                
                archivo += line + "\n";
                
            }
            
        } catch (Exception ex) {
            System.out.println("**Error reading a file (NotFoundException)**");
        } finally {
            
            try {
                
                br.close();
                
            } catch (IOException ex) {
                System.out.println("**Error reading a file (IOException)**");
            }
            
        }
        
        return archivo;
        
    }
    
    public void WriteFile(String filePath, String data){
        
        BufferedWriter bw = null;
        
        try {
            
            File file = new File(filePath);
            bw = new BufferedWriter(new FileWriter(file));
            
            bw.write(data);
            
        } catch (IOException ex) {
            System.out.println("**Error writting a file (NotFoundException)**");
        } finally {
            try {
                
                bw.close();
                
            } catch (IOException ex) {
                System.out.println("**Error writting a file (IOException)**");
            }
        }
        
    }
    
    public void AppendFile(String filePath, String data){
        
        BufferedWriter bw = null;
        
        try {
            
            File file = new File(filePath);
            bw = new BufferedWriter(new FileWriter(file));
            
            bw.append(data);
            
        } catch (IOException ex) {
            System.out.println("**Error appending a file (NotFoundException)**");
        } finally {
            try {
                
                bw.close();
                
            } catch (IOException ex) {
                System.out.println("**Error appending a file (IOException)**");
            }
        }
        
    }
    
    public boolean FileExist(String filePath){
        
        File f = new File(filePath);
        if(f.exists() && !f.isDirectory()) { 
            
            return true;
            
        }else{
            
            return false;
            
        }
        
    }
    
    public String ISOtoUTF(String isoString){
        
        String utfText = "";
        
        try {
            
            byte[] iso = isoString.getBytes();
            
            for (int i = 0; i < iso.length; i++) {
                
                if(iso[i] == -17 || iso[i] == -65 || iso[i] == -67)
                    iso[i] = 0;
                
            }
            
            utfText = new String(iso);
            
        } catch (Exception ex) {
            System.out.println("**Error encoding a file**");
        }
        
        return utfText;
        
    }
    
}