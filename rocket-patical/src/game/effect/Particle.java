package game.effect;

import base.FrameCounter;
import base.GameObject;
import base.Vector2D;
import renderer.ImageRenderer;

public class Particle extends GameObject {
    public Vector2D velocity;
    private FrameCounter frameCounter;

    public Particle(){
        this.velocity = new Vector2D();
        this.frameCounter = new FrameCounter(5);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        if (this.frameCounter.run()) {
            ((ImageRenderer) this.renderer).width -= 2;
            ((ImageRenderer) this.renderer).height -= 2;
            this.frameCounter.reset();
        }
        if (((ImageRenderer) this.renderer).width < 0){
            this.isAlive = false;
        }
    }
}
