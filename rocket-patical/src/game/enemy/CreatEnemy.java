package game.enemy;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import renderer.ImageRenderer;

import java.util.Random;

public class CreatEnemy extends GameObject {

    private FrameCounter frameCounter;
    private Random random;
    int rows, cols;

    public CreatEnemy() {
        this.frameCounter = new FrameCounter(900);
        this.random = new Random();
        this.rows = 4;
        this.cols = 20;
        this.creat();
    }

    public void creat(){
        for (int row = 0; row < this.rows; row++ ){
            for (int col = 0; col < this.cols; col++){
                Enemy enemy = new Enemy();
                enemy.position.set(100 + col * (((ImageRenderer)enemy.renderer).width + 20), ((ImageRenderer)enemy.renderer).height + row * 50);
                enemy.velocity.set(0.0f, 0.2f);
                GameObjectManager.instance.add(enemy);
            }
        }
    }
    @Override
    public void run() {
        super.run();
        if (this.frameCounter.run()){
            this.frameCounter.reset();
        }
    }
}
