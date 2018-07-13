package game.star;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;

import java.util.Random;

public class CreateStar extends GameObject {

    private FrameCounter frameCounter;
    private Random random;

    public CreateStar() {
        this.frameCounter = new FrameCounter(30);
        this.random = new Random();
    }

    @Override
    public void run() {
        super.run();
        if (this.frameCounter.run()) {
            Star star = GameObjectManager.instance.recycle(Star.class);
            star.position.set(1024, this.random.nextInt(600));
            star.velocity.set(-(this.random.nextInt(3) + 1), 0);
            this.frameCounter.reset();
        }
    }
}
