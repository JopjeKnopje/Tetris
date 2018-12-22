package tetris.graphics;


import java.util.Random;

public class Screen {

    private static final int TILE_SIZE_BIT = 5; // gets bitshifted

    private int width, height;
    public int[] pixels;


    private int[] tiles = new int[64 * 64];

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = random.nextInt(0xffffff);

        }

    }



    public void render() {

        for (int y = 0; y < height; y++) {
            if(y >= height || y < 0) break; // if pixel is out of screen do not render that pixel

            for (int x = 0; x < width; x++) {
                if(x >= width || x < 0) break; // if pixel is out of screen do not render that pixel


                // if x = 1, 1 / TILE_SIZE_BIT(32) = 0.03125 but it gets rounded up to 1 same to y.
                int tileIndex = (x >> TILE_SIZE_BIT) + (y >> TILE_SIZE_BIT) * 64;
                pixels[x + y * width] = tiles[tileIndex];

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