package com.vulps.main;

import java.awt.*;

public abstract class GameObject {

    protected int x, y;
    protected ID id;
    protected int velX, velY;
    protected Handler handler;
    protected Boolean collision;

    protected int WIDTH, HEIGHT;

    public GameObject(int x, int y, int WIDTH, int HEIGHT, ID id, Handler handler, Boolean collision){
        this.x = x;
        this.y = y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.id = id;
        if (handler == null) {
            throw new IllegalArgumentException("Handler cannot be null");
        }else {
            this.handler = handler;
        }
        this.collision = collision;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public Boolean isCollisionEnabled(){
        return collision;
    }

    public void setCollisionEnabled(Boolean shouldCollide){
        this.collision = shouldCollide;
    }

    protected void checkCollision(){
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ID getId(){
        return id;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }
}
