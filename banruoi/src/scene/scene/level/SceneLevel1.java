package scene.scene.level;

import action.ActionAdapter;
import action.SequenceAction;
import action.WaitAction;
import base.GameObject;
import base.GameObjectManager;
import game.background.Background;
import game.star.CreateStar;
import renderer.Image;
import renderer.ImageRenderer;
import renderer.Text;
import renderer.TextRenderer;
import scene.GamePlayLevel1Scene;
import scene.Scene;
import scene.SceneManager;
import utils.Utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SceneLevel1 extends GameObject implements Scene {

    @Override
    public void init() {
        SceneLevel1 sceneLevel1 = GameObjectManager.instance.recycle(SceneLevel1.class);
        sceneLevel1.configAction();
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
    }

    public void configAction() {
        this.addAction(
                new SequenceAction(
                        new ActionAdapter() {
                            @Override
                            public boolean run(GameObject owner) {
                                GameObjectManager.instance.add(new Background());
                                Image image = GameObjectManager.instance.recycle(Image.class);
                                image.renderer = new ImageRenderer("resources/images/level1 (1).png", 263, 84);
                                image.position.set(470, 200);
                                Text text = GameObjectManager.instance.recycle(Text.class);
                                ((TextRenderer) text.renderer).text = "Loading ...";
                                text.position.set(512, 400);
                                GameObjectManager.instance.recycle(CreateStar.class);
                                return true;
                            }
                        },
                        new WaitAction(60),
                        new ActionAdapter() {
                            @Override
                            public boolean run(GameObject owner) {
                                SceneManager.instance.changeScene(new GamePlayLevel1Scene());
                                return true;
                            }
                        }
                )
        );
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
