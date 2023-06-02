package com.vulps.main;

import java.awt.*;
import java.util.Random;

public class Wormhole extends GameObject{

    private int delta = 0;
    private int lifespan = 350;
    private int enemiesSpawned = 0;

    private Random r = new Random();

    private int spawnLimit = 5;
    public Wormhole(int x, int y, ID id, Handler handler, Boolean collision) {
        super(x, y, 100, 100, id, handler, collision);
        checkCollision();
    }

    @Override
    public void tick() {

        if(delta == 50){
            spawnEnemy();
            delta = 0;
        }else{
            delta++;
        }
        lifespan--;
        if(lifespan <= 0) handler.removeWormhole(this);
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
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    private void spawnEnemy(){

        if(enemiesSpawned < 5){
            handler.addEnemy(new BasicEnemy((x + WIDTH/4), (y+HEIGHT/4), ID.BasicEnemy, handler));
            enemiesSpawned++;
        }
    }

    @Override
    protected void checkCollision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if(tempObject.id == ID.Wormhole) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    onCollision(tempObject);
                }
            }
        }
    }

    @Override
    protected void onCollision(GameObject object) {
        if(object.id == ID.Wormhole){

            Random r = new Random();
            x += 150;
            y += 150;

        }
    }
}
