package game.enemy;

import base.GameObject;
import renderer.HpRenderer;

import java.awt.*;

public class BossHp extends GameObject {
    public int hp;
    public BossHp(){
        this.position.set(650,500);
        this.renderer = new HpRenderer(0,15, Color.GREEN);
    }

    @Override
    public void run() {
        super.run();
        ((HpRenderer) renderer).width = hp;
    }
}
