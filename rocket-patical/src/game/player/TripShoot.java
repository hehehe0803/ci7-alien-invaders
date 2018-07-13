package game.player;

import base.GameObjectAttributes;
import base.GameObjectManager;
import base.Vector2D;

public class TripShoot implements GameObjectAttributes<Player> {
    @Override
    public void run(Player gameObject) {
        BulletPlayer bulletPlayer = new BulletPlayer();
        bulletPlayer.position.set(gameObject.position.x - 5, gameObject.position.y);
        bulletPlayer.velocity.set(new Vector2D(0.0f, -4.0f));
        GameObjectManager.instance.add(bulletPlayer);

        BulletPlayer bulletPlayer1 = new BulletPlayer();
        bulletPlayer1.position.set(gameObject.position.x, gameObject.position.y);
        bulletPlayer1.velocity.set(new Vector2D(0.0f, -4.0f));
        GameObjectManager.instance.add(bulletPlayer1);

        BulletPlayer bulletPlayer2 = new BulletPlayer();
        bulletPlayer2.position.set(gameObject.position.x + 5, gameObject.position.y);
        bulletPlayer2.velocity.set(new Vector2D(0.0f, -4.0f));
        GameObjectManager.instance.add(bulletPlayer2);
    }
}
