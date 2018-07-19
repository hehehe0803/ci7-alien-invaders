package game.enemy;

import base.GameObject;
import base.Vector2D;
import game.effect.CreatParticle;
import physic.BoxCollider;
import physic.PhysicBody;
import renderer.ImageRenderer;

import java.awt.*;

public class Enemy extends GameObject implements PhysicBody {

    public Vector2D velocity;
    public BoxCollider boxCollider;
    private CreatParticle creatParticle;
    public int genitiveRow;

    public Enemy() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/virus_PNG4.png", 20, 20, Color.magenta);
        this.boxCollider = new BoxCollider(20, 20);
        this.creatParticle = new CreatParticle();


    }
    public Enemy set(Enemy enemy){
        this.position = enemy.position;
//        this.velocity = enemy.velocity;
//        this.renderer = enemy.renderer;
//        this.boxCollider = enemy.boxCollider;
//        this.creatParticle = enemy.creatParticle;
        return this;
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.isAlive = false;
        this.creatParticle.run(this);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
