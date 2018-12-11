package tetris.graphics;

import java.util.Random;

public class Screen {

    private int width, height;
    public int[] pixels;
    private int nextInt;
    private Random random;
    private String hex;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = 0x1f12ff;

            }

        }

    }


    public void clear() {

    }

    private int randomColor() {
        nextInt = random.nextInt(256*256*256);
        hex = String.format("0x%06x", nextInt);
        System.out.println(hex);
//        return hex;
        return 0;
    }


}