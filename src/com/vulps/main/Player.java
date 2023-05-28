package com.vulps.main;

import java.awt.*;

public class Player extends Character{

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, 32, 32, Color.white, id, handler, true);
    }

    public void tick(){
        if(HUD.HEALTH > 0) {
            super.tick();


            x = Game.clamp(x, 0, Game.WIDTH - 32);
            y = Game.clamp(y, 0, Game.HEIGHT - 32);

            handler.addObject(new Trail(x, y, id, WIDTH, HEIGHT, 0.1f, Color.white, handler));
        }

    }

    @Override
    protected void onCollision(GameObject object){
        if(object.getId() == ID.BasicEnemy) HUD.HEALTH -= 2;
    }
}
