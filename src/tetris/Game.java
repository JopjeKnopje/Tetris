package tetris;

import tetris.graphics.Screen;
import tetris.io.KeyBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {


    private final int TARGET_FPS = 60;
    private final double TARGET_NS = 1000000000.0 / TARGET_FPS;

    // Dimensions
    private static final int TILE_SIZE = 32;
    private static final int HEIGHT = TILE_SIZE * 20;
    private static final int WIDTH = TILE_SIZE * 10;


    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;

    // Image to draw stuff on
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    // to access the pixels from the image
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();


    private Game() {
        // Set dimension of Canvas (The game class)
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);

        addKeyListener(new KeyBoard());

        screen = new Screen(WIDTH, HEIGHT);
        frame = new JFrame();

    }



    private synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }



    private synchronized void stop() {
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



    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        screen.render();


        // Copy rendered (Screen) pixels to current pixel array.
        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);


//        for (int i = 0; i < pixels.length; i++) {
//            pixels[i] = screen.pixels[i];
//        }


        Graphics g = bs.getDrawGraphics(); // Get graphics object to draw to.

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // If you draw the image with a higher width/height it will stretch. just a tippie.

        g.dispose();
        bs.show();
    }



    private void tick() {

    }



    private static void main(String args[]) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Tetris");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }

}
