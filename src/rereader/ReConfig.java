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
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ReConfig {

    private int textSizeMin = 4;
    private int textSizeMax = 9;
    private int fontSize = 24;
    private String fontType = "Arial";
    private int wordSpeed = 250;
    private String saveLang = "english";
    private int savePosition = 0;
    private boolean saveValue = false;

    private ReConfig() {
        
    }
    
    public int getTextSizeMin() {
        return textSizeMin;
    }

    public void setTextSizeMin(int value) {
        if (value > 0 && value < textSizeMax) {
            textSizeMin = value;
        }
    }

    public int getTextSizeMax() {
        return textSizeMax;
    }

    public void setTextSizeMax(int value) {
        if (value > 0 && value > textSizeMax) {
            textSizeMax = value;
        }
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int value) {
        if (value > 0) {
            fontSize = value;
        }
    }

    public String getFontType() {
        return fontType;
    }

    public void setFontType(String value) {
        if (value.length() > 0 && value != null) {
            fontType = value;
        }
    }

    public int getWordSpeed() {
        return wordSpeed;
    }

    public void setWordSpeed(int value) {
        if (wordSpeed > 0 && wordSpeed < 2500) {
            wordSpeed = value;
        }
    }

    public String getSaveLang() {
        return saveLang;
    }

    public void setSaveLang(String value) {
        saveLang = value;
    }
    
    public int getSavePosition() {
        return savePosition;
    }

    public void setSavePosition(int value) {
        savePosition = value;
    }

    public boolean getSaveValue() {
        return saveValue;
    }

    public void setSaveValue(boolean value) {
        saveValue = value;
    }

    public void main (String [] args) {
        readXML("default.xml");
    }
    
    public void readXML(String file) {
        String workingDir = System.getProperty("user.dir");
        String xmlDir = workingDir + "/conf/";
        String xmlFile = xmlDir + file;
System.out.println(xmlFile);
        Document xmlDOM = ReFile.openXML(xmlFile);

        setTextSizeMin(Integer.parseInt(xmlDOM.getElementsByTagName("textSizeMin").item(0).getTextContent()));
        setTextSizeMax(Integer.parseInt(xmlDOM.getElementsByTagName("textSizeMax").item(0).getTextContent()));
        setFontSize(Integer.parseInt(xmlDOM.getElementsByTagName("fontSize").item(0).getTextContent()));
        setFontType(xmlDOM.getElementsByTagName("fontType").item(0).getTextContent());
        setWordSpeed(Integer.parseInt(xmlDOM.getElementsByTagName("wordSpeed").item(0).getTextContent()));
        setSaveLang(xmlDOM.getElementsByTagName("saveLang").item(0).getTextContent());
        setSavePosition(Integer.parseInt(xmlDOM.getElementsByTagName("savePosition").item(0).getTextContent()));
        setSaveValue(Boolean.parseBoolean(xmlDOM.getElementsByTagName("saveValue").item(0).getTextContent()));
    }

}
