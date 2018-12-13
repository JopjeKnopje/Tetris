package tetris.graphics;



public class Screen {

    private int width, height;
    public int[] pixels;
    private int counter;
    private int c;
    private int time;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        counter = 0;


    }

    public void render() {
        counter++;
        if (counter % 2 == 0) time++;
        if (time > width) time = 0;

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