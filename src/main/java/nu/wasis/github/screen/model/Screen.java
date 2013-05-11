package nu.wasis.github.screen.model;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class Screen {
    private static final Logger LOG = Logger.getLogger(Screen.class);

    public static final int MAX_WIDTH = 51; // 52 - 1 weeks / year
    public static final int MAX_HEIGHT = 7; // days / week

    private final Color[][] pixels = new Color[MAX_WIDTH][MAX_HEIGHT];

    public Screen() {
    }

    public Screen(final String filename) throws IOException {
        loadFile(filename);
    }

    private void loadFile(final String filename) throws IOException {
        final File imageFile = new File(filename);
        final BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        final BufferedImage image = op.filter(ImageIO.read(imageFile), null);

        if (image.getWidth() > Screen.MAX_WIDTH) {
            LOG.warn("Image width to large (will be cut): " + image.getWidth());
        }
        if (image.getHeight() > Screen.MAX_HEIGHT) {
            LOG.warn("Image height to large (will be cut): " + image.getHeight());
        }

        for (int x = 0; x < image.getWidth() && x < Screen.MAX_WIDTH; ++x) {
            for (int y = 0; y < image.getHeight() && y < Screen.MAX_HEIGHT; ++y) {
                final int colorValue = image.getRGB(x, y);
                setPixel(x, y, new Color(colorValue));
            }
        }
    }

    public void setPixel(final int x, final int y, final Color color) {
        checkCoords(x, y);
        pixels[x][y] = color;
    }

    public Color getPixel(final int x, final int y) {
        checkCoords(x, y);
        return pixels[x][y];
    }

    private void checkCoords(final int x, final int y) {
        if (x >= MAX_WIDTH) {
            throw new IllegalArgumentException("x must be < " + MAX_WIDTH);
        }
        if (y >= MAX_HEIGHT) {
            throw new IllegalArgumentException("y must be < " + MAX_HEIGHT);
        }
    }

}
