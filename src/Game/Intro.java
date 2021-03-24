package Game;

import Utility.ResourceLoader;
import Utility.Sound;

import java.awt.*;


public class Intro {
    private final Image BACKGROUND;
    private final Image HERO;
    private final Image FRIEND_INTRO;
    private final Image FRIEND_MIRROR_INTRO;
    private final Image BONFIRE_1;
    private final Image BONFIRE_2;
    private final Image FLAME_1;
    private final Image FLAME_2;
    private final Image FIREWOOD;
    private final Image DID_YOU_WORD;
    private final Image NO_WORD;
    private final Image LOOK_WORD;
    private final Image NO_DANGER_WORD;
    private final Image HEY_1_WORD;
    private final Image HEY_2_WORD;
    private final Image HEY_AAA_WORD;
    private final Image FUCK_WORD;
    private final Sound CLICK_SOUND  = new Sound(ResourceLoader.uploadAudio("assets/button_click_2.wav"));
    private Image imageFriend;

    private final long[][] DELAY_ARRAY;
    private final int[][] X_FRIEND;
    private final int[][] Y_FRIEND;

    private int  i=0,j=0,i1=0, i4=0,i5=0,i6=0;
    private int  xFriendCoord = 540,yFriendCoord = 340;

    private boolean introMustGoOn = true;
    private long deltaFire = System.currentTimeMillis();
    private long deltaText = System.currentTimeMillis();
    private long deltaMoveFriend = System.currentTimeMillis();


    {
        HEY_AAA_WORD = ResourceLoader.uploadImage("assets/HEY_AAA.png");
        BACKGROUND  = ResourceLoader.uploadImage("assets/prev_green_forest.png");
        HERO  = ResourceLoader.uploadImage("assets/man_intro.png");
        FRIEND_INTRO  = ResourceLoader.uploadImage("assets/friend.png");
        FRIEND_MIRROR_INTRO  = ResourceLoader.uploadImage("assets/friend_mirror.png");
        imageFriend = FRIEND_MIRROR_INTRO;
        BONFIRE_1  = ResourceLoader.uploadImage("assets/bonfire1.png");
        BONFIRE_2  = ResourceLoader.uploadImage("assets/bonfire2.png");
        FLAME_1  = ResourceLoader.uploadImage("assets/flame1.png");
        FLAME_2  = ResourceLoader.uploadImage("assets/flame2.png");
        FIREWOOD  = ResourceLoader.uploadImage("assets/firewood_small_1.png");
        DID_YOU_WORD  = ResourceLoader.uploadImage("assets/DID_YOU_classic_14.png");
        NO_WORD  = ResourceLoader.uploadImage("assets/NO.png");
        LOOK_WORD  = ResourceLoader.uploadImage("assets/LOOK.png");
        NO_DANGER_WORD  = ResourceLoader.uploadImage("assets/NO_DANGER.png");
        HEY_1_WORD = ResourceLoader.uploadImage("assets/HEY_HEY.png");
        HEY_2_WORD  = ResourceLoader.uploadImage("assets/HEY_HEY.png");
        FUCK_WORD  = ResourceLoader.uploadImage("assets/FUCK.png");



        long tFrz = 30L;
        DELAY_ARRAY  = new long[][]{
                {tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, 1000},
                {tFrz, tFrz, tFrz, 1700},
                {tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, 1000},
                {tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, 1700},
                {tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, 1500},
                {tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, 1800},
                {tFrz, tFrz, tFrz, tFrz, 600, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, 2000},
                {tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, tFrz, 2500, tFrz}};

        X_FRIEND = new int[][]{
                {},
                {},
                {},
                {},
                {540,530,520,510,500,490,480,470,460,450, 440,430,420,410,400,390,380,370,360,350, 340,330,320, 310,300,290},
                {290,300,310,320,330,340,350,360,370,380, 390,400,410,420,430,440,450,460,470,480, 490,500,510, 520,530,540},
                {540,530,520,510,500,490,480,470,460,450, 440,430,420,410,400,390,380,370,360,350, 340,330,320, 310,300,290,
                        270,250,230,210,190,170,150,130,110,90,70,50,30,10,-10,-30,-50,-70,-90,-110}};

        Y_FRIEND = new int[][]{
                {},
                {},
                {},
                {},
                {340,340,340, 340,331,314,299,286,275,266,259,254,251, 251,254,259,266,275,286,299,314,331,340, 340,340,340},
                {340,340,340, 340,331,314,299,286,275,266,259,254,251, 251,254,259,266,275,286,299,314,331,340, 340,340,340,},
                {340,340,340, 340,331,314,299,286,275,266,259,254,251, 251,254,259,266,275,286,299,314,331,340, 340,340,340,
                        340,340,340,340,340,340,340,340,340,340,340,340,340,340,340,340,340,340,340,340}};
    }

    boolean getIntroMustGoOn() {
        return introMustGoOn;
    }

    private void drawBonfire(Graphics g){
        if ((System.currentTimeMillis() - deltaFire) > 350){
            g.drawImage(BONFIRE_1, 420, 320, null);
            deltaFire = System.currentTimeMillis();
        }else {
            g.drawImage(BONFIRE_2, 420, 320, null);
        }
    }

    private void drawFlame(Graphics g){
        if ((System.currentTimeMillis() - deltaFire) > 174){
            g.drawImage(FLAME_1, xFriendCoord+50, yFriendCoord+25, null);
        }else {
            g.drawImage(FLAME_2, xFriendCoord+50, yFriendCoord+25, null);
        }
    }

    private void textOutput(Graphics g, int item,int offset){
        if (item == 0 ) {
            g.setColor(new Color(190, 224, 197));
            g.fillRect(20,25, 10, 3);
            g.setColor(Color.BLACK);
            g.drawImage(DID_YOU_WORD, 40, 20, null);
            g.fillRect(40+offset,20, 590, 25);
        }
        if (item == 1 ) {
            g.setColor(new Color(190, 224, 197));
            g.fillRect(20,25, 10, 3);
            g.fillRect(20,53, 10, 3);
            g.setColor(Color.BLACK);
            g.drawImage(DID_YOU_WORD, 40, 20, null);
            g.drawImage(NO_WORD, 40, 48, null);
            g.fillRect(40+offset,48, 590, 45);
        }

        if (item == 2 ) {
            g.setColor(new Color(190, 224, 197));
            g.fillRect(20,25, 10, 3);
            g.setColor(Color.BLACK);
            g.drawImage(LOOK_WORD, 40, 20, null);
            g.fillRect(40+offset,20, 590, 25);
        }
        if (item == 3 ) {
            g.setColor(new Color(190, 224, 197));
            g.fillRect(20,25, 10, 3);
            g.fillRect(20,53, 10, 3);
            g.setColor(Color.BLACK);
            g.drawImage(LOOK_WORD, 40, 20, null);
            g.drawImage(NO_DANGER_WORD, 40, 48, null);
            g.fillRect(40+offset,48, 590, 45);
        }
        if (item == 4 ) {
            //jump to LEFT
            g.setColor(new Color(190, 224, 197));
            g.fillRect(20,25, 10, 3);
            g.setColor(Color.BLACK);
            g.drawImage(HEY_1_WORD, 40, 20, null);
            g.fillRect(40+offset,20, 590, 25);
        }
        if (item == 5 ) {
            //jump to RIGHT
            g.setColor(new Color(190, 224, 197));
            g.fillRect(20,25, 10, 3);
            g.fillRect(20,53, 10, 3);
            g.setColor(Color.BLACK);
            g.drawImage(HEY_1_WORD, 40, 20, null);
            g.drawImage(HEY_2_WORD, 40, 48, null);
            g.fillRect(40+offset,48, 590, 45);
        }
        if (item == 6 ) {
            //jump to LEFT and RUN
            g.setColor(new Color(190, 224, 197));
            g.fillRect(20,25, 10, 3);
            g.setColor(Color.BLACK);
            g.drawImage(HEY_AAA_WORD, 40, 20, null);
            g.fillRect(40+offset,20, 590, 25);
        }
        if (item == 7 ) {
            g.setColor(new Color(190, 224, 197));
            g.fillRect(20,25, 10, 3);
            g.fillRect(20,53, 10, 3);
            g.setColor(Color.BLACK);
            g.drawImage(HEY_AAA_WORD, 40, 20, null);
            g.drawImage(FUCK_WORD, 40, 48, null);
            g.fillRect(40+offset,48, 590, 45);

        }
    }

    private void textDrawing(Graphics g){
        if((System.currentTimeMillis() - deltaText) > DELAY_ARRAY[j][i]){
            deltaText = System.currentTimeMillis();
            i1+=14;

            if(i <DELAY_ARRAY[j].length-1) {
                i++;
                if (j==7 && i==8) CLICK_SOUND.setVolume(0);
                CLICK_SOUND.play();
            }
            else {
                if (j < DELAY_ARRAY.length-1){
                    j++;
                    i = 0;
                    i1 = 0;
                }
            }
        }
        textOutput(g, j, i1);
        if (j == 4){
            if((System.currentTimeMillis() - deltaMoveFriend) > 20){

                  if(i4 < 25) {
                    i4++;
                }

                yFriendCoord = Y_FRIEND[j][i4];
                xFriendCoord = X_FRIEND[j][i4];

                deltaMoveFriend = System.currentTimeMillis();
            }
        }
        if (j == 5){
            if((System.currentTimeMillis() - deltaMoveFriend) > 20){
                imageFriend  = FRIEND_INTRO;

                if(i5 < 25) {
                    i5++;
                }

                yFriendCoord = Y_FRIEND[j][i5];
                xFriendCoord = X_FRIEND[j][i5];

                deltaMoveFriend = System.currentTimeMillis();
            }
        }
        if (j == 6){
            if((System.currentTimeMillis() - deltaMoveFriend) > 20){
                imageFriend  = FRIEND_MIRROR_INTRO;

                if(i6 < 45) {
                    i6++;
                }

                yFriendCoord = Y_FRIEND[j][i6];
                xFriendCoord = X_FRIEND[j][i6];
                deltaMoveFriend = System.currentTimeMillis();
                if (i6 > 12){
                    drawFlame(g);
                }
            }
        }

        if (j==7 && i==(DELAY_ARRAY[j].length-1)){
            introMustGoOn = false;
        }


    }

    void render(Graphics g, long delayForFreezeIntro){
        g.setClip(0,0,820,500);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 820, 500);

        g.drawImage(BACKGROUND, 0, 0, null);
        g.drawImage(FIREWOOD, 400, 380, null);
        g.drawImage(HERO, 150, 340, null);
        g.drawImage(imageFriend, xFriendCoord, yFriendCoord, null);

        drawBonfire(g);

        if ((System.currentTimeMillis() - delayForFreezeIntro) > 1000){
            textDrawing(g);
        }
    }

}
