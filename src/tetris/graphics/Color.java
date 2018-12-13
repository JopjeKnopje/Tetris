package tetris.graphics;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.util.Random;

public class Color extends java.awt.Color {


    public Color(int r, int g, int b) {
        super(r, g, b);
    }

    public Color(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public Color(int rgb) {
        super(rgb);
    }

    public Color(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
    }

    public Color(float r, float g, float b) {
        super(r, g, b);
    }

    public Color(float r, float g, float b, float a) {
        super(r, g, b, a);
    }

    public Color(ColorSpace cspace, float[] components, float alpha) {
        super(cspace, components, alpha);
    }

    // Returns a random rbg
    public static int randomInt() {
        Random rand = new Random();
        return rand.nextInt(16581375);
    }




}
