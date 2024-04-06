package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    /* Utility class for different functionalities */

    public BufferedImage scaleImage(BufferedImage og, int width, int height){
        /* will optimize rendering by scaling the images beforehand through 2D graphics,
        optimizes draw time by around 25% */

        BufferedImage scaledImage = new BufferedImage(width, height, 2); /* Selected imageType 2
         as a solution to a minor bug*/

        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(og, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
    public static int centeredTextX(String string, Graphics2D g2, JPanel panel, int panelWidth){
        //Make Strings centered on x-axis
        int x;
        int textLength = (int)g2.getFontMetrics().getStringBounds(string, g2).getWidth();
        x=panelWidth/2-textLength/2; //x=middle of screen-middle of textLength
        return x;
    }
}
