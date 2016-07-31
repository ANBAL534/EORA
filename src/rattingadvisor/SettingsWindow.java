/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rattingadvisor;

import java.awt.Point;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Locale;
import sun.net.www.protocol.http.HttpURLConnection;

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
        
        //Frame Settings
        setTitle("EVE Online Ratting Advisor - Settings");
        setResizable(false);//We do not want to let people resize the window
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(p);
        
        //Set the default values for the textFields
        timeText.setText(Calendar.getInstance(Locale.GERMANY).getTime().getDate()+ "/" + (Calendar.getInstance(Locale.GERMANY).getTime().getMonth()+1) + "/" + (Calendar.getInstance(Locale.GERMANY).getTime().getYear()+1900));
        logPath.setText(System.getProperty("user.home") + "\\Documents\\EVE\\logs\\Chatlogs");
        intelChannel.setText("GOTG_Intel");
        
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
        jLabel4 = new javax.swing.JLabel();
        timeText = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        discardButton = new javax.swing.JButton();

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

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        timeText.setEditable(false);
        timeText.setName("timeText"); // NOI18N

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jLabel1)
                        .addGap(0, 223, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(logPath)
                                    .addComponent(intelChannel)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeText, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 140, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(discardButton)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(logPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(intelChannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(timeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(discardButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        
    }//GEN-LAST:event_discardButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        
        FileManager fileManager = new FileManager();
        String oldFile, newFile;
        
        oldFile = fileManager.ReadFile("config/settings.cfg");
        fileManager.NewFile("config/settings.cfg");
        
        //STUB
        newFile = "ChatLogsPath=" + logPath.getText() + "\n"
                + "IntelChannel=" + intelChannel.getText() + "\n"
                + "RattingSystem=" + "9CK-KZ" + "\n"
                + "JumpsNumber=" + "3" + "\n" 
                + "AlarmSound=" + "G:\\Mi Musica\\Pilar Man Awaken.mp3" + "\n"
                + "CheckLocal=" + "FALSE" + "\n"
                + "CheckShield=" + "FALSE" + "\n";
        
    }//GEN-LAST:event_saveButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton discardButton;
    private javax.swing.JTextField intelChannel;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField logPath;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField timeText;
    // End of variables declaration//GEN-END:variables
}
