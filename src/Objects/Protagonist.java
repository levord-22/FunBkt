package Objects;

import java.awt.Image;
import java.awt.Graphics;
import Game.WorkLayer;

public class Protagonist implements Sprite{
    Image imageLeftGoing, imageRightGoing;

    WorkLayer workLayer;
    int dropsInBucket = 0;


    public  int x = 0;
    public  int y = 378;

    public Protagonist(WorkLayer workLayer, Image imageLeftGoing, Image imageRightGoing) {

        this.imageLeftGoing = imageLeftGoing;
        this.imageRightGoing = imageRightGoing;
        this.workLayer = workLayer;

    }

    public void update() {
        if (workLayer.keyListener.isLeftPressed()){


            if (this.x > 0) {
                this.x -= 4;
            }
        }
        if (workLayer.keyListener.isRightPressed()){

            if (this.x < (workLayer.WIDTH-60)) {
                this.x += 4;
            }
        }
    }

    public int getDropsInBucket() {
        return dropsInBucket;
    }

    public void setDropsInBucket(int dropsInBucket) {
        this.dropsInBucket = dropsInBucket;
    }



    public int getWidth(){
        return imageLeftGoing.getWidth(null);
    }

    public int getHeight(){
        return imageLeftGoing.getHeight(null);
    }

    public void draw(Graphics g){
        boolean isLooksToTheLeft = false;
        if (workLayer.keyListener.isLeftPressed()){
            g.drawImage(imageLeftGoing, this.x, this.y, null);
            isLooksToTheLeft = true;
        }
        if (workLayer.keyListener.isRightPressed() || !isLooksToTheLeft){
            g.drawImage(imageRightGoing, this.x, this.y, null);
            isLooksToTheLeft = false;
        }

    }

}