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
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import javax.imageio.ImageIO;



public class Main {
    // This is used for the checker function. since I cant get that to work, I
    // don't have a need for this at this time.
    static boolean isSteamRunning=false;
    static boolean hasFileChanged=false;
    
    // These will be used my more than just the loader function, so I decided
    // that I should probably move it out here so I only have to deal with it once.
    

    static File bgOutput = new File("C:\\Program Files (x86)\\Steam\\tenfoot\\resource\\images\\mainmenu_bg_2.png");
    

    // I should Probably put the interface elements somewhere else, but for now
    // they are right here.
    
    public static void main(String[] args) throws IOException, InterruptedException {
        //Creates the custom background folder. would like to have this set in a gui in the future.
        File bgFolder = new File("C:\\SteamCustomBackGround");
        // Checks if the background folder exists, if not the folder is created.
        if (bgFolder.exists()){
        }
        else {
            bgFolder.mkdir();
        }
        BufferedImage bgInput = ImageIO.read(new File("c:\\SteamCustomBackGround\\SteamBG.png"));
        
        loader(bgInput);
        isChanged(bgInput);

        /*if (hasSteamChangedFiles==true) {
            loader();
            hasSteamChangedFiles=false;
        }
        /*while (isSteamRunning==false){
            checker(isSteamRunning);
        }
        if (isSteamRunning==true){
            loader();
        }
        */
        
    }

    //Testing a different way in order to compare the images.
    public static void isChanged(BufferedImage input) throws IOException, InterruptedException {
    
    Path tenfoot = FileSystems.getDefault().getPath("C:\\Program Files (x86)\\Steam\\tenfoot\\resource\\images\\");
    WatchService watcher = FileSystems.getDefault().newWatchService();   
    WatchKey tenKey = tenfoot.register(watcher, ENTRY_MODIFY);
    while (true) {
        WatchKey key = watcher.take();
        
        for (WatchEvent<?> event : key.pollEvents()) {
            Path changed = (Path) event.context();
            System.out.println(changed);
            if (changed!=null) {
                //Thread.sleep(1750);
                loader(input);
            }
        }
        boolean valid = key.reset();
    if (!valid) {
        System.out.println("Key has been unregisterede");
    }
    }

    /*final Path path = FileSystems.getDefault().getPath("C:\\Program Files (x86)\\Steam\\tenfoot\\resource\\images\\");
    System.out.println(path);
    final WatchService watchService = FileSystems.getDefault().newWatchService();
    final WatchKey watchKey = path.register(watchService, ENTRY_MODIFY);
    while (true) {
    final WatchKey wk = watchService.take();
    for (WatchEvent<?> event : wk.pollEvents()) {
        //we only register "ENTRY_MODIFY" so the context is always a Path.
        final Path changed = (Path) event.context();
        System.out.println(changed);
        if (changed.endsWith("mainmenu_bg_2.png")) {
            System.out.println("My file has changed");
        }
    }
    // reset the key
    boolean valid = wk.reset();
    if (!valid) {
        System.out.println("Key has been unregisterede");
    }
}*/
    }


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

    public static void loader (BufferedImage input) throws InterruptedException {
        // Load the Image the person selected into Java, then set as big picture mode background.
            try {
                // Decided to streamline this a bit, it reads the inputed image,
                // then outputs the image as the Background image for big picture mode.
                Thread.sleep(50);
                ImageIO.write(input, "png", bgOutput);
            }
            catch (IOException e) {
            }
    }
}
