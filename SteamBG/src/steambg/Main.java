/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package steambg;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *
 * @author James Pattemore
 * Written 2014/03/17
 * This is an application that changes the default background of Big Picture mode to
 * one of the users choosing.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creates the custom background folder. would like to have this set in a gui in the future.
        File bgFolder = new File("C:\\SteamCustomBackGround");
        // Checks if the background folder exists, if not the folder is created.
        if (bgFolder.exists()){
        }
        else {
            bgFolder.mkdir();
        }
        // Load the Image the person selected into Java, then set as big picture mode background.
        BufferedImage bgImg = null;
            try {
                // load image into java
                bgImg = ImageIO.read(new File("c:\\SteamCustomBackGround\\SteamBG.png"));
                // output image as the Background image for big picture mode.
                File outputfile = new File("C:\\Program Files (x86)\\Steam\\tenfoot\\resource\\images\\mainmenu_bg_2.png");
                ImageIO.write(bgImg, "png", outputfile);
            }
            catch (IOException e) {
            }
    }

}
