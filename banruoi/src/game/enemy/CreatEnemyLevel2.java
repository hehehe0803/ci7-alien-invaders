package game.enemy;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import renderer.ImageRenderer;
import scene.GamePlayLevel2Scene;
import scene.SceneManager;
import scene.scene.level.SceneLevel2;
import scene.scene.level.SceneLevel3;

import java.util.Random;

public class CreatEnemyLevel2 extends GameObject {
    private FrameCounter frameCounter;
    private Random random;
    int rows, cols;
    public static int numFlyLevel2 = 80;

    private static final Vector2D velocitySubX = new Vector2D(-0.3f, 0.0f);
    private static final Vector2D velocityAddX = new Vector2D(0.3f, 0.0f);
    private static final Vector2D velocityAddY = new Vector2D(0.5f, 0.3f);
    private static final Vector2D velocitySubY = new Vector2D(-0.5f, 0.3f);

    public CreatEnemyLevel2() {
        this.frameCounter = new FrameCounter(500);
        this.random = new Random();
        this.rows = 4;
        this.cols = 20;
        this.creat();
    }

    public void creat() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Enemy enemy = GameObjectManager.instance.recycle(Enemy.class);
                enemy.position.set(125 + col * (((ImageRenderer) enemy.renderer).width + 20), ((ImageRenderer) enemy.renderer).height + row * 50);
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
        if (CreatEnemyLevel2.numFlyLevel2 == 0){
            CreatEnemyLevel3.numFlyLevel3 = 80;
            SceneManager.instance.changeScene(new SceneLevel3());

        }

        if (this.frameCounter.run()){
            Player player = GameObjectManager.instance.findPlayer();
            Enemy enemy1 = GameObjectManager.instance.findEnemyTail();
            if (Player.life > 0){
                Vector2D velocity = player.position
                        .subtract(enemy1.position)
                        .normalize()
                        .multiply(1.5f);
                enemy1.velocity.set(velocity);
            }
           // this.frameCounter.reset();
        }
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
