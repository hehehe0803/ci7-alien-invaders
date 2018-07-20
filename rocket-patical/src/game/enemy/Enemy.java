package game.enemy;

import base.GameObject;
import base.Vector2D;
import game.effect.CreatParticle;
import game.player.BulletPlayer;
import game.player.Player;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import utils.Utils;

import javax.sound.sampled.Clip;
import java.awt.*;

public class Enemy extends GameObject implements PhysicBody {

    public Vector2D velocity;
    public BoxCollider boxCollider;
    private CreatParticle creatParticle;
    public int genitiveRow;
    public RunHitObject runHitObject;

    private Clip clip;

    public Enemy() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/virus_PNG4.png", 20, 20, Color.magenta);
        this.boxCollider = new BoxCollider(20, 20);
        this.creatParticle = new CreatParticle();
        this.runHitObject = new RunHitObject(Player.class, BulletPlayer.class);
        this.clip = Utils.loadAudio("resources/audio/Boom.wav");
    }

    public Enemy set(Enemy enemy) {
        this.position = enemy.position;
        return this;
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);
        this.runHitObject.run(this);
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
    }

    @Override
    public void getHit(GameObject gameObject) {
        if (gameObject instanceof Player || gameObject instanceof BulletPlayer) {
            this.isAlive = false;
            this.creatParticle.run(this);
            this.clip.loop(1);
            this.clip.start();
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
