import java.util.Stack;

public class Tower {
    private final Stack<Block> blocks;

    public Tower(){
        blocks = new Stack<>();
    }

    public boolean addBlock(Block block){
        if (!blocks.isEmpty() && block.getSize() > blocks.peek().getSize()) {
            return false;
        }

        blocks.push(block);
        return true;
    }
}
