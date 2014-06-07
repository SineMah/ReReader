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
public class ReReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    
    public static String[] resizeArray(String[] oldArray, int addSize) {
        String[] newArray = new String[oldArray.length + addSize];
        
        for(int i=0; i<oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        
        return newArray;
    }
    
    public static String[] copyArray(String[] oldArray, String[] newArray) {
//        well new array is just a part which needs to be added to an old array
        
        String[] newText = resizeArray(oldArray, newArray.length);
        
        for(int i=0; i<newText.length; i++) {
            newText[i+oldArray.length-1] = newArray[i];
        }
        
        return newText;
    }
    
}
