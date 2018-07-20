package game.effect;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import renderer.ImageRenderer;
import utils.Utils;

import javax.sound.sampled.Clip;

public class CreatParticle {

    public CreatParticle() {
    }

    public void run(GameObject gameObject) {
        if (gameObject.isAlive == false) {
            for (double angle = 0.0; angle < 360.0; angle += 360.0 / 12.0) {
                Particle particle = GameObjectManager.instance.recycle(Particle.class);
                particle.renderer = new ImageRenderer("resources/images/Blue-Star-3.png", 12, 12);
                particle.position.set(gameObject.position);
                particle.velocity.set(new Vector2D(3.5f, 0.0f).rotate(angle));
            }
        }
    }
}
