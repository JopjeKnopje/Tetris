package tetris;

import tetris.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {

    // Dimensions
    private static final int CELL_SIZE = 30;
    private static final int HEIGHT = CELL_SIZE * 20;
    private static final int WIDTH = CELL_SIZE * 11;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;

    // Image to draw thing on
    private BufferedImage image = new BufferedImage(HEIGHT, WIDTH, BufferedImage.TYPE_INT_RGB);
    // pixels from the image
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();



    public Game() {
        // Set dimension of Canvas (The game class)
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);

        screen = new Screen(WIDTH, HEIGHT);

        frame = new JFrame();

    }



    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }



    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Override
    // Game loop
    public void run() {
        while (running) {
            tick();
            render();
        }
    }



    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.render();

        // Copy rendered pixels to current pixel array
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), getHeight());


//        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }



    public void tick() {

    }



    public static void main(String args[]) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Tetris");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();

    }

}
