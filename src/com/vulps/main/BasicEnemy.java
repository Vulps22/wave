package com.vulps.main;

import java.awt.*;
import java.util.Random;

public class BasicEnemy extends Character{

    private int LIFE = 100;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, 16, 16, Color.red, id, handler, true);

        Random r = new Random();

        velX = r.nextInt(5);
        velY = r.nextInt(5);

        if(velX == 0) velX = 5;
        else if (velY == 0) velY = 5;

    }

    public void tick(){
        super.tick();
        if(y <= 0 || y >= Game.HEIGHT - 32){
            velY *=-1;
            subtractLife();
        }
        if(x <= 0 || x >= Game.WIDTH - 32){
            velX *=-1;
            LIFE = LIFE - 20;
        }
        if(velX == velY) velX += 10;

        handler.addObject(new Trail(x, y, ID.Trail, WIDTH, HEIGHT, 0.02f, Color.red, handler));
    }

    @Override
    protected void onCollision(GameObject object) {

    }

    private void subtractLife(){
        if (Game.isPlayerLiving()) LIFE = LIFE - 20;
        if (LIFE <= 0) handler.removeEnemy(this);
    }
}
