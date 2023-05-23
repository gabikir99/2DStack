import java.awt.*;

public class Block {
    private int x;
    private int y;
    private int width;
    private int height;
    private int direction;

    public Block(int y, int width, int x) {
        this.y = y;
        this.width = width;
        this.x = x;
        height = 10;
        direction = 1;
    }

    public void move() {
        x += direction;
        if (x + width > 400 || x < 0) {
            direction *= -1;
        }
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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getOverlap(Block other) {
        int overlapStart = Math.max(this.x, other.x);
        int overlapEnd = Math.min(this.x + this.width, other.x + other.width);
        return overlapEnd - overlapStart;
    }
}