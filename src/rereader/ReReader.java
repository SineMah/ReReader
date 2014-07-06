/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rereader;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ReReader {

    private static ReConfig config = ReGlobals.initGlobals();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public static String[] resizeArray(String[] oldArray, int addSize) {
        String[] newArray = new String[oldArray.length + addSize];

        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }

        return newArray;
    }

    public static String[] copyArray(String[] oldArray, String[] newArray) {
//        well new array is just a part which needs to be added to an old array

        String[] newText = resizeArray(oldArray, newArray.length);

        for (int i = 0; i < newText.length; i++) {
            newText[i + oldArray.length - 1] = newArray[i];
        }

        return newText;
    }

    public static String[] initDictionary(String[] text) {
        String[] dics = getDictionaries();
        String[][] dictionaries = new String[dics.length][0];
        String[][] cleanedDictionaries = new String[dics.length][0];
        String[] dictionary = new String[0];
        float perc = 100;
        float maxLength = dics.length;

        ReUI.setProgressLabel("Initiate dictionaries");
        
        for (int i = 0; i < dics.length; i++) {
            dictionaries[i] = readDic(dics[i]);
            cleanedDictionaries[i] = cleanDic(dictionaries[i]);
            
            ReStatus.setProgress(i, maxLength, perc); 
        }

        dictionary = chooseDic(cleanedDictionaries, text);

        return dictionary;
    }

    public static String[] getDictionaries() {
        List<String> dics = new ArrayList<String>();
        String workingDir = System.getProperty("user.dir");
        String dicDir = workingDir + "/dic/";
        String[] finalDics;

        ///////////////////////////////////////////////////////////////
        Path dir = FileSystems.getDefault().getPath(dicDir);
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir);

            for (Path path : stream) {
                dics.add(path.getFileName().toString());
//               System.out.println( path.getFileName() );
            }

            stream.close();
        } catch (IOException ex) {
            System.out.println("Nope. Nope. Nope");
        }

        finalDics = new String[dics.size()];

        ///////////////////////////////////////////////////////////////
//         try {
//                String dic = ReFile.readFile(dicFile);
//                dic = ReText.reFromatText(dic);
//                words = ReText.splitText(dic);
//                
//                System.out.println(Arrays.deepToString(words));
//            } catch (IOException ex) {
//                System.out.println("There was nothing to load.");
//            }
//        
//        System.out.println("Current working directory : " + workingDir);
        return dics.toArray(finalDics);
    }

    private static String[] readDic(String dic) {
        String workingDir = System.getProperty("user.dir");
        String dicDir = workingDir + "/dic/";
        String file = dicDir + dic;
        String dictionary;
        String[] dictionaryWords = new String[0];

        try {
            dictionary = ReFile.readFile(file);
            dictionary = ReText.reFromatText(dictionary);
            dictionaryWords = ReText.splitText(dictionary);

//                System.out.println(Arrays.deepToString(words));
        } catch (IOException ex) {
            System.out.println("There was nothing to load.");
        }

        return dictionaryWords;
    }

//    just list shorter words in array and shrink it
    private static String[] cleanDic(String[] dic) {
        List<String> list = new ArrayList<String>();
        String[] finalList;

        for (int i = 0; i < dic.length; i++) {
            if (dic[i].length() < config.getTextSizeMax() && dic[i].length() > config.getTextSizeMin()-1) {
                list.add(dic[i]);
            }
        }

        finalList = new String[list.size()];

        return list.toArray(finalList);
    }

    private static String[] chooseDic(String[][] dics, String[] text) {
        String tempText;
        String tempDic;
        int langVal;
        int langKey;
        int[] countLang = new int[dics.length];
        float perc = 100;
        float maxLength = dics.length;

        ReUI.setProgressLabel("Readinf dictionaries");
        
        for (int i = 0; i < dics.length; i++) {
            for (int j = 0; j < dics[i].length; j++) {
                for (int cnt = 0; cnt < text.length && cnt < config.getDictionaryMaxSearch(); cnt++) {
                    tempText = text[cnt].toLowerCase();
                    tempDic = dics[i][j].toLowerCase();

                    if (tempText.contains(tempDic)) {
                        countLang[i]++;
                    }
                }
            }
            
            ReStatus.setProgress(i, maxLength, perc); 
        }

        langVal = getMax(countLang);
        langKey = getIndex(countLang, langVal);

        return dics[langKey];
    }

    public static int getMax(int[] numbers) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < numbers.length; i++) {
            if (max < numbers[i]) {
                max = numbers[i];
            }
        }

        return max;
    }

    public static int getIndex(int[] numbers, int needle) {
        int index = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (needle == numbers[i]) {
                index = i;
                break;
            }
        }

        return index;
    }
}
