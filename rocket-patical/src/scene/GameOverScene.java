package scene;

import base.GameObjectManager;
import game.background.Background;

public class GameOverScene implements Scene {
    @Override
    public void init() {
        GameObjectManager.instance.recycle(Background.class);
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
    }
}
