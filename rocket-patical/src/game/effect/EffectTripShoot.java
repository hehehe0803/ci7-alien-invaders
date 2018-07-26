package game.effect;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import physic.BoxCollider;
import physic.PhysicBody;
import renderer.ImageRenderer;

import java.util.Random;

public class EffectTripShoot extends GameObject implements PhysicBody {

    public BoxCollider boxCollider;
    private Random random = new Random();
    private FrameCounter frameCounter;
    public Vector2D velocity;
    public static boolean chonceTripShoot = false;
    public static FrameCounter timeLifeTripShoot = new FrameCounter(20);

    public EffectTripShoot() {
        this.boxCollider = new BoxCollider(30, 30);
        this.renderer = new ImageRenderer("resources/images/tripshoot.png", 40, 40);
        this.position.set(random.nextInt(1024), random.nextInt(600));
        this.velocity = new Vector2D(0.2f,0.3f);
        this.frameCounter = new FrameCounter(100);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 20, this.position.y - 20);
        GameObjectManager.instance.objectExitDisplay(this);

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        if (gameObject instanceof Player) {
            this.isAlive = false;
            this.chonceTripShoot = true;
            EffectTripShoot.timeLifeTripShoot = new FrameCounter(20);
        }
    }
}
