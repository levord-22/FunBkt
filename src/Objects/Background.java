package Objects;

import java.awt.*;

public class Background implements Sprite{
    Image image;

    public Background(Image image) {
        this.image = image;
    }

    @Override
    public int getWidth() {
        return 800;
    }

    @Override
    public int getHeight() {
        return 480;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,0, 0, null);
    }
}
