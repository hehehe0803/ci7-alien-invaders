package effect;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.enemy.BulletEnemy;
import game.enemy.Enemy;
import game.player.Player;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;

import java.awt.*;

public class Shield extends GameObject implements PhysicBody {
    public Vector2D velocity;
    public BoxCollider boxCollider;
   public RunHitObject runHitObject;
    private int alive;

    public Shield() {
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(50, 50);
        this.alive = 3;
        this.runHitObject = new RunHitObject(Enemy.class,BulletEnemy.class);
    }

    @Override
    public void run() {
        super.run();
        Player player = GameObjectManager.instance.findPlayer();
        if (player != null) {
            this.position.set(player.position);
        }
        this.boxCollider.position.set(this.position.x - 25, this.position.y - 25);
        this.runHitObject.run(this);

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        if (gameObject instanceof Enemy || gameObject instanceof BulletEnemy)
            this.alive--;
        if (this.alive <= 0) {
            this.isAlive = false;
        }
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        if (this.isAlive) {
            graphics.setColor(Color.BLUE);
            graphics.drawOval((int) this.position.x - 25, (int) this.position.y - 25, 50, 50);
        }
    }
}
