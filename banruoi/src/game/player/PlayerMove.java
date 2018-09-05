package game.player;

import base.GameObject;
import base.GameObjectAttributes;
import input.KeyboardInput;

import java.util.Random;

public class PlayerMove implements GameObjectAttributes<Player> {

    public PlayerMove() {

    }

    @Override
    public void run(Player gameObject) {
        if (KeyboardInput.instance.isLeft) {
            gameObject.position.x -= 2;
        }
        if (KeyboardInput.instance.isRight) {
            gameObject.position.x += 2;
        }
        if (KeyboardInput.instance.isUp) {
            gameObject.position.y -= 2;
        }
        if (KeyboardInput.instance.isDown && ((gameObject.position.y + 2 + 50) <= 600)) {
            gameObject.position.y += 2;
        }
        this.backToScreen(gameObject);
    }

    private void backToScreen(GameObject gameObject) {
        if (gameObject.position.x < 0) gameObject.position.x = 1024;

        if (gameObject.position.x > 1024) gameObject.position.x = 0;

        if (gameObject.position.y < 0) gameObject.position.y = 600;
    }
}
