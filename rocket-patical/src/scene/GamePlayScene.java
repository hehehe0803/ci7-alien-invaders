package scene;

import base.GameObjectManager;
import game.background.Background;
import game.player.Player;
import game.star.CreateStar;
import utils.Utils;

import javax.sound.sampled.Clip;

public class GamePlayScene implements Scene {

    private Clip clip;

    @Override
    public void init() {
        this.setupCharacter();
        this.clip = Utils.loadAudio("resources/audio/shot.wav");
//        this.clip.loop(-1);
//        this.clip.start();
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
        this.clip.stop();
    }

    private void setupCharacter() {
        GameObjectManager.instance.add(new Background());
        GameObjectManager.instance.add(new CreateStar());
        CreateStar createStar = GameObjectManager.instance.recycle(CreateStar.class);
        createStar.configAction();
      //  GameObjectManager.instance.add(new Enemy());
       // CreateEnemy createEnemy = GameObjectManager.instance.recycle(CreateEnemy.class);
       // createEnemy.configAction();
        this.setupPlayer();

       // Demo demo = GameObjectManager.instance.recycle(Demo.class);
       // demo.position.set(512, 300);

    }

    private void setupPlayer() {
        Player player = new Player();
        player.position.set(100, 200);
        GameObjectManager.instance.add(player);
    }
}
