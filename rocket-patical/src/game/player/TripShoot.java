package game.player;

import base.GameObjectAttributes;
import base.GameObjectManager;
import base.Vector2D;

public class TripShoot implements GameObjectAttributes<Player> {
    @Override
    public void run(Player gameObject) {
        BulletPlayer bulletPlayer = GameObjectManager.instance.recycle(BulletPlayer.class);
        bulletPlayer.position.set(gameObject.position.x - 5, gameObject.position.y);
        bulletPlayer.velocity.set(new Vector2D(0.0f, -4.0f));

        BulletPlayer bulletPlayer1 = GameObjectManager.instance.recycle(BulletPlayer.class);
        bulletPlayer1.position.set(gameObject.position.x, gameObject.position.y);
        bulletPlayer1.velocity.set(new Vector2D(0.0f, -4.0f));

        BulletPlayer bulletPlayer2 = GameObjectManager.instance.recycle(BulletPlayer.class);
        bulletPlayer2.position.set(gameObject.position.x + 5, gameObject.position.y);
        bulletPlayer2.velocity.set(new Vector2D(0.0f, -4.0f));

    }
}
