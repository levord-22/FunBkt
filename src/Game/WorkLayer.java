package Game;

import Utility.KeyInputHandler;
import Utility.ResourceLoader;
import Utility.Sound;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class WorkLayer extends Canvas implements Runnable{

    private boolean running = false;

    public boolean isFirstStartingGame() {return isFirstStartingGame;}
    public void setFirstStartingGame(boolean firstStartingGame) {isFirstStartingGame = firstStartingGame;}
    private boolean isFirstStartingGame = true;

    public boolean getGameplayStarted() {
        return isGameplayStarted;
    }
    public void setGameplayStarted(boolean gameplayStarted) {isGameplayStarted = gameplayStarted;}
    private boolean isGameplayStarted = false;


    public boolean getSettingStarted() {
        return isSettingStarted;
    }
    public void setSettingStarted(boolean settingStarted) {
        isSettingStarted = settingStarted;
    }
    private boolean isSettingStarted = false;


    public  boolean isStartCanvasAdd = false;
    public  boolean isGameCanvasAdd = false;

    public static int WIDTH = 800;
    public static int HEIGHT = 480;
    public static String NAME = "FunnyBucket";

    public Sound backgroundMusic;
    public KeyInputHandler keyListener = new KeyInputHandler(this);

    static WorkLayer workLayer = new WorkLayer();

    Menu menu;
    static JFrame frame;
    Game game;


    public WorkLayer(){
        addKeyListener(keyListener);
        game = new Game(this);
        menu = new Menu(this);
    }



    @Override
    public void run() {
        setSettingStarted(true);
        backgroundMusic.setVolume(0.0f);
       // backgroundMusic.playLoop();

        while (running){
            render();
        }
    }



    public void start(){
        running = true;
        new Thread(this).start();
    }


    public void init() {
        backgroundMusic = new Sound(ResourceLoader.uploadAudio("assets/background_music1.wav"));
        addKeyListener(keyListener);
    }


    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(2);
            requestFocus();
            System.out.println("Return null BS from Game");
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0, getWidth(), getHeight());


        if (getSettingStarted()){
            if (!isStartCanvasAdd){
                isStartCanvasAdd = true;
                isGameCanvasAdd = false;
            }

           menu.render(g);
        }
        if (getGameplayStarted()){
            if (!isGameCanvasAdd){
                isGameCanvasAdd = true;
                isStartCanvasAdd = false;
            }

            game.render(g);


            if (keyListener.isEscPressed() &&  !isStartCanvasAdd){
                if (this.getGameplayStarted()) {
                    this.setGameplayStarted(false);
                    this.setSettingStarted(true);
                }
            }
        }

        g.dispose();

        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bs.show();
    }


    public void go(){
        /* -------------- worked variant --------------------*/
        workLayer.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(workLayer);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        workLayer.init();
        workLayer.start();
    }
}
