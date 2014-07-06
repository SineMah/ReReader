/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rereader;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ReUI extends javax.swing.JFrame {

    private static String[] words = new String[0];
    private Boolean timerEnabled = false;
    private Timer timer = new Timer();
    private int positionWords = 0;
    private static Image[] images = new Image[0];
    private ReConfig config = ReGlobals.initGlobals();
    private static int fileStatus = 0;
    private static String content;

    /**
     * Creates new form ReUI
     */
    public ReUI() {
        initComponents();
        reTimer();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectedDocument = new javax.swing.JFileChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        reading = new javax.swing.JButton();
        openFile = new javax.swing.JButton();
        textPic = new javax.swing.JPanel();
        reProgressBar = new javax.swing.JProgressBar();
        reProgressLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        loadDefault = new javax.swing.JCheckBox();
        wordSpeed = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        reading.setText("Start");
        reading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readingActionPerformed(evt);
            }
        });

        openFile.setText("open file");
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout textPicLayout = new javax.swing.GroupLayout(textPic);
        textPic.setLayout(textPicLayout);
        textPicLayout.setHorizontalGroup(
            textPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        textPicLayout.setVerticalGroup(
            textPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        reProgressLabel.setText("Welcome to ReReader");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(reading)
                                .addGap(48, 48, 48)
                                .addComponent(openFile))
                            .addComponent(reProgressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textPic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reading)
                    .addComponent(openFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(reProgressLabel)
                .addGap(11, 11, 11)
                .addComponent(reProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Read", jPanel1);

        loadDefault.setText("use ini file if it exists");
        loadDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadDefaultActionPerformed(evt);
            }
        });

        wordSpeed.setText("250");
        wordSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordSpeedActionPerformed(evt);
            }
        });

        jLabel1.setText("words per minute");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loadDefault)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(wordSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wordSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(loadDefault)
                .addGap(44, 44, 44))
        );

        jTabbedPane1.addTab("Options", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wordSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordSpeedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wordSpeedActionPerformed

    private void loadDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadDefaultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loadDefaultActionPerformed

    private void readingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readingActionPerformed
        if (words.length == 0) {
            JOptionPane.showMessageDialog(null, "No file selected");
        } else {
            //        init timer
            handleTimer();

        }
    }//GEN-LAST:event_readingActionPerformed

    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileActionPerformed
        int returnVal = selectedDocument.showOpenDialog(this);

        if (returnVal == 0) {
            String file = selectedDocument.getSelectedFile().toString();
            
            ReStatus.setLabel("Reading Files");
            
            try {
                fileStatus = 1;
                
                content = ReFile.readFileEncoded(file);
//                System.out.println(content);
                content = ReText.reFromatText(content);
                
//                words = ReText.splitText(content);
//                words = ReText.proofWords(words);
//                images = ReImage.generateImageList(words);

                fileStatus = 2;
                positionWords = 0;

//                System.out.println(Arrays.deepToString(words));
            } catch (IOException ex) {
                System.out.println("There was nothing to load.");
            }
        }
    }//GEN-LAST:event_openFileActionPerformed

    private void handleTimer() {
        if (timerEnabled == false) {
            reading.setText("Stop");
            timerEnabled = true;

            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (positionWords < images.length) {
//                        textField.setText(words[positionWords]);

                        drawImage(images[positionWords]);

                        positionWords++;
                    } else {
                        timer.cancel();
                        timer = new Timer();
                        positionWords = 0;
                        reading.setText("Start");
                        timerEnabled = false;
                    }
                }
            }, 0, getPeriod());
        } else {
            reading.setText("Start");
            timerEnabled = false;

            timer.cancel();
            timer = new Timer();
        }
    }

    private void drawImage(Image image) {
        Graphics2D gfx = (Graphics2D) textPic.getGraphics();
        gfx.clearRect(0, 0, textPic.getWidth(), textPic.getHeight());
        gfx.drawImage(image, null, this);
        gfx.dispose();
    }

    private  int getPeriod() {
        int amount = config.getWordSpeed();

        if (amount < 1 || amount > 2000) {

//            set a default value of 50 words per second
            return 200;
        } else {

            return Math.round(60 * 1000 / amount);
        }
    }

    public static int getProgressBar() {
        return reProgressBar.getValue();
    }

    public static void setProgressBar(int value) {
        if(value >= 0) {
            reProgressBar.setValue(value);
        }
    }

    public String getProgressLabel() {
        return reProgressLabel.getText();
    }

    public static void setProgressLabel(String value) {
        if(value.length() > 0) {
           reProgressLabel.setText(value); 
        }
    }

    private static void reTimer() {
        Timer timer = new Timer();

//        0 listen 
//        1 processing
//        2 processing done
//        3 inactive
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                switch (fileStatus) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        fileStatus = 1;
                        words = ReText.splitText(content);
                        images = ReImage.generateImageList(words);
                        
                        ReUI.setProgressLabel("Ready to read");
                        fileStatus = 3;
                        break;
                    default: 
                        break;
                }
            }
        }, 0, 50);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox loadDefault;
    private javax.swing.JButton openFile;
    public static javax.swing.JProgressBar reProgressBar;
    public static javax.swing.JLabel reProgressLabel;
    private javax.swing.JButton reading;
    private javax.swing.JFileChooser selectedDocument;
    private javax.swing.JPanel textPic;
    private javax.swing.JTextField wordSpeed;
    // End of variables declaration//GEN-END:variables
}
