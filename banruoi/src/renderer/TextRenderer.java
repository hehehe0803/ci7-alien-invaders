package renderer;

import base.GameObject;
import base.Vector2D;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TextRenderer implements Renderer {
    public String text;
    private Font font;
    private Color color;

//    public TextRenderer(String text, Color color, String fontName, int fontSize) {
//        this.text = text;
//        this.color = color;
//        this.font = new Font(fontName, Font.PLAIN, fontSize);
//    }

    public TextRenderer(String text, Color color, String path, int fontSize) {
        this.text = text;
        this.color = color;
        try {
            this.font = Font.createFont(Font.PLAIN, new File(path));
            this.font = this.font.deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics graphics, Vector2D position) {
        graphics.setColor(this.color);
        graphics.setFont(this.font);
        graphics.drawString(this.text, (int) position.x, (int) position.y);
    }
}
