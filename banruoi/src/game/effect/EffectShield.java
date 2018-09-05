package game.effect;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import physic.BoxCollider;
import physic.PhysicBody;
import renderer.ImageRenderer;
import utils.Utils;

import javax.sound.sampled.Clip;
import java.util.Random;

public class EffectShield extends GameObject implements PhysicBody {
    public BoxCollider boxCollider;
    public Vector2D velocity;
    private Random random = new Random();
    private Clip clip;

    public EffectShield() {
        this.boxCollider = new BoxCollider(30, 30);
        this.renderer = new ImageRenderer("resources/images/Love-Heart-13.png", 30, 30);
        this.velocity = new Vector2D(0.2f, 0.5f);
        this.position.set(random.nextInt(1024), random.nextInt(600));
        this.clip = Utils.loadAudio("resources/audio/powerup.wav");
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 15, this.position.y - 15);
        GameObjectManager.instance.objectExitDisplay(this);

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        if (gameObject instanceof Player)
            this.clip.loop(1);
        this.clip.start();
        this.isAlive = false;
        Player.life += 1;
    }
}
