package game.enemy;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;

public class EnemyDrop extends GameObject{

    public void run(){
        super.run();
        Player player = GameObjectManager.instance.findPlayer();
        Enemy enemy1 = GameObjectManager.instance.findEnemyTail();
        Vector2D velocity = player.position
                .subtract(enemy1.position)
                .normalize()
                .multiply(1.5f);
        enemy1.velocity.set(velocity);
    }



}
