import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class Game {
    private JFrame frame;
    private Tower tower;

    public Game() {
        frame = new JFrame("Stacking Game");
        tower = new Tower();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.add(tower);
        frame.setVisible(true);

        Action stopAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tower.tryToAddBlock();
            }
        };

        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(enter, "STOP_ACTION");
        frame.getRootPane().getActionMap().put("STOP_ACTION", stopAction);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}