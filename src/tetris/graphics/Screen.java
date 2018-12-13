package tetris.graphics;



public class Screen {

    private int width, height;
    public int[] pixels;
    private int c;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];


    }

    public void render() {

        c = Color.randomInt();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = c;

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