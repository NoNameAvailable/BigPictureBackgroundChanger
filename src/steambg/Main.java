/**
 *
 * @author James Pattemore
 * Written 2014/03/17
 * This is an application that changes the default background of Big Picture mode to
 * one of the users choosing.
 */

package steambg;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;



public class Main {
    /* This is used for the checker function. since I cant get that to work, I 
     * don't have a need for this at this time.
     */
    static boolean isSteamRunning=false;
    

    /* I should Probably put the interface elements somewhere else, but for now
     * they are right here.
     */
    
    public static void main(String[] args) throws IOException, InterruptedException {
        //Creates the custom background folder. would like to have this set in a gui in the future.
        File bgFolder = new File("C:\\SteamCustomBackGround");
        // Checks if the background folder exists, if not the folder is created.
        if (bgFolder.exists()){
        }
        else {
            bgFolder.mkdir();
        }
        while (isSteamRunning==false){
            checker(isSteamRunning);
        }
        if (isSteamRunning==true){
            loader();
        }
        
        
    }

/* I tried to make this work and failed. I'll come back to it in the furture, bot for now, I'm focusing
* on other aspects
*/
    public static void checker (Boolean sRunning) throws IOException, InterruptedException {

            String line;
            try {
                Process proc = Runtime.getRuntime().exec("wmic.exe");
                BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                OutputStreamWriter oStream = new OutputStreamWriter(proc.getOutputStream());
                oStream .write("process where name='steam.exe'");
                oStream .flush();
                oStream .close();
                if (sRunning==false){
                    Thread.sleep(1000);
                    while ((line = input.readLine()) !=null) {
                        if (line.contains("Steam.exe")){
                            Thread.sleep(20000);
                            System.out.println(line);
                            sRunning=true;
                        }
                    }
                }
                input.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            isSteamRunning=sRunning;
            System.out.println(isSteamRunning);
    }

    public static void loader () {
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
