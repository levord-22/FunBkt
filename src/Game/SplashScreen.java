package Game;

import Utility.ResourceLoader;
import Utility.Sound;

import java.awt.*;


public class SplashScreen  {

    private final Image ARMCHAIR_WARRIORS  = ResourceLoader.uploadImage("assets/Armchair_warriors.png");
    private final Image RIFLE_LOGO  = ResourceLoader.uploadImage("assets/rifle_3_res.png");
    private final Image PRESENT  = ResourceLoader.uploadImage("assets/present_26_size1.png");
    private final Sound CLICK_SOUND = new Sound(ResourceLoader.uploadAudio("assets/button_click_2.wav"));

    private int coordinateShift = 0;

    private boolean splashNeverRun = true;
    boolean isSplashNeverRun() {
        return splashNeverRun;
    }
    void setSplashNeverRun(boolean splashNvrRn) {
        splashNeverRun = splashNvrRn;
    }


    private boolean splashScreenEnded = false;
    boolean isSplashScreenEnded() {
        return splashScreenEnded;
    }


    void render(Graphics g){
        if (coordinateShift == 128) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            splashScreenEnded = true;
        }

        g.setClip(0,0,820,500);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 820, 500);

        g.drawImage(RIFLE_LOGO, 70, 60, null);
        g.drawImage(ARMCHAIR_WARRIORS, 100, 160, null);
        g.drawImage(PRESENT, 500, 250, null);
        g.fillRect(500+coordinateShift,250, 120, 30);

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if((coordinateShift>0) && (coordinateShift < 128))CLICK_SOUND.play();
        coordinateShift +=16;
    }
}
