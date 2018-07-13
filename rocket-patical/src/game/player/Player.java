package game.player;

import base.GameObject;
import base.Vector2D;
import game.effect.CreatParticle;
import game.effect.EffectShield;
import game.effect.EffectTripShoot;
import game.enemy.BulletEnemy;
import game.enemy.Enemy;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.PolygonRenderer;

import java.awt.*;

public class Player extends GameObject implements PhysicBody {
    public BoxCollider boxCollider;
    public Vector2D velocity;

    public RunHitObject runHitObject;
    public CreatParticle creatParticle;

    public Player() {
        this.renderer = new PolygonRenderer(
                Color.RED,
                new Vector2D(8,0),
                new Vector2D(0, 20),
                new Vector2D(16, 20)
        );
        this.velocity = new Vector2D(3.5f, 0);
        this.attributes.add(new PlayerShoot());
        this.attributes.add(new PlayerMove());
        this.boxCollider = new BoxCollider(20, 16);
        this.runHitObject = new RunHitObject(Enemy.class, EffectShield.class, EffectTripShoot.class);
        this.creatParticle = new CreatParticle();
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 8);
        this.runHitObject.run(this);

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        if (gameObject instanceof Enemy) {
            this.isAlive = false;
            this.creatParticle.run(this);
        }
    }
}
