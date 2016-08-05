/*
 * AlertLauncher.java

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

import javax.swing.JOptionPane;

/**
 *
 * @author Anibal
 */
public class AlertLauncher {
    
    Shared shared;
    MediaPlayer player;
    
    public AlertLauncher(){
        
        shared = new Shared();
        player = new MediaPlayer(shared.getAlarmSoundPath());
        player.setVolume(1.0F);
        
    }
    
    public void launchAlarm(String[] report){
        
        player.play();
        JOptionPane.showMessageDialog(null, "Neutral detected in range!\nReporter: " + report[1] + "\nSystem: " + report[2] + "Full Message: \n\"" + report[3] + "\"", "Neutral Detected", JOptionPane.WARNING_MESSAGE);
        player.stop();
        
    }
    
}
