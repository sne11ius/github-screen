package nu.wasis.github.screen.model;

import java.util.HashSet;
import java.util.Set;

public class Screen {

    private final Set<Pixel> pixels = new HashSet<>();

    public Screen() {
    }

    @Override
    public String toString() {
        return "Screen [pixels=" + pixels + "]";
    }

    public void addPixel(final Pixel pixel) {
        pixels.add(pixel);
    }

}
