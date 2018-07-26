package game.enemy;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.effect.CreatParticle;
import game.effect.EffectTripShoot;
import game.player.BulletPlayer;
import game.player.Player;
import game.score.Score;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import scene.SceneManager;
import scene.WinScene;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.Random;

public class Boss extends GameObject implements PhysicBody {
    public Vector2D velocity;
    public BoxCollider boxCollider;
    private CreatParticle creatParticle;
    public RunHitObject runHitObject;
    private Random random;

    public FrameCounter frameCounter;
    public BossHp bossHp;
    public int life;
    public static int maxBulletFollow;

    private Clip clip;

    public Boss() {
        this.position = new Vector2D(500.0f, 50.0f);
        this.velocity = new Vector2D(2.0f, 0.0f);
        this.renderer = new ImageRenderer("resources/images/boss.png", 70, 70);
        this.boxCollider = new BoxCollider(70, 70);
        this.creatParticle = new CreatParticle();
        this.runHitObject = new RunHitObject(Player.class, BulletPlayer.class);
        this.frameCounter = new FrameCounter(50);
//        this.clip = Utils.loadAudio("resources/audio/Boom.wav");
        this.random = new Random();
        this.bossHp = GameObjectManager.instance.recycle(BossHp.class);
        this.life = 100;
        Boss.maxBulletFollow = 50;
    }

    @Override
    public void run() {
        super.run();
        this.creatBullet();
        this.bossHp.hp = this.life;
        if (this.life > 0 && this.life < 20) {
            Player player = GameObjectManager.instance.findPlayer();
            GameObjectManager.instance.list
                    .stream()
                    .filter(gameObject -> gameObject instanceof BulletBoss)
                    .filter(gameObject -> gameObject.isAlive)
                    .forEach(gameObject -> {
                        Vector2D velocity = player.position
                                .subtract(gameObject.position)
                                .normalize()
                                .multiply(3.0f);
                        ((BulletBoss) gameObject).velocity.set(velocity);
                    });
        }
        this.position.addUp(this.velocity);
        this.move();
        this.boxCollider.position.set(this.position.x - 35, this.position.y - 35);
        this.runHitObject.run(this);
        GameObjectManager.instance.checkGameOver();
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
    }

    @Override
    public void getHit(GameObject gameObject) {
        if (gameObject instanceof Player || gameObject instanceof BulletPlayer) {
            Score.instance.score += 10;
            this.life -= 1;
            if (EffectTripShoot.chonceTripShoot) {
                this.life -= 1;
            }
            if (this.life <= 0) {
                this.isAlive = false;
                this.creatParticle.run(this);
                // this.clip.loop(1);
                //  this.clip.start();
                Score.instance.score += 100;
                SceneManager.instance.changeScene(new WinScene());
            }
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public void move() {
        if (this.position.x - 70 <= 0) {
            this.velocity.set(0.5f, 0.0f);
        }
        if (this.position.x + 70 >= 1024) {
            this.velocity.set(-0.5f, 0.0f);
        }
    }

    public void creatBullet() {
        if (this.frameCounter.run()) {
            if (this.life >= 50) {
                BulletBoss bulletBoss = GameObjectManager.instance.recycle(BulletBoss.class);
                bulletBoss.position.set(this.position.x + 10, this.position.y + 25);
                bulletBoss.velocity.set(0.0f, 2.0f);
            }
            if (20 <= this.life && this.life < 50) {
                for (double angle = 0; angle < 31; angle += 15) {
                    BulletBoss bulletBoss = GameObjectManager.instance.recycle(BulletBoss.class);
                    bulletBoss.position.set(this.position.x + 10, this.position.y + 30);
                    bulletBoss.velocity.set(new Vector2D(2.0f, 0.0f).rotate(75 + angle));
                }

            }
            if (this.life > 0 && this.life < 20) {
                if (Boss.maxBulletFollow >= 0) {
                    BulletBoss bulletBoss = GameObjectManager.instance.recycle(BulletBoss.class);
                    Player player = GameObjectManager.instance.findPlayer();
                    bulletBoss.position.set(this.position.x + 10, this.position.y + 30);
                    Vector2D velocity = player.position
                            .subtract(bulletBoss.position)
                            .normalize()
                            .multiply(3.0f);
                    bulletBoss.velocity.set(velocity);
                    Boss.maxBulletFollow -= 1;
                }
            }

            this.frameCounter.reset();
        }
    }
}
