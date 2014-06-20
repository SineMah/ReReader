/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rereader;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ReText {

    public static String reFromatText(String textFromFile) {

//        return textFromFile.replaceAll("\\r\\n|\\r|\\n", " ");
        return textFromFile.replaceAll("\\s+", " ");
    }

    public static String[] splitText(String text) {

        return text.split(" ");
    }

    public static String[] proofWords(String[] text) {
         String[] dictionary = ReReader.initDictionary(text);
         String[] splittedWord;
         List<String> words = new ArrayList<String>();
         String[] finalList = new String[0];
         
         for(int i=0; i<text.length; i++) {
             splittedWord = splitWithDictionary(text[i], dictionary);
             
             words = stringsToList(words, splittedWord);
         }
        
         finalList = new String[words.size()];
        
        return words.toArray(finalList);
    }

    private static String[] splitWithDictionary(String word, String[] dic) {
         String[] splitted = new String[0];

        if(word.length() >8) {
                 splitted = wordParts(word, dic);
//                 debug here
                 if(splitted.length == 0) {
                     splitted = defaultSplit(word);
                 }
        }else {
            splitted = new String[1];
            splitted[0] = word;
        }

        return splitted;
    }
    
    private static String[] defaultSplit(String word) {
        List<String> newWords = new ArrayList<String>();

        if (word.length() > 11) {
//                String[] splitted = splitByNumber(word, 12);
            String[] splitted = splitWord(word, 12);

            for (int i = 0; i < splitted.length; i++) {
                if (i == 0) {
                    newWords.add(splitted[i] + '-');
                }else {
                    newWords.add(splitted[i]);
                }
            }
//                split
        } else {
            newWords.add(word);
        }
        
//        cast back to array
        String[] finalWords = new String[newWords.size()];
        
        return newWords.toArray(finalWords);
    }
    
    private static String[] wordParts(String word, String[] dic) {
        List<String> splittedList = new ArrayList<String>();
         String[] splitted = new String[0];
         String splitValue;
         int splitSize = 1;
        
        while(word.length() > 8 && splitSize > 0) {
            splitValue = findInDic(word, dic);
            splitSize = splitValue.length();
            
            if(word.substring(0, splitSize).length() > 0) {
                splittedList.add(word.substring(0, splitSize) + "-");
            }
            
            word = word.substring(splitSize, word.length());
        }
        
        splittedList.add(word);
        
        String[] finalSplitted = new String[splittedList.size()];
        
        return splittedList.toArray(finalSplitted);
    }
    
    private static String findInDic(String word, String[] dic) {
        String tempText = word.toLowerCase();
        String tempDic = "";
        String tempWord = "";
        String spltValue = "";
            
        for(int i=0; i<dic.length; i++) {  
             tempDic = dic[i].toLowerCase();
             tempWord = word.substring(0, dic[i].length()).toLowerCase();
             
             if(tempWord.contains(tempDic) && tempWord.length() == tempDic.length()) {
                    spltValue = dic[i];
                    break;
             }
        }
                    
        return spltValue;
    }
    
    private static List stringsToList(List wordList, String[] parts) {
        for(int i=0; i<parts.length; i++) {
            if(parts[i].length() > 0) {
                wordList.add(parts[i]);
            }
        }
        
        return wordList;
    }
    
    public static String[] poofSplittedText(String[] text) {
        String[] newText = new String[0];
        String[] splitted;
        
        for(int i=0; i<text.length; i++) {
            if(text[i].length() > 12) {
                splitted = splitWord(text[i], 12);
            }else {
                splitted = new String[1];
                splitted[0] = text[i];
            }
                newText = ReReader.copyArray(text, splitted);
        }
        
        return newText;
    }
    
    private static String[] splitByNumber(String s, int chunkSize) {
        int chunkCount = (s.length() / chunkSize) + (s.length() % chunkSize == 0 ? 0 : 1);
        String[] returnVal = new String[chunkCount];

        for (int i = 0; i < chunkCount; i++) {
            returnVal[i] = s.substring(i * chunkSize, Math.min((i + 1) * chunkSize - 1, s.length()));
        }

        return returnVal;
    }

    private static String markupString(String word) {
//         String[] chars = splitByNumber(word, 2);
//        System.out.println(word);
         String[] chars = splitWord(word, 1);
//System.out.println(chars);
        
         if(chars.length == 1) {
             chars = highlightString(chars, 1);
         }else if(chars.length < 4) {
             chars = highlightString(chars, 2);
         }else if(chars.length < 9) {
             chars = highlightString(chars, 3);
         }else {
             chars = highlightString(chars, 4);
         }
         
        return mergeChars(chars);
    }
    
    private static String[] splitWord(String word, int size) {
        
        return word.split("(?<=\\G.{" + size + "})");
//        return word.split("");
    }
    
    private static String[] highlightString(String[] chars, int position) {
        
        for(int i=0; i<chars.length; i++) {
//            System.out.println(chars[i]);
             if(i == position-1) {
                 chars[i] = "<font color=\"red\">" + chars[i] + "</font>";
             }else {
                 chars[i] = "<font>" + chars[i] + "</font>";
             }
         }
        
        return chars;
    }

    private static String mergeChars(String[] chars) {
        String word = "";
        
        for(int i=0; i<chars.length; i++) {
            word = word + chars[i];
        }
        
        return "<html>" + word + "</html>";
    }
    
}
