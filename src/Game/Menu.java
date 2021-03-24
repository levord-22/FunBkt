package Game;

import Utility.ResourceLoader;
import Utility.Sound;

import java.awt.*;


public class Menu {
    private final Image BACKGROUND;
    private final Image TEXT;

    private final Sound MENU_STEP;
    private final Sound SOUND;
    private final Sound BACKGROUND_MUSIC;

    private final WorkLayer WORK_LAYER;
    private final Intro INTRO;
    private final SplashScreen SPLASH_SCREEN;

    private long deltaForFreezeINTRO = 0L;

    private final int[] X_POLYGON;
    private final int[] Y_POLYGON;
    private final int TOTAL_MENU_ITEMS;
    private int currentMenuItem = 0;

    private boolean isFirstStartScreen;

    {
        BACKGROUND  = ResourceLoader.uploadImage("assets/Funny_bucket_start_menu_background.png");
        TEXT  = ResourceLoader.uploadImage("assets/Funny_bucket_start_menu_text.png");

        MENU_STEP  = new Sound(ResourceLoader.uploadAudio("assets/menu_step.wav"));
        SOUND  = new Sound(ResourceLoader.uploadAudio("assets/enter_sound.wav"));
        BACKGROUND_MUSIC  = new Sound(ResourceLoader.uploadAudio("assets/background_music2.wav"));

        X_POLYGON = new int[]{275, 315, 275};
        Y_POLYGON = new int[]{193, 213, 233};
        TOTAL_MENU_ITEMS = 3;
    }

    Menu(WorkLayer workLayer){
        this.WORK_LAYER = workLayer;
        SPLASH_SCREEN = new SplashScreen();
        INTRO = new Intro();
        isFirstStartScreen = true;
    }


    private void update() {
        if (WORK_LAYER.keyListener.isUpPressed()){
            if ( currentMenuItem == 0){

                Y_POLYGON[0] +=156;
                Y_POLYGON[1] +=156;
                Y_POLYGON[2] +=156;
                currentMenuItem = 3;
            }else {
                Y_POLYGON[0] -=52;
                Y_POLYGON[1] -=52;
                Y_POLYGON[2] -=52;
                currentMenuItem--;
            }
            MENU_STEP.play();
        }
        if (WORK_LAYER.keyListener.isDownPressed()){
            if (currentMenuItem == TOTAL_MENU_ITEMS){
                Y_POLYGON[0] -=156;
                Y_POLYGON[1] -=156;
                Y_POLYGON[2] -=156;
                currentMenuItem = 0;
            }else {
                Y_POLYGON[0] +=52;
                Y_POLYGON[1] +=52;
                Y_POLYGON[2] +=52;
                currentMenuItem++;
            }
            MENU_STEP.play();
        }
        if (WORK_LAYER.keyListener.isEnterPressed()){
            SOUND.play();
            if (WORK_LAYER.getSettingStarted() && (currentMenuItem == 0)){
                if (WORK_LAYER.isFirstStartingGame()){
                    WORK_LAYER.setFirstStartingGame(false);
                }else{
                    WORK_LAYER.setSettingStarted(false);
                    WORK_LAYER.setGameplayStarted(true);
                }
            }
            if (WORK_LAYER.getSettingStarted() && (currentMenuItem == 3)){
                System.exit(0);
            }
        }
    }


    private void menu(Graphics g){
        g.setClip(0,0,820,500);

        g.setColor(Color.BLACK);
        g.fillRect(0,0, WORK_LAYER.getWidth()+50, WORK_LAYER.getHeight()+50);

        g.drawImage(BACKGROUND, -80, 18, null);
        g.drawImage(TEXT, -80, 18, null);
        g.setColor(new Color(33, 111, 71));
        g.fillPolygon(new Polygon(X_POLYGON,Y_POLYGON,3));
    }


    void render(Graphics g) {
            if (WORK_LAYER.isFirstStartingGame())
            {
                if (SPLASH_SCREEN.isSplashNeverRun()){
                    SPLASH_SCREEN.render(g);
                    if (SPLASH_SCREEN.isSplashScreenEnded()){
                        SPLASH_SCREEN.setSplashNeverRun(false);
                    }

                }else {
                    if ( WORK_LAYER.backgroundMusic.getVolume() < 0.1f) {
                        WORK_LAYER.backgroundMusic.setVolume(0.8f);
                        WORK_LAYER.backgroundMusic.playLoop();
                    }
                    menu(g);
                }
            }else {
                if (isFirstStartScreen){
                    //playing INTRO for Game
                    if (deltaForFreezeINTRO==0){
                        deltaForFreezeINTRO = System.currentTimeMillis();
                    }
                    INTRO.render(g, deltaForFreezeINTRO);
                    if(!INTRO.getIntroMustGoOn()){
                        WORK_LAYER.backgroundMusic.stop();
                        WORK_LAYER.backgroundMusic = BACKGROUND_MUSIC;
                        WORK_LAYER.backgroundMusic.setVolume(0.75f);
                        WORK_LAYER.backgroundMusic.playLoop();

                        isFirstStartScreen = INTRO.getIntroMustGoOn();
                        WORK_LAYER.setSettingStarted(false);
                        WORK_LAYER.setGameplayStarted(true);
                    }
                }else{
                    menu(g);
                }
            }

        if (isFirstStartScreen){
            try {
                Thread.sleep(40L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        update();
    }
}
