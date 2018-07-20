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
        this.frameCounter = new FrameCounter(30);
        this.tripShoot = new TripShoot();
        this.clip = Utils.loadAudio("resources/audio/player_shoot.wav");
    }

    @Override
    public void run(Player gameObject) {
        if (this.frameCounter.run() && EffectTripShoot.chonceTripShoot && KeyboardInput.instance.isSpace) {
            this.tripShoot.run(gameObject);
            this.frameCounter.reset();
            this.clip.loop(1);
            this.clip.start();
        } else if (this.frameCounter.run() && KeyboardInput.instance.isSpace) {
            BulletPlayer bulletPlayer = GameObjectManager.instance.recycle(BulletPlayer.class);
            bulletPlayer.position.set(gameObject.position);
            bulletPlayer.velocity.set(new Vector2D(0.0f, -4.0f));
            this.frameCounter.reset();
            this.clip.loop(1);
            this.clip.start();
        }
    }
}
