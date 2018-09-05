package base;

import game.enemy.Enemy;
import game.player.BulletPlayer;
import game.player.Player;
import physic.BoxCollider;
import physic.PhysicBody;
import scene.OverScene;
import scene.SceneManager;

import java.awt.*;
import java.util.ArrayList;

public class GameObjectManager {

    public static GameObjectManager instance = new GameObjectManager();

    public ArrayList<GameObject> list;
    public ArrayList<GameObject> tempList;

    public Enemy enemyTail = new Enemy();

    private GameObjectManager() {
        this.list = new ArrayList<>();
        this.tempList = new ArrayList<>();

    }

    public void add(GameObject gameObject) {
        this.tempList.add(gameObject);
    }

    public void runAll() {
        this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.run());
        this.list.addAll(this.tempList);
        this.tempList.clear();
    }

    public void renderAll(Graphics graphics) {
        this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.render(graphics));
    }

    public Player findPlayer() {
        return (Player) this.list
                .stream()
                .filter(gameObject -> gameObject instanceof Player)
                .filter(gameObject -> gameObject.isAlive)
                .findFirst()
                .orElse(null);
    }

    public Enemy findEnemyFrist(int genitiveRow) {
        return (Enemy) GameObjectManager.instance.list
                .stream()
                .filter(gameObject -> gameObject instanceof Enemy)
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> ((Enemy) gameObject).genitiveRow == genitiveRow)
                .findFirst()
                .orElse(null);
    }

    public Enemy findEnemyTail(int genitiveRow) {
        GameObjectManager.instance.list
                .stream()
                .filter(gameObject -> gameObject instanceof Enemy)
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> ((Enemy) gameObject).genitiveRow == genitiveRow)
                .forEach(gameObject -> this.enemyTail = (Enemy) gameObject);
        return this.enemyTail;
    }

    public Enemy findEnemyTail() {
        GameObjectManager.instance.list
                .stream()
                .filter(gameObject -> gameObject instanceof Enemy)
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> this.enemyTail = (Enemy) gameObject);
        return this.enemyTail;
    }

    public void setVelocityEnemy(Vector2D vector2D, int genitiveRow) {
        GameObjectManager.instance.list
                .stream()
                .filter(gameObject -> gameObject instanceof Enemy)
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> ((Enemy) gameObject).genitiveRow == genitiveRow)
                .forEach(gameObject -> ((Enemy) gameObject).velocity.set(vector2D));
    }

    public Enemy checkCollision(BulletPlayer bulletPlayer) {
        return (Enemy) this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> gameObject instanceof Enemy)
                .filter(gameObject -> {
                    BoxCollider other = ((Enemy) gameObject).boxCollider;
                    return bulletPlayer.boxCollider.checkCollision(other);
                })
                .findFirst()
                .orElse(null);
    }

    public <T extends GameObject & PhysicBody> T checkCollision(BoxCollider boxCollider, Class<T> cls) {
        return (T) this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> cls.isInstance(gameObject))
                .filter(gameObject -> {
                    BoxCollider other = ((T) gameObject).getBoxCollider();
                    return boxCollider.checkCollision(other);
                })
                .findFirst()
                .orElse(null);
    }

    //newInstance su dung khi contructor k co tham so dau vao moi tao moi
    public <T extends GameObject> T recycle(Class<T> cls) {
        T object = (T) this.list
                .stream()
                .filter(gameObject -> !gameObject.isAlive)
                .filter(gameObject1 -> cls.isInstance(gameObject1))
                .findFirst()
                .orElse(null);
        if (object == null) {
            try {
                object = cls.newInstance();
                this.add(object);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            object.isAlive = true;
        }
        return object;
    }

    public void objectExitDisplay(GameObject gameObject) {
        if (gameObject.position.x < 0 || gameObject.position.x > 1024
                || gameObject.position.y < 0 || gameObject.position.y > 600)
            gameObject.isAlive = false;

    }

    public void checkGameOver() {
        this.list
                .stream()
                .filter(gameObject -> gameObject instanceof Enemy)
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> {
                    if (gameObject.position.y + 20 >= 600) {
                        SceneManager.instance.changeScene(new OverScene());
                        return;
                    }
                });
    }

    public void clear() {
        this.list.clear();
        this.tempList.clear();
    }

}
