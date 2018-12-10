package tetris;

import blocks.Block;
import blocks.BlockI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO Create block container
 */
public class Window extends JFrame {

    private static final int CELL_SIZE = 30;
    private static final int HEIGHT = CELL_SIZE * 20;
    private static final int WIDTH = CELL_SIZE * 11;
    private static final int TIMER_DELAY = 1000 / 16;
    private final Timer gameLoopTimer;

    Block b = new BlockI();

    public Window() {
        super("Thotras"); // Instantiate JFrame
        super.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // JFrame size mate

        JPanel panel = new JPanel();
//        panel.setSize(new Dimension(WIDTH, HEIGHT)); kanker op lucas <3

        // Add panel to JFrame
        add(panel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // Updates the graphics
        ActionListener gameTimer = new ActionListener() { // Fuck lambdas
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("tick");
                repaint();
            }
        };
        gameLoopTimer = new Timer(TIMER_DELAY, gameTimer);
        gameLoopTimer.start();

    }


    public void paint(Graphics g) {
        clear(g);
        g.setColor(Color.orange);
        g.drawRect(b.getX(), b.getY(), CELL_SIZE, CELL_SIZE * 4); //TODO move the dimensions to the block class itself
        b.moveDown();
    }

    public void clear(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, WIDTH, HEIGHT);

    }


    // Getters, Setters

    public static int getCellSize() {
        return CELL_SIZE;
    }

    public static int krijgHoogte() {
        return HEIGHT;
    }


    public static int krijgBreedte() {
        return WIDTH;
    }
}
