/*
 * RattingAdvisorView.java

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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;


/**
 * The application's main frame.
 */
public class RattingAdvisorMainWindow extends FrameView {

    Thread searcher;
    
    public RattingAdvisorMainWindow(SingleFrameApplication app) {
        super(app);

        initComponents();
        
        //Frame Settings
        getFrame().setTitle("EVE Online Ratting Advisor - v 0.1");
        getFrame().setResizable(false);//We do not want to let people resize the window
        //End Frame Settings

        initializeFieldsAndShared();
        
    }
    
    private void initializeFieldsAndShared(){
        
        //Populate Shared Variables
        Shared shared = new Shared();
        IntelFileFinder intelFinder = new IntelFileFinder();
        FileManager fileManager = new FileManager();
        String raw = fileManager.ReadFile("settings.cfg");//Read the settings file
        String[] variables = raw.split("\n");
        String[] value;
        String[] orderedValues = new String[7];
        for (int i = 0; i < variables.length-1; i++) {
            
            value = variables[i].split("=");
            orderedValues[i] = value[1];
            
        }
        shared.setChatLogsPath(orderedValues[0]);
        shared.setIntelChannelName(orderedValues[1]);
        shared.setRattingSystemName(orderedValues[2]);
        shared.setMaxJumpsNumber(Integer.parseInt(orderedValues[3]));
        shared.setAlarmSoundPath(orderedValues[4]);
        shared.setCheckLocal(false);
        shared.setCheckShield(false);
        shared.setMapDbPath("Deklein.db");
        shared.setDbUtils(new DbUtils(shared.getMapDbPath(), logTextArea));
        shared.setLogTextArea(logTextArea);
        shared.setFileManager(new FileManager());
        shared.setMapLogic(new MapLogic());
        shared.setIntelReader(new IntelReader(intelFinder.pathToLastIntelFile(shared.getChatLogsPath(), shared.getIntelChannelName())));
        shared.setStillSearching(true);
        shared.setSystemsInRange(shared.getMapLogic().mapSearcher(shared.getRattingSystemName(), shared.getMaxJumpsNumber()));
        shared.setKeepsGettingSystems(false);
        //End Populate Shared Variables
        
        //Set Default Values for the MainWindow TextAreas
        rattingSystemText.setText(shared.getRattingSystemName());
        maxJumps.setValue(shared.getMaxJumpsNumber());
        //END Set Default Values for the MainWindow TextAreas
        
        //Launch the GetSystems Thread
        Thread getSystemsThread = new GetSystemsThread();
        getSystemsThread.start();
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        rattingSystemText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        maxJumps = new javax.swing.JSpinner();
        checkNeutrals = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        shieldAlarm = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        clearLogButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        mainPanel.setMaximumSize(new java.awt.Dimension(600, 400));
        mainPanel.setMinimumSize(new java.awt.Dimension(600, 400));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(600, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(rattingadvisor.RattingAdvisor.class).getContext().getResourceMap(RattingAdvisorMainWindow.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        startButton.setText(resourceMap.getString("startButton.text")); // NOI18N
        startButton.setName("startButton"); // NOI18N
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        stopButton.setText(resourceMap.getString("stopButton.text")); // NOI18N
        stopButton.setEnabled(false);
        stopButton.setName("stopButton"); // NOI18N
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        settingsButton.setText(resourceMap.getString("settingsButton.text")); // NOI18N
        settingsButton.setName("settingsButton"); // NOI18N
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        rattingSystemText.setText(resourceMap.getString("rattingSystemText.text")); // NOI18N
        rattingSystemText.setEnabled(false);
        rattingSystemText.setName("rattingSystemText"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        maxJumps.setEnabled(false);
        maxJumps.setName("maxJumps"); // NOI18N

        checkNeutrals.setText(resourceMap.getString("checkNeutrals.text")); // NOI18N
        checkNeutrals.setEnabled(false);
        checkNeutrals.setName("checkNeutrals"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        shieldAlarm.setText(resourceMap.getString("shieldAlarm.text")); // NOI18N
        shieldAlarm.setEnabled(false);
        shieldAlarm.setName("shieldAlarm"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        searchButton.setText(resourceMap.getString("searchButton.text")); // NOI18N
        searchButton.setName("searchButton"); // NOI18N
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        logTextArea.setEditable(false);
        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        logTextArea.setText(resourceMap.getString("logTextArea.text")); // NOI18N
        logTextArea.setName("logTextArea"); // NOI18N
        jScrollPane2.setViewportView(logTextArea);

        clearLogButton.setText(resourceMap.getString("clearLogButton.text")); // NOI18N
        clearLogButton.setName("clearLogButton"); // NOI18N
        clearLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rattingSystemText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxJumps, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(searchButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shieldAlarm)
                            .addComponent(checkNeutrals)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(startButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton)
                        .addGap(143, 143, 143)
                        .addComponent(clearLogButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                        .addComponent(settingsButton)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rattingSystemText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkNeutrals))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(maxJumps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shieldAlarm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchButton)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(stopButton)
                    .addComponent(settingsButton)
                    .addComponent(clearLogButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        
        /* Create and display the settings form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SettingsWindow(getFrame().getLocation(), false).setVisible(true);
            }
        });
        
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        
        if(new Shared().isKeepsGettingSystems()){
            
            JOptionPane.showMessageDialog(null, "The map is still loading!", "Not yet loaded", JOptionPane.INFORMATION_MESSAGE);
            
        }else{
            
            //Disable the componets
            rattingSystemText.setEnabled(false);
            maxJumps.setEnabled(false);
            startButton.setEnabled(false);
            settingsButton.setEnabled(false);
            searchButton.setEnabled(false);
            stopButton.setEnabled(true);

            new Shared().setStillSearching(true);
            searcher = new SearcherThread();
            searcher.start();
            logTextArea.setText(logTextArea.getText() + "\nScanning started...");
            
        }
        
    }//GEN-LAST:event_startButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        
        //Enable the componets
//        rattingSystemText.setEnabled(true);
//        maxJumps.setEnabled(true);
        startButton.setEnabled(true);
        settingsButton.setEnabled(true);
        searchButton.setEnabled(true);
        stopButton.setEnabled(false);
        
        new Shared().setStillSearching(false);
        try {
            searcher.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(RattingAdvisorMainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        logTextArea.setText(logTextArea.getText() + "\nScanning stopped...");
        
    }//GEN-LAST:event_stopButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        
        JOptionPane.showMessageDialog(null, "Renember only the last game session opened's intel channel will be chosen\neven if that game session is closed.", "New Session Search", JOptionPane.INFORMATION_MESSAGE);
        initializeFieldsAndShared();
        
    }//GEN-LAST:event_searchButtonActionPerformed

    private void clearLogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearLogButtonActionPerformed
        
        logTextArea.setText("Log:");
        
    }//GEN-LAST:event_clearLogButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkNeutrals;
    private javax.swing.JButton clearLogButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSpinner maxJumps;
    private javax.swing.JTextField rattingSystemText;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JCheckBox shieldAlarm;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables

}
