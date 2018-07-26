package scene;

import base.GameObjectManager;
import game.background.Background;
import game.enemy.CreatEnemy;
import game.score.Score;
import game.star.CreateStar;
import input.KeyboardInput;
import renderer.Image;
import renderer.ImageRenderer;
import renderer.Text;
import renderer.TextRenderer;
import utils.Utils;

import javax.sound.sampled.Clip;

public class OverScene implements Scene {
    private Clip clip;

    @Override
    public void init() {
        this.setupCharacter();
        this.clip = Utils.loadAudio("resources/audio/musicstart.wav");
        this.clip.loop(-1);
        this.clip.start();
        CreatEnemy.numFly = 60;
        KeyboardInput.isEnter = true;
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
        this.clip.stop();
    }

    private void setupCharacter() {
        GameObjectManager.instance.add(new Background());
        Image image = GameObjectManager.instance.recycle(Image.class);
        image.renderer = new ImageRenderer("resources/images/gameover.png",388,84);
        image.position.set(470, 200);
        Text text = GameObjectManager.instance.recycle(Text.class);
        ((TextRenderer) text.renderer).text = "Press enter to start";
        text.position.set(512, 400);
        GameObjectManager.instance.recycle(CreateStar.class);
        GameObjectManager.instance.add(Score.instance);

    }
}
