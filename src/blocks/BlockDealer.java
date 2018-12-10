package blocks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Logger;

/**
 * There are 2 blocks in the queue at any given time.
 * TODO Implement this class in the game loop
 */
public class BlockDealer {

    Queue<Block> blockQueue;
    Random random;


    // setup queue
    public BlockDealer() {
        blockQueue = new LinkedList<>();

    }

    // gets called every time a block has been "used"
    public void genBlocks() {
        blockQueue.add(getBlock(random.nextInt((6 - 0) + 1)));
    }

    private Block getBlock(int index) { //throws invailidBlockException
        switch (index) {
            case 0: return new BlockI();
            case 1: return new BlockO();
            case 2: return new BlockT();
            case 3: return new BlockS();
            case 4: return new BlockZ();
            case 5: return new BlockJ();
            case 6: return new BlockL();
        }
        //Log error
        return new BlockI(); //TODO Create invailidBlockException

    }
}
