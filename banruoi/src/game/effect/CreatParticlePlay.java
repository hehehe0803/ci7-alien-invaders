package game.effect;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import renderer.ImageRenderer;

import java.awt.*;

public class CreatParticlePlay extends GameObject {

    private FrameCounter frameCounter ;


    public CreatParticlePlay(){
        this.frameCounter = new FrameCounter(10);
    }

    public void run(Player player) {
        if (this.frameCounter.run()) {
            Particle particle = GameObjectManager.instance.recycle(Particle.class);
            particle.renderer = new ImageRenderer("resources/images/circle.png", 10, 10, Color.CYAN);
            particle.position.set(player.position);
            particle.velocity.set(new Vector2D(0.0f,2.0f));
            this.frameCounter.reset();
        }
    }
}
