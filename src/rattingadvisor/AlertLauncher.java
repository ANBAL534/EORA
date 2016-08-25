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

/**
 *
 * @author Anibal
 */
public class AlertLauncher {
    
    Shared shared;
    MediaPlayer player;
    
    public AlertLauncher(){
        
        shared = new Shared();
        
    }
    
    public void launchAlarm(final String[] report){
        
        shared.getDbUtils().log("Musica Maestro");
        final Thread sound = new MediaPlayer(shared.getAlarmSoundPath());
        
        sound.start();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AlertWindow dialog = new AlertWindow(new javax.swing.JFrame(), true, report);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        
                        sound.stop();
                        
                    }
                });
                dialog.setVisible(true);
                dialog.setAlwaysOnTop(true);
                dialog.setAutoRequestFocus(true);
                dialog.setResizable(false);
            }
        });
        
    }
    
}
