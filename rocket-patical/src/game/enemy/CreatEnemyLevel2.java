package game.enemy;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import renderer.ImageRenderer;

import java.util.Random;

public class CreatEnemyLevel2 extends GameObject {
    private FrameCounter frameCounter;
    private Random random;
    int rows, cols;

    private static final Vector2D velocitySubX = new Vector2D(-0.2f, 0.0f);
    private static final Vector2D velocityAddX = new Vector2D(0.2f, 0.0f);
    private static final Vector2D velocityAddY = new Vector2D(0.5f, 0.2f);
    private static final Vector2D velocitySubY = new Vector2D(-0.5f, 0.2f);

    public CreatEnemyLevel2() {
        this.frameCounter = new FrameCounter(900);
        this.random = new Random();
        this.rows = 4;
        this.cols = 20;
        this.creat();
    }

    public void creat() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Enemy enemy = GameObjectManager.instance.recycle(Enemy.class);
                enemy.position.set(100 + col * (((ImageRenderer) enemy.renderer).width + 20), ((ImageRenderer) enemy.renderer).height + row * 50);
                enemy.genitiveRow = row;
                if (row == 0 || row == 2) {
                    enemy.velocity.set(velocitySubX);
                } else {
                    enemy.velocity.set(velocityAddX);
                }
            }
        }
    }

    @Override
    public void run() {
        super.run();
        this.move(0);
        this.move(1);
        this.move(2);
        this.move(3);

    }

    public void move(int genitiveRow) {
        Enemy enemy = GameObjectManager.instance.findEnemyFrist(genitiveRow);
        Enemy enemyAny = GameObjectManager.instance.findEnemyTail(genitiveRow);
        if (genitiveRow == 0 || genitiveRow == 2) {
            if (enemy != null) {
                if (enemy.position.x - 20 < 0) {
                    GameObjectManager.instance.setVelocityEnemy(velocityAddY, genitiveRow);
                }
            }
            if (enemyAny != null) {
                if (enemyAny.position.x + 30 > 1024) {
                    GameObjectManager.instance.setVelocityEnemy(velocitySubY, genitiveRow);
                }
            }
        }

        if (genitiveRow == 1 || genitiveRow == 3) {
            if (enemy != null) {
                if (enemy.position.x - 20 < 0) {
                    GameObjectManager.instance.setVelocityEnemy(velocityAddY, genitiveRow);
                }
            }
            if (enemyAny != null) {
                if (enemyAny.position.x + 30 > 1024) {
                    GameObjectManager.instance.setVelocityEnemy(velocitySubY, genitiveRow);
                }
            }
        }
    }
}