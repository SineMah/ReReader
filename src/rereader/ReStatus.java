

package rereader;

/**
 * 
 * @author Christian Bock <bock.christian@mincedmnd.com>
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
