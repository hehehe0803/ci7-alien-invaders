package game.player;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.enemy.Boss;
import game.enemy.BulletBoss;
import game.enemy.BulletEnemy;
import game.enemy.Enemy;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;

import java.awt.*;

public class BulletPlayer extends GameObject implements PhysicBody {

    public Vector2D velocity;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;

    public BulletPlayer() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/bullet.png", 24, 24);
        this.boxCollider = new BoxCollider(24, 24);

        this.runHitObject = new RunHitObject(
                Enemy.class,
                BulletBoss.class,
                Boss.class
        );
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 12, this.position.y - 12);
        this.runHitObject.run(this);
        GameObjectManager.instance.objectExitDisplay(this);
    }

    @Override
    public void getHit(GameObject gameObject) {
        if (gameObject instanceof Enemy || gameObject instanceof BulletBoss || gameObject instanceof Boss)
            this.isAlive = false;

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
