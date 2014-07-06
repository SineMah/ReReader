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
import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

public class ReFile {

    String openFile(String pathname) throws IOException {
        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int) file.length());
//        Scanner scanner = new Scanner(file);
        Scanner scanner = new Scanner((Readable) new BufferedReader(new FileReader(file)));
        String lineSeparator = System.getProperty("line.separator");

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }

    public static String readFile(String pathname) throws IOException {
        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int) file.length());
//        Scanner scanner = new Scanner(file);
        Scanner scanner = new Scanner((Readable) new BufferedReader(new FileReader(file)));
        String lineSeparator = System.getProperty("line.separator");

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }
    
    public static String readFileEncoded(String pathname) throws IOException {
        String content = "";
        String part;
        String lineSeparator = System.getProperty("line.separator");
        File file = new File(pathname);
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        int i = 1;

        while ((part = in.readLine()) != null) {
//            PrintStream out = new PrintStream(System.out, true, "UTF-8");
//            out.println(part);
            content += part + lineSeparator;     
            
            ReUI.setProgressLabel("Reading line " + i);
//            ReUI.setProgressBar(i);
            i++;
        }
        
        in.close();
        
        return content;

}
    
    public static Document openXML(String path) {
        Document xml = null;

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            File xmlFile = new File(path);
            xml = docBuilder.parse(xmlFile);
            xml.getDocumentElement().normalize();
        } catch (Exception e) {
            System.out.println("no xml file");
        }

        return xml;
    }

}
