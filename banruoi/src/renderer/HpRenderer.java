package renderer;

import base.Vector2D;

import java.awt.*;

public class HpRenderer implements Renderer {
    public int width;
    public int height;
    public Color color;

    public HpRenderer(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void render(Graphics graphics, Vector2D position) {
        graphics.setColor(this.color);
        graphics.fillRect((int) position.x, (int) position.y, this.width, this.height);
        graphics.setColor(Color.red);
        graphics.drawRect((int) position.x, (int) position.y, 100, 15);
    }
}
