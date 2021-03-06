package scene;

import base.GameObjectManager;
import game.background.Background;
import game.effect.CreatTripShootShield;
import game.enemy.CreatEnemyLevel2;
import game.player.Player;
import game.score.Score;
import game.star.CreateStar;
import utils.Utils;

import javax.sound.sampled.Clip;

public class GamePlayLevel2Scene implements Scene {
    private Clip clip;

    @Override
    public void init() {
        this.setupCharacter();
        this.clip = Utils.loadAudio("resources/audio/musicbackgound.wav");
        this.clip.loop(-1);
        //this.clip.start();
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
        this.clip.stop();
    }

    private void setupCharacter() {
        GameObjectManager.instance.add(new Background());
        GameObjectManager.instance.recycle(CreateStar.class);
        GameObjectManager.instance.recycle(CreatEnemyLevel2.class);
        GameObjectManager.instance.add(Score.instance);
        CreatTripShootShield creatTripShootShield = GameObjectManager.instance.recycle(CreatTripShootShield.class);
        creatTripShootShield.configAction();
        this.setupPlayer();
    }

    private void setupPlayer() {
        Player player = new Player();
        player.position.set(504, 550);
        GameObjectManager.instance.add(player);
    }
}
