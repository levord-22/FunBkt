package Game;

import Objects.Background;
import Objects.Drops;
import Objects.Protagonist;
import Utility.ResourceLoader;
import Utility.Sound;

import java.awt.*;


public class Game {

    private int ground_Drops = 0;

    private final Protagonist HERO;
    private final Drops[] DROPS;
    private final Background BACKGROUND;
    private final Sound DROP_TOUCHES_GROUND;
    private final Sound DROP_TOUCHES_BUCKET;


    Game(WorkLayer workLayer){
        HERO = new Protagonist(workLayer, ResourceLoader.uploadImage("assets/man_60_mirror.png"),ResourceLoader.uploadImage("assets/man_60.png"));
        DROPS = new Drops(ResourceLoader.uploadImage("assets/drop_small.png")).createDropsArray(3);
        BACKGROUND = new Background(ResourceLoader.uploadImage("assets/background_800.png"));
        DROP_TOUCHES_GROUND = new Sound(ResourceLoader.uploadAudio("assets/drop_touches_the_ground.wav"));
        DROP_TOUCHES_GROUND.setVolume(0.85f);
        DROP_TOUCHES_BUCKET = new Sound(ResourceLoader.uploadAudio("assets/drop_touches_the_bucket.wav"));
        DROP_TOUCHES_BUCKET.setVolume(0.8f);
    }

    private void dropsDraw(Graphics g){
        for (int i = 0; i < DROPS.length; i++) {
            if(!DROPS[i].isDropTouchFloor()){
                if (DROPS[i].isDropTouchBucket(HERO)) {
                    HERO.setDropsInBucket(HERO.getDropsInBucket()+1);
                    DROPS[i].setNewСoordinate();
                    DROP_TOUCHES_BUCKET.play();
                }else {
                    DROPS[i].draw(g);
                }
            }else {
                DROPS[i].setNewСoordinate();
                ground_Drops++;
                DROP_TOUCHES_GROUND.play();
            }
        }
    }


    public void render(Graphics g) {
        BACKGROUND.draw(g);
        dropsDraw(g);

        g.setColor(new Color(144, 255, 116, 122));
        g.setFont( new Font( Font.MONOSPACED, Font.BOLD, 28 ));
        g.drawString("B " + HERO.getDropsInBucket(),700, 50);
        g.setColor(new Color(30, 128, 137, 158));
        g.setFont( new Font( Font.MONOSPACED, Font.BOLD, 20 ));
        g.drawString("G " + ground_Drops,720, 70);

        HERO.update();
        HERO.draw(g);
    }

}