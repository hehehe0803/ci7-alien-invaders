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
        this.renderer = new ImageRenderer("resources/images/Blue-Star-3.png", 10, 10);
        this.frameCounter = new FrameCounter(5);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        if (this.frameCounter.run()){
            ((ImageRenderer)this.renderer).width -= 1;
            ((ImageRenderer)this.renderer).height -= 1;
            this.frameCounter.reset();
        }
        if (((ImageRenderer)this.renderer).width <= 0){
            this.isAlive = false;
            ((ImageRenderer)this.renderer).width = 10;
            ((ImageRenderer)this.renderer).height = 10;
        }
    }
}
