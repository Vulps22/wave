package com.vulps.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();
    private int enemiesSpawned = 0;
    HUD hud = new HUD();
    public void tick(){
        hud.tick();
        for (int i = 0; i < object.size(); i++) {

            GameObject tempObject = object.get(i);
            tempObject.tick();

        }
    }
    public void render(Graphics g){
        for (GameObject tempObject : object) {
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public GameObject getObject(ID id) {
        for (GameObject tempObject : object) {
            if (tempObject.getId() == id) {
                return tempObject;
            }
        }
        return null;
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public int getEnemiesSpawned(){
        return enemiesSpawned;
    }

    public void setEnemiesSpawned(int enemies){
        this.enemiesSpawned = enemies;
    }

}
