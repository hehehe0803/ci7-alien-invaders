package game.score;

import base.GameObject;
import base.Vector2D;
import renderer.TextRenderer;

import java.awt.*;

public class Score extends GameObject {
    public static Score instance = new Score();
    public int score;

    private Score() {
        this.score = 0;
        this.renderer = new TextRenderer(
                "Score: " + this.score,
                Color.red,
                "resources/FiraMono-Bold.ttf",
                25
        );
        this.position = new Vector2D(50,50);
    }
    @Override
    public void run(){
        super.run();
        ((TextRenderer)renderer).text = "Score: " + this.score;
    }
}
