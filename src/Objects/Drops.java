package Objects;

import java.awt.Image;
import java.awt.Graphics;
import Game.*;

public class Drops implements Sprite {

    int countDrops;

    Image image;
    public int x;
    public int y;


    public Drops(Image image) {
        this.image = image;
    }


    public Drops[] createDropsArray(int count) {

        Drops[] dropsArray = new Drops[count];
        countDrops = count;

        for (int i = 0; i < count; i++) {
            Drops drops = new Drops(image);
            drops.x = (int) (Math.random() * (WorkLayer.WIDTH-25));
            drops.y = (-1) * i * WorkLayer.HEIGHT / 3;
            dropsArray[i] = drops;
        }
        return dropsArray;
    }

    public int getCountDrops() {
        return countDrops;
    }

    public void setNewСoordinate(){
        this.x = (int) (Math.random() * WorkLayer.WIDTH);
        this.y = 0;
    }

    public void draw(Graphics g) {
        g.drawImage(image, this.x, this.y, null);
        this.y += 1;
        if (this.y >= (WorkLayer.HEIGHT-25)) {
            setNewСoordinate();
        }
    }

     public boolean isDropTouchFloor( ){
        if (this.y == ((WorkLayer.HEIGHT-25) - this.getHeight())){

            return true;
        }
        return false;
    }
    public boolean isDropTouchBucket(Protagonist hero){
        if ((this.y == (hero.y - this.getHeight())) && (Math.abs((this.x+12) - (hero.x+23))<15)){
            return true;
        }
        return false;
    }

    @Override
    public int getWidth() {
        return image.getWidth(null);
    }

    @Override
    public int getHeight() {
        return image.getHeight(null);

    }
}
