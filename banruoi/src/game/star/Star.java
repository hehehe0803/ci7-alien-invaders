package game.star;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import renderer.ImageRenderer;

public class Star extends GameObject {

    public Vector2D velocity;

    public Star() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/Blue-Star-3.png", 10, 10);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        GameObjectManager.instance.objectExitDisplay(this);

    }
}
