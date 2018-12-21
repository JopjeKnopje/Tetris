package tetris;

import tetris.graphics.Screen;
import tetris.io.KeyBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {


    private final double TARGET_NS = 1000000000.0 / 60;



    // Dimensions
    private static final int CELL_SIZE = 30;
    private static final int HEIGHT = CELL_SIZE * 20;
    private static final int WIDTH = CELL_SIZE * 11;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;

    // Image to draw stuff on
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    // to access the pixels from the image
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();


    public Game() {
        // Set dimension of Canvas (The game class)
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);

        addKeyListener(new KeyBoard());

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
    // Gets called when Thread.start();
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;

        int frames = 0;
        int updates = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / TARGET_NS;
            lastTime = now;

            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle("fps: " + frames + ", ups: " + updates);
                System.out.println("fps: " + frames + ", ups: " + updates);
                frames = 0;
                updates = 0;
            }

        }
        stop();
    }



    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        screen.render();

        // Copy rendered (Screen) pixels to current pixel array.
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics(); // Get graphics object to draw to.

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // If you draw the image with a higher width/height it will stretch. just a tippie.

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
