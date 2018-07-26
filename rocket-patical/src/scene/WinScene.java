package scene;

import base.GameObjectManager;
import game.background.Background;
import game.score.Score;
import game.star.CreateStar;
import renderer.Image;
import renderer.ImageRenderer;
import renderer.Text;
import utils.Utils;

import javax.sound.sampled.Clip;

public class WinScene implements Scene {

    private Clip clip;
    @Override
    public void init() {
        this.setupCharacter();
        this.clip = Utils.loadAudio("resources/audio/musicstart.wav");
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
        Image image = GameObjectManager.instance.recycle(Image.class);
        image.renderer = new ImageRenderer("resources/images/win.png",263,84);
        image.position.set(470, 200);
//        Text text = GameObjectManager.instance.recycle(Text.class);
//        text.position.set(512, 400);
        GameObjectManager.instance.add(Score.instance);
        GameObjectManager.instance.recycle(CreateStar.class);

    }
}
