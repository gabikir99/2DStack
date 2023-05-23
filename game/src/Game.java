import java.util.Scanner;

public class Game {
    private final Tower tower;
    private final Scanner scanner;

    public Game() {
        tower = new Tower();
        scanner = new Scanner(System.in);
    }

    public void play() {
        while (true) {
            System.out.print("Enter block size: ");
            int size = scanner.nextInt();

            if (!tower.addBlock(new Block(size))) {
                System.out.println("Tower fell! Game over.");
                break;
            }
        }
    }
}
