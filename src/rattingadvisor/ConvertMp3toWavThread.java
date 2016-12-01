/*
 * ConvertMp3toWavThread.java

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
import javazoom.jl.converter.*;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author Anibal
 */
public class ConvertMp3toWavThread extends Thread{

    File mp3;
    File wav;
    
    public ConvertMp3toWavThread(File mp3In, File wavOut) {
        
        mp3 = mp3In;
        wav = wavOut;
        
    }
    
    public void run(){
        
        try {
            
            Converter converter = new Converter();
            converter.convert(mp3.getAbsolutePath(), wav.getAbsolutePath());
            
        } catch (JavaLayerException ex) {
            new Shared().getDbUtils().log("**ERROR WHILE CONVERTING MP3 FILE TO WAV**\nTHERE WILL BE NO SOUND, CHANGE SOUND FILE PATH.");
        }
        
    }
    
}
