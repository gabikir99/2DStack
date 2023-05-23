import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tower extends JPanel {
    private Block movingBlock;
    private java.util.List<Block> blocks;
    private int score;
    private double speed;
    private final JLabel jackpotLayout;

    public Tower() {
        blocks = new ArrayList<>();
        speed = 1.0;
        movingBlock = new Block(290, 50, 0, (int) speed);

        Timer timer = new Timer(20, e -> {
            movingBlock.move();
            repaint();
        });
        score = 0;
        timer.start();

        setLayout(new BorderLayout());
        jackpotLayout = new JLabel("Jackpot : 500$");
        jackpotLayout.setHorizontalAlignment(SwingConstants.CENTER);
        jackpotLayout.setFont(new Font("Times new Roman", Font.BOLD, 50));
        add(jackpotLayout, BorderLayout.SOUTH);
    }

    public void tryToAddBlock() {
        Block lastBlock = blocks.isEmpty() ? null : blocks.get(blocks.size() - 1);

        if (lastBlock == null) {
            // No blocks yet, add moving block to the tower
            blocks.add(movingBlock);
            // Create a new moving block
            movingBlock = new Block(290 - movingBlock.getHeight(), 50, 0, (int) speed);
            score++;
        } else {
            int overlapStart = Math.max(movingBlock.getX(), lastBlock.getX());
            int overlapWidth = movingBlock.getOverlap(lastBlock);

            if (overlapWidth <= 0) {
                // Block missed entirely, show "Game Over" message
                JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                mainFrame.dispose();
                JOptionPane.showMessageDialog(this, "Game Over");
                System.exit(0);
            } else {
                // Adjust the current block width and position to the overlapped area
                movingBlock.setX(overlapStart);
                movingBlock.setWidth(overlapWidth);
                // Move the block up to fill the empty space
                movingBlock.setY(lastBlock.getY() - movingBlock.getHeight());
                // Add moving block to the tower
                blocks.add(movingBlock);
                // Create a new moving block at the position just above the last block
                // and with the width of the overlap
                movingBlock = new Block(lastBlock.getY() - 2 * movingBlock.getHeight(), overlapWidth, 0, (int) speed);
                speed += 0.25;
                score++;

                if (score == 25){
                    JOptionPane.showConfirmDialog(this, "Jackpot You Win 500$");
                }
            }
        }
    }


    public void addBlock() {
        blocks.add(movingBlock);
    }

    private int getMaxHeight() {
        int maxHeight = 0;
        for (Block block : blocks) {
            maxHeight = Math.max(maxHeight, block.getY());
        }
        return maxHeight;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        movingBlock.draw(g);

        for (Block block : blocks) {
            block.draw(g);
        }

        g.setColor(Color.BLACK);
        g.drawString("Score =" + score, 10, 20);
        g.drawString("Hit 25 for Jackpot", 10, 40);
    }
}