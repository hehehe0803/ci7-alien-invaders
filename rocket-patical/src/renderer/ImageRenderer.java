package renderer;

import base.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRenderer implements Renderer {

    private BufferedImage image;
    public int width;
    public int height;

    public ImageRenderer(String path, int width, int height) {
        this.image = this.loadImage(path);
        this.width = width;
        this.height = height;
    }

    public ImageRenderer(String path, int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.image = this.loadImage(path);
        for (int i = 0; i < this.image.getWidth(); i++) {
            for (int j = 0; j < this.image.getHeight(); j++) {
                if (this.image.getRGB(i, j) == Color.WHITE.getRGB()) {
                    this.image.setRGB(i, j, color.getRGB());
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics, Vector2D position) {
        graphics.drawImage(this.image, (int) position.x - this.width / 2, (int) position.y - this.height / 2, this.width, this.height, null);
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
