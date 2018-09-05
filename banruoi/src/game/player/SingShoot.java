package game.player;

import base.GameObjectAttributes;
import base.GameObjectManager;
import base.Vector2D;
public class SingShoot implements GameObjectAttributes<Player> {

    @Override
    public void run(Player gameObject) {
        BulletPlayer bulletPlayer = GameObjectManager.instance.recycle(BulletPlayer.class);
        bulletPlayer.position.set(gameObject.position);
        bulletPlayer.velocity.set(new Vector2D(0.0f, -2.0f));
    }
}
