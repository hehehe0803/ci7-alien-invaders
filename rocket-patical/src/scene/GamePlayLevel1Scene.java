package scene;

import base.GameObjectManager;
import game.background.Background;
import game.enemy.CreatEnemy;
import game.player.Player;
import game.score.Score;
import game.star.CreateStar;
import utils.Utils;

import javax.sound.sampled.Clip;

public class GamePlayLevel1Scene implements Scene {

    private Clip clip;

    @Override
    public void init() {
        this.setupCharacter();
        this.clip = Utils.loadAudio("resources/audio/RolyPolyNhacChuong-T-Ara_372em.wav");
        this.clip.loop(-1);
        this.clip.start();
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
        this.clip.stop();
    }

    private void setupCharacter() {
        GameObjectManager.instance.add(new Background());
        GameObjectManager.instance.recycle(CreateStar.class);
        GameObjectManager.instance.recycle(CreatEnemy.class);
        GameObjectManager.instance.add(Score.instance);
        Score.instance.score = 0;
        this.setupPlayer();
    }

    private void setupPlayer() {
        Player player = new Player();
        player.position.set(504, 550);
        GameObjectManager.instance.add(player);
    }
}
