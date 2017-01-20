/*
 * SettingsWindow.java

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

import java.awt.Point;
import java.io.File;
import java.awt.event.WindowEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author Anibal
 */
public class SettingsWindow extends javax.swing.JFrame {

    /**
     * Creates new form SettingsWindow
     */
    public SettingsWindow(Point p, boolean firstStart) {
        initComponents();
        Shared shared = new Shared();
        
        //Frame Settings
        setTitle("EVE Online Ratting Advisor - Settings");
        setResizable(false);//We do not want to let people resize the window
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(p);
        
        //Set the default values for the textFields
        logPath.setText(shared.getChatLogsPath());
        intelChannel.setText(shared.getIntelChannelName());
        rattingSystemText.setText(shared.getRattingSystemName());
        maxJumps.setValue(shared.getMaxJumpsNumber());
        alarmSoundPath.setText(shared.getAlarmSoundPath());
        
        //*UNUSED*
        //Set controls to make mandatory press the save button
        if(firstStart){
            
            setAlwaysOnTop(true);
            discardButton.setEnabled(false);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            requestFocus();
            
        }else{
            
            setAlwaysOnTop(false);
            discardButton.setEnabled(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
        }
        //END - Set controls to make mandatory press the save button
        
        SettingsManager settingsManager = new SettingsManager();
        if(settingsManager.getSetting("Style").equals("DARK")){
            
            darkThemeCheck.setSelected(true);
            
        }else if(settingsManager.getSetting("Style").equals("LIGHT")){
            
            darkThemeCheck.setSelected(false);
            
        }else{
            
            darkThemeCheck.setSelected(false);
            settingsManager.setSetting("Style", "LIGHT");
            
        }
        
        includeClear.setSelected(Boolean.valueOf(settingsManager.getSetting("IncludeClear")));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        logPath = new javax.swing.JTextField();
        intelChannel = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        discardButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        rattingSystemText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        maxJumps = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alarmSoundPath = new javax.swing.JTextPane();
        changeSoundButton = new javax.swing.JButton();
        changeLogsPathButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        darkThemeCheck = new javax.swing.JCheckBox();
        includeClear = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(rattingadvisor.RattingAdvisor.class).getContext().getResourceMap(SettingsWindow.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        logPath.setName("logPath"); // NOI18N

        intelChannel.setName("intelChannel"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.setName("jButton3"); // NOI18N

        saveButton.setText(resourceMap.getString("saveButton.text")); // NOI18N
        saveButton.setName("saveButton"); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        discardButton.setText(resourceMap.getString("discardButton.text")); // NOI18N
        discardButton.setName("discardButton"); // NOI18N
        discardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtonActionPerformed(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        rattingSystemText.setText(resourceMap.getString("rattingSystemText.text")); // NOI18N
        rattingSystemText.setName("rattingSystemText"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        maxJumps.setName("maxJumps"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        alarmSoundPath.setName("alarmSoundPath"); // NOI18N
        jScrollPane1.setViewportView(alarmSoundPath);

        changeSoundButton.setText(resourceMap.getString("changeSoundButton.text")); // NOI18N
        changeSoundButton.setName("changeSoundButton"); // NOI18N
        changeSoundButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSoundButtonActionPerformed(evt);
            }
        });

        changeLogsPathButton.setText(resourceMap.getString("changeLogsPathButton.text")); // NOI18N
        changeLogsPathButton.setName("changeLogsPathButton"); // NOI18N
        changeLogsPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeLogsPathButtonActionPerformed(evt);
            }
        });

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        darkThemeCheck.setText(resourceMap.getString("darkThemeCheck.text")); // NOI18N
        darkThemeCheck.setName("darkThemeCheck"); // NOI18N
        darkThemeCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darkThemeCheckActionPerformed(evt);
            }
        });

        includeClear.setText(resourceMap.getString("includeClear.text")); // NOI18N
        includeClear.setName("includeClear"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 231, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(252, 252, 252))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(darkThemeCheck)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(saveButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addGap(116, 116, 116)
                                .addComponent(discardButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(changeSoundButton)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxJumps, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rattingSystemText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(logPath, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                            .addComponent(intelChannel))
                                        .addGap(18, 18, 18)
                                        .addComponent(changeLogsPathButton))
                                    .addComponent(includeClear))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(logPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeLogsPathButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(intelChannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(darkThemeCheck)
                    .addComponent(includeClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rattingSystemText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(maxJumps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(changeSoundButton, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(discardButton)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        
    }//GEN-LAST:event_discardButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        
        FileManager fileManager = new FileManager();
        Shared shared = new Shared();
        String oldFile, newFile;
        
        oldFile = fileManager.ReadFile("settings.cfg");
        fileManager.NewFile("settings.cfg");
        
        //Detect if the selected audio file is other than wav and if so, convert it
        //and change the path of the alarm sound
        if(alarmSoundPath.getText().endsWith("mp3")){
            
            File mp3 = new File(alarmSoundPath.getText());
            File wav = new File("converted.wav");
            Thread converter = new ConvertMp3toWavThread(mp3, wav);
            
            converter.start();
            alarmSoundPath.setText("converted.wav");
            
        }
        
        //STUB
        //Style selection for save
        String theme;
        if (darkThemeCheck.isSelected()) {
            theme = "DARK";
        } else {
            theme = "LIGHT";
        }
        
        newFile = "ChatLogsPath=" + logPath.getText() + "\n"
                + "IntelChannel=" + intelChannel.getText() + "\n"
                + "RattingSystem=" + rattingSystemText.getText().toUpperCase() + "\n"
                + "JumpsNumber=" + maxJumps.getValue() + "\n" 
                + "AlarmSound=" + alarmSoundPath.getText() + "\n"
                + "CheckLocal=" + "FALSE" + "\n"
                + "CheckShield=" + "FALSE" + "\n"
                + "Style=" + theme + "\n"
                + "IncludeClear=" + String.valueOf(includeClear.isSelected()).toUpperCase();
        
        fileManager.WriteFile("settings.cfg", newFile);
        
        InitializeShared initializeShared = new InitializeShared(shared.getLogTextAreaMainWindow(), shared.getRattingSystemTextMainWindow(), shared.getMaxJumpsMainWindow());
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        
    }//GEN-LAST:event_saveButtonActionPerformed

    private void changeSoundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSoundButtonActionPerformed
        
        String ruta;
        JFileChooser fileChooser;
        File selectedFile;
        Shared shared;
        
        fileChooser = new JFileChooser();
        shared = new Shared();
        
        fileChooser.setDialogTitle("Select an alarm sound file.");
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showDialog(null, "Select");
        
        selectedFile = fileChooser.getSelectedFile();
        shared.setAlarmSoundPath(selectedFile.getAbsolutePath());
        alarmSoundPath.setText(selectedFile.getAbsolutePath());
        
    }//GEN-LAST:event_changeSoundButtonActionPerformed

    private void changeLogsPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeLogsPathButtonActionPerformed
        
        String ruta;
        JFileChooser fileChooser;
        File selectedDirectory;
        Shared shared;
        
        fileChooser = new JFileChooser();
        shared = new Shared();
        
        fileChooser.setDialogTitle("Select the ChatLogs's directory.");
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showDialog(null, "Select");
        
        selectedDirectory = fileChooser.getSelectedFile();
        logPath.setText(selectedDirectory.getAbsolutePath());
        
    }//GEN-LAST:event_changeLogsPathButtonActionPerformed

    private void darkThemeCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darkThemeCheckActionPerformed
        
        if(darkThemeCheck.isSelected()){
            
            new SettingsManager().setSetting("Style", "DARK");
            
        }else{
            
            new SettingsManager().setSetting("Style", "LIGHT");
            
        }
            
        JOptionPane.showMessageDialog(null, "Restart Ratting Advisor to changes take effect", "Restart needed", JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_darkThemeCheckActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane alarmSoundPath;
    private javax.swing.JButton changeLogsPathButton;
    private javax.swing.JButton changeSoundButton;
    private javax.swing.JCheckBox darkThemeCheck;
    private javax.swing.JButton discardButton;
    private javax.swing.JCheckBox includeClear;
    private javax.swing.JTextField intelChannel;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField logPath;
    private javax.swing.JSpinner maxJumps;
    private javax.swing.JTextField rattingSystemText;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
