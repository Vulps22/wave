package com.vulps.main;

import java.awt.*;

public class BasicEnemy extends Character{


    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, 16, 16, Color.red, id, handler, true);

        velX = 5;
        velY = 5;

    }

    public void tick(){
        super.tick();
        if(y <= 0 || y >= Game.HEIGHT - 32) velY *=-1;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *=-1;

        handler.addObject(new Trail(x, y, ID.Trail, WIDTH, HEIGHT, 0.02f, Color.red, handler));
    }

    @Override
    protected void onCollision(GameObject object) {

    }

}
