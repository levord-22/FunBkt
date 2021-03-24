package Utility;

import Game.WorkLayer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputHandler extends KeyAdapter {
    private WorkLayer workLayer;
    public KeyInputHandler(WorkLayer workLayer) {
        this.workLayer = workLayer;
    }
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean escPressed = false;
    private boolean enterPressed = false;

    public boolean isLeftPressed() {
        return leftPressed;
    }

    private void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    private void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isEscPressed() {
        return escPressed;
    }

    public void setEscPressed(boolean escPressed) {
        this.escPressed = escPressed;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if (workLayer.getGameplayStarted()) setLeftPressed(true);

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (workLayer.getGameplayStarted()) setRightPressed(true);

        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            setUpPressed(true);

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            setDownPressed(true);

        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            setEscPressed(true);

        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            setEnterPressed(true);

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            setLeftPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            setRightPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            setUpPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            setDownPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            setEscPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            setEnterPressed(false);
        }

    }

}
