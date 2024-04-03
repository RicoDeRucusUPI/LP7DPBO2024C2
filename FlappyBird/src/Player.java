import java.awt.*;
import java.awt.event.*;
public class Player {
    private int posx;
    private int posY;
    private int width;
    private int height;
    private Image image;
    private int velocityY;

    public boolean jump;

    public Player(int posx, int posY, int width, int height, Image image) {
        this.posx = posx;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.velocityY = -0;
        this.jump = false;
    }

    public int getPosX() {
        return posx;
    }

    public void setPosX(int posx) {
        this.posx = posx;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}