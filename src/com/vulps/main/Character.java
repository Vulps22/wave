package com.vulps.main;

import java.awt.*;

public abstract class Character extends GameObject{

    protected Color color;
    public Character(int x, int y, int WIDTH, int HEIGHT, Color color, ID id, Handler handler, Boolean collide) {
        super(x, y, WIDTH, HEIGHT, id, handler, collide);

        if(WIDTH <= 0) throw new IllegalArgumentException("SizeX must be a positive integer greater than 0");
        if(HEIGHT <= 0) throw new IllegalArgumentException("SizeY must be a positive integer greater than 0");

        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        this.color = color;
    }

    @Override
    public void tick() {
        x+= velX;
        y += velY;

        checkCollision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    @Override
    public Rectangle getBounds(){ return new Rectangle(x, y, WIDTH, HEIGHT); }

    private void checkCollision(){
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if(tempObject.isCollisionEnabled()) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    onCollision(tempObject);
                }
            }
        }
    }

    protected abstract void onCollision(GameObject object);
}
