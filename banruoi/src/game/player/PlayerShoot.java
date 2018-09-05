package game.player;

import base.FrameCounter;
import base.GameObjectAttributes;
import base.GameObjectManager;
import base.Vector2D;
import game.effect.EffectTripShoot;
import input.KeyboardInput;
import utils.Utils;

import javax.sound.sampled.Clip;

public class PlayerShoot {

    private FrameCounter frameCounter;
    private TripShoot tripShoot;
    private SingShoot singShoot;
    private Clip clip;


    public PlayerShoot() {
        this.frameCounter = new FrameCounter(20);
        this.tripShoot = new TripShoot();
        this.singShoot = new SingShoot();
        this.clip = Utils.loadAudio("resources/audio/shot.wav");
    }

    public void run(Player gameObject) {

        if (this.frameCounter.run() && KeyboardInput.instance.isSpace) {
            this.clip.loop(1);
            this.clip.start();

            if (EffectTripShoot.chonceTripShoot) {
                if (EffectTripShoot.timeLifeTripShoot.run()) {
                    EffectTripShoot.chonceTripShoot = false;
                }
                this.tripShoot.run(gameObject);
                this.frameCounter.reset();
            } else {
                this.singShoot.run(gameObject);
                this.frameCounter.reset();

            }

        }
    }
}
