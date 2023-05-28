package com.vulps.main;

import java.util.Random;

public class Spawn {

    private final Handler handler;
    private int scoreKeep = 0;
    private final Random r = new Random();
    public Spawn(Handler handler){
        this.handler = handler;
    }

    public void tick(){
        if(Game.isPlayerLiving()) {
            scoreKeep++;
        }else{
            GameObject object = handler.getObject(ID.Player);
            if(object != null) handler.addObject(new BasicEnemy(object.x, object.y, ID.BasicEnemy, handler));

            handler.removeObject(object);
        }

        if (scoreKeep >= 500){
            scoreKeep = 0;
            handler.hud.setLevel(handler.hud.getLevel()+1);
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));


        }
    }

}
