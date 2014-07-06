/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rereader;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ReStatus {

    public static void setProgress(String label, int progress) {

    }
    
    public static void setLabel(String label) {
//        reProgressLabel
        ReUI.setProgressLabel(label);
    }
    
    public static void setProgress(int current, float max, float maxPerc) {
         int progressBase = ReUI.getProgressBar();
         double progress;
         
//          progress = Math.ceil(current / max * maxPerc) + progressBase;
          progress = Math.ceil(current / max * maxPerc);
             
          ReUI.setProgressBar((int) progress);
    }
}
