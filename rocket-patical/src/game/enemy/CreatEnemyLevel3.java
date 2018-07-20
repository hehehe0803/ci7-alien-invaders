package game.enemy;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import renderer.ImageRenderer;

import java.util.Random;

public class CreatEnemyLevel3 extends GameObject {

    private FrameCounter frameCounter;
    private Random random;
    int rows, cols;
    private int countRow0 = 500;
    private int countRow01 = 500;

    private int countRow1 = 500;
    private int countRow11 = 500;

    private static final Vector2D velocitySubX = new Vector2D(-0.3f, 0.0f);
    private static final Vector2D velocityAddX = new Vector2D(0.3f, 0.0f);
    private static final Vector2D velocityAddY = new Vector2D(0.0f, 0.3f);
    private static final Vector2D velocityBulletEnemy = new Vector2D(0.0f, 1.5f);

    public CreatEnemyLevel3() {
        this.frameCounter = new FrameCounter(200);
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
        this.creatBullet();
        this.move(0);
        this.move(1);
        this.move(2);
        this.move(3);

    }

    public void creatBullet(){
        if (this.frameCounter.run()){
            GameObjectManager.instance.list
                    .stream()
                    .filter(gameObject -> gameObject.isAlive)
                    .filter(gameObject -> gameObject instanceof Enemy)
                    .forEach(gameObject ->{
                        if (((Enemy) gameObject).genitiveRow == 0) {
                            BulletEnemy bulletEnemy = GameObjectManager.instance.recycle(BulletEnemy.class);
                            bulletEnemy.position.set(gameObject.position);
                            bulletEnemy.velocity.set(velocityBulletEnemy);
                        }
                    });
            this.frameCounter.reset();
        }

    }

    public void move(int genitiveRow) {
        Enemy enemy = GameObjectManager.instance.findEnemyFrist(genitiveRow);
        Enemy enemyAny = GameObjectManager.instance.findEnemyTail(genitiveRow);
        if (genitiveRow == 0 || genitiveRow == 2) {
            if (enemy != null) {
                if (enemy.position.x - 20 < 0 && countRow0 > 0) {
                    GameObjectManager.instance.setVelocityEnemy(velocityAddY, genitiveRow);
                    countRow0 -= 1;
                    countRow01 = 500;
                }
                if (countRow0 == 0) {
                    GameObjectManager.instance.setVelocityEnemy(velocityAddX, genitiveRow);
                }
            }
            if (enemyAny != null) {
                if (enemyAny.position.x + 30 > 1024 && countRow01 > 0) {
                    GameObjectManager.instance.setVelocityEnemy(velocityAddY, genitiveRow);
                    countRow01 -= 1;
                    countRow0 = 500;
                }
                if (countRow01 == 0) {
                    GameObjectManager.instance.setVelocityEnemy(velocitySubX, genitiveRow);
                }
            }
        }

        if (genitiveRow == 1 || genitiveRow == 3) {
            if (enemy != null) {
                if (enemy.position.x - 20 < 0 && countRow1 > 0) {
                    GameObjectManager.instance.setVelocityEnemy(velocityAddY, genitiveRow);
                    countRow1 -= 1;
                    countRow11 = 500;
                }
                if (countRow1 == 0) {
                    GameObjectManager.instance.setVelocityEnemy(velocityAddX, genitiveRow);
                }
            }
            if (enemyAny != null) {
                if (enemyAny.position.x + 30 > 1024 && countRow11 > 0) {
                    GameObjectManager.instance.setVelocityEnemy(velocityAddY, genitiveRow);
                    countRow11 -= 1;
                    countRow1 = 500;
                }
                if (countRow11 == 0) {
                    GameObjectManager.instance.setVelocityEnemy(velocitySubX, genitiveRow);
                }
            }
        }
    }

}
