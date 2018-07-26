package game.player;

import base.FrameCounter;
import base.GameObjectAttributes;
import base.GameObjectManager;
import base.Vector2D;
import game.effect.EffectTripShoot;
import input.KeyboardInput;
import utils.Utils;

import javax.sound.sampled.Clip;

public class PlayerShoot implements GameObjectAttributes<Player> {

    private FrameCounter frameCounter;
    private TripShoot tripShoot;
    private Clip clip;


    public PlayerShoot() {
        this.frameCounter = new FrameCounter(20);
        this.tripShoot = new TripShoot();
        this.clip = Utils.loadAudio("resources/audio/shot.wav");
    }

    @Override
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
//                this.clip.loop(1);
//                this.clip.start();
            } else {
                BulletPlayer bulletPlayer = GameObjectManager.instance.recycle(BulletPlayer.class);
                bulletPlayer.position.set(gameObject.position);
                bulletPlayer.velocity.set(new Vector2D(0.0f, -2.0f));
                this.frameCounter.reset();

            }

        }
    }
}
