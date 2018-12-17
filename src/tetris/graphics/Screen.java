package tetris.graphics;


public class Screen {

    private int width, height;
    public int[] pixels;
    private int c;
    private int counter;
    private int xTime;
    private int yTime;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        yTime = 50;
    }



    public void render() {
        counter++;
        if(counter % 25 == 0) yTime++;
        if(counter % 10 == 0) xTime++;

        for (int y = 0; y < height; y++) {
            if(yTime >= height || yTime < 0) break; // if pixel is out of screen do not render that pixel

            for (int x = 0; x < width; x++) {
                if(xTime >= width || xTime < 0) break; // if pixel is out of screen do not render that pixel

                pixels[xTime + yTime * width] = Color.CYAN.getRGB();

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