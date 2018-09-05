package input;

import game.enemy.CreatEnemyLevel2;
import game.enemy.CreatEnemyLevel3;
import scene.WinScene;
import scene.scene.level.SceneLevel1;
import scene.SceneManager;
import scene.scene.level.SceneLevel2;
import scene.scene.level.SceneLevel3;
import scene.scene.level.SceneLevelBoss;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    public static KeyboardInput instance = new KeyboardInput();

    public boolean isLeft = false;
    public boolean isRight = false;
    public boolean isUp = false;
    public boolean isDown = false;
    public boolean isSpace = false;
    public static boolean isEnter = true;

    private KeyboardInput() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.isUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.isLeft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.isRight = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.isSpace = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.isDown = true;
        }
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            if (KeyboardInput.isEnter){
                SceneManager.instance.changeScene(new SceneLevel1());
                KeyboardInput.isEnter = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_X) {
            SceneManager.instance.changeScene(new SceneLevel2());
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            SceneManager.instance.changeScene(new SceneLevel3());
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            SceneManager.instance.changeScene(new SceneLevelBoss());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.isUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.isLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.isRight = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.isSpace = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.isDown = false;
        }
    }
}
