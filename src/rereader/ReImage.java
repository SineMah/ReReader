

package rereader;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.applet.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class ReImage {
    
    private static ReConfig config = ReGlobals.initGlobals(); 
    
    public static Image[] generateImageList(String[] words) {
        words = ReText.proofWords(words);
        float perc = 100;
        float maxLength = words.length;
        
        Image[] images = new Image[words.length];
        
        ReUI.setProgressLabel("Generate word list");
        
        for(int i=0; i<words.length; i++) {
//            images[i] = generateImage(words[i], 'b');
            images[i] = proofWord(words[i]);
            
            ReStatus.setProgress(i, maxLength, perc); 
        }
        
        return  images;
    }
    
    private static Image generateImage(String text, char color) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font(config.getFontType(), Font.PLAIN, config.getFontSize());
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        
        switch(color) {
            case 'r': 
                g2d.setColor(Color.RED);
                break;
            default: 
                g2d.setColor(Color.BLACK);
                break; 
        }
        
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();
//        try {
//            ImageIO.write(img, "png", new File("Text.png"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        
//        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        return img;
    }
    
    private static Image proofWord(String word) {
//        just cunt alpha nummeric stuff
        int texLength = word.replaceAll("[^a-zA-Z0-9]", "").length();
        int position = 0;
        String[] parts;
  
        if(texLength == 1) {
            position = 1;
//            highlight first
        }else if(texLength <= 4) {
            position = 2;
//            highlight second
        }else if (texLength < 9) {
            position = 3;
//            highlight third
        }else {
            position = 4;
//            highlight fourth
        }
        
        parts = buildWordParts(word, position);
        
//        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        return buildImageParts(parts);
    }
    
    private static String[] buildWordParts(String word, int delimeter) {
        String[] chars = word.split("(?<=\\G.{1})");
        String[] parts =new String[3];
        
        for(int i=0; i<parts.length; i++) {
            parts[i] = "";
        }
        
        for(int i=0; i<chars.length; i++) {
            if(i < delimeter) {
                parts[0] = parts[0] + chars[i];
            }else if(i == delimeter) {
                parts[1] = parts[1] + chars[i];
            }else {
                 parts[2] = parts[2] + chars[i];
            }
        }
        
        return parts;
    }
    
    private static Image buildImageParts(String[] parts) {
         Image[] images = new Image[parts.length];
        
        for(int i=0; i<parts.length; i++) {
            if(parts[i].length() > 0) {
                if(i != 1) {
                    images[i] = generateImage(parts[i], 'b');
                }else {
                    images[i] = generateImage(parts[i], 'r');
                }
            }else if(parts[i].length() == 1) {
                    images[i] = generateImage(parts[i], 'b');
            }
            
        }
        
        interpolateImage(images);
//        images = interpolateImage(images);
        
//        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);        
        return mergeImages(images);
    }
    
    private static Image[] interpolateImage(Image[] imageParts) {
        Image[] firstImage = new Image[2];
        int width;
        int height;
        int maxOffset = config.getTextOffset();
        
//        just the first part needs to get a new size to center all words
        if(imageParts.length > 0 ) {
            width = maxOffset - imageParts[0].getWidth(null);
            height =  imageParts[0].getHeight(null);
            
            firstImage[0] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            firstImage[1] = imageParts[0];
            
            imageParts[0] = mergeImages(firstImage);
        }
        
        return imageParts;
    }
    
    private static Image mergeImages(Image[] images) {
        int maxWidth = 0;
        int maxHeight = 0;
        int offset = 0;
        
//        get widths and height
        for(int i=0;i<images.length; i++) {
            if(images[i] != null) {
                maxWidth += images[i].getWidth(null);
                
                if(images[i].getHeight(null) > maxHeight) {
                    maxHeight = images[i].getHeight(null);
                }
            }
         }
//            
        BufferedImage finalImage = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_ARGB);
        
        Graphics g = finalImage.getGraphics();
        
        for(int i=0; i<images.length; i++) {
            if(images[i] != null) {
                g.drawImage(images[i], offset, 0, null);
                offset += images[i].getWidth(null);
            }     
        }
//        
        return finalImage;
//        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);   
    }
}
