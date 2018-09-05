package game.effect;

import action.*;
import base.GameObject;
import base.GameObjectManager;

public class CreatTripShootShield extends GameObject {
    public void configAction() {
        this.addAction(
                new GroupAction(
                        new RepeatActionForever(
                                new SequenceAction(
                                        new WaitAction(20 * 60),
                                        new ActionAdapter() {
                                            @Override
                                            public boolean run(GameObject owner) {
                                                GameObjectManager.instance.recycle(EffectTripShoot.class);
                                                return true;
                                            }
                                        }
                                )
                        ),
                        new RepeatActionForever(
                                new SequenceAction(
                                        new WaitAction(30 * 60),
                                        new ActionAdapter() {
                                            @Override
                                            public boolean run(GameObject owner) {
                                                GameObjectManager.instance.recycle(EffectShield.class);
                                                return true;
                                            }
                                        }
                                )
                        )
                )
        );
    }
}
