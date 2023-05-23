import java.awt.*;

public class Block {
    private int x;
    private int y;
    private int width;
    private int height;
    private int direction;
    private int speed;

    public Block(int y, int width, int x, int speed) {
        this.y = y;
        this.width = width;
        this.x = x;
        height = 10;
        direction = 1;
        this.speed = speed;
    }

    public void move() {
        x += direction * speed;
        if (x + width > 400 || x < 0) {
            direction *= -1;
        }
    }

    public int getSpeed(){
        return speed;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getOverlap(Block other) {
        int overlapStart = Math.max(this.x, other.x);
        int overlapEnd = Math.min(this.x + this.width, other.x + other.width);
        return overlapEnd - overlapStart;
    }
}