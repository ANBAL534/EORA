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
