import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tower extends JPanel {
    private Block movingBlock;
    private java.util.List<Block> blocks;

    public Tower() {
        blocks = new ArrayList<>();
        movingBlock = new Block(290, 50, 0);

        Timer timer = new Timer(20, e -> {
            movingBlock.move();
            repaint();
        });

        timer.start();
    }

    public void tryToAddBlock() {
        Block lastBlock = blocks.isEmpty() ? null : blocks.get(blocks.size() - 1);

        if (lastBlock == null) {
            addBlock();
        } else {
            int overlapStart = Math.max(movingBlock.getX(), lastBlock.getX());
            int overlapWidth = movingBlock.getOverlap(lastBlock);

            if (overlapWidth <= 0) {
                // Block missed entirely, create a new block of the same size at starting position
                movingBlock = new Block(lastBlock.getY() - movingBlock.getHeight(), movingBlock.getWidth(), 0);
            } else {
                // Adjust the current block width and position to the overlapped area
                movingBlock.setX(overlapStart);
                movingBlock.setWidth(overlapWidth);
                addBlock();
                // Create a new block of overlap size and position
                movingBlock = new Block(lastBlock.getY() - lastBlock.getHeight(), overlapWidth, overlapStart);
            }
        }
    }

    public void addBlock() {
        blocks.add(movingBlock);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        movingBlock.draw(g);

        for (Block block : blocks) {
            block.draw(g);
        }
    }
}