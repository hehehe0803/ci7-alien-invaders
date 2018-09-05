package renderer;

import base.GameObject;

import java.awt.*;

public class Text extends GameObject {
    public Text(){
        this.renderer = new TextRenderer(
                "press enter to start",
                Color.magenta,
                "resources/FiraMono-Bold.ttf",
                30
        );
    }
}
