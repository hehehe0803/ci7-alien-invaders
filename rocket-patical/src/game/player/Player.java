package game.player;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.effect.CreatParticle;
import game.effect.CreatParticlePlay;
import game.effect.EffectShield;
import game.effect.EffectTripShoot;
import game.enemy.Boss;
import game.enemy.BulletBoss;
import game.enemy.BulletEnemy;
import game.enemy.Enemy;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.PolygonRenderer;
import scene.OverScene;
import scene.SceneManager;
import scene.scene.level.SceneLevel3;

import java.awt.*;

public class Player extends GameObject implements PhysicBody {
    public BoxCollider boxCollider;
    public Vector2D velocity;

    public RunHitObject runHitObject;
    public CreatParticle creatParticle;
    public CreatParticlePlay creatParticlePlay;

    public PlayerHp playerHp;
    public static int life;

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
        this.runHitObject = new RunHitObject(Enemy.class,
                EffectShield.class,
                EffectTripShoot.class,
                Boss.class,
                BulletBoss.class,
                BulletEnemy.class
                );
        this.creatParticle = new CreatParticle();
        this.creatParticlePlay = new CreatParticlePlay();
        this.playerHp = GameObjectManager.instance.recycle(PlayerHp.class);
        Player.life = 5;
    }

    @Override
    public void run() {
        super.run();
        playerHp.count = Player.life;
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 8);
        this.runHitObject.run(this);
        this.creatParticlePlay.run(this);
        if (Player.life > 10){
            Player.life = 10;
        }
        if (Player.life <= 0){
            SceneManager.instance.changeScene(new OverScene());
        }

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        if (gameObject instanceof Enemy || gameObject instanceof BulletEnemy || gameObject instanceof Boss || gameObject instanceof BulletBoss) {
            Player.life -= 1;
            if (Player.life <= 0){
                this.isAlive = false;
                this.creatParticle.run(this);
                SceneManager.instance.changeScene(new OverScene());
            }

        }
    }
}
