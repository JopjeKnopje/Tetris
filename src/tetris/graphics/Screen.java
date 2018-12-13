package tetris.graphics;


import javafx.scene.control.Cell;
import jdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;

public class Screen {

    private int width, height;
    public int[] pixels;
    private int c;
    private int counter;
    private int time;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }



    public void render() {
        counter++;
        if(counter % 8 == 0) time++;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if ((time + time * width) > pixels.length) time--;
                if ((time + time * width) < 1) time++;

                pixels[time + time * width] = Color.CYAN.getRGB();

            }
        }
    }



    public void clear() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = Color.BLACK.getRGB();
            }
        }


    }



}