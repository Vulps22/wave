package com.vulps.main;

import java.awt.*;

public class Wormhole extends GameObject{

    private int delta = 0;
    private int enemiesSpawned = 0;

    private int spawnLimit = 5;
    public Wormhole(int x, int y, ID id, Handler handler, Boolean collision) {
        super(x, y, 100, 100, id, handler, collision);
    }

    @Override
    public void tick() {
        if(delta == 100){
            spawnEnemy();
            delta = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(x, y, WIDTH, HEIGHT);

        g.setColor(Color.white);
        g.fillOval(x + (WIDTH/4), y + (HEIGHT/4), WIDTH/2, HEIGHT/2);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    private void spawnEnemy(){

    }
}
