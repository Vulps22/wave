package com.vulps.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();
    LinkedList<Sound> sounds = new LinkedList<Sound>();
    Sound soundtrack = new Sound("soundtrack_through_prism.wav", -20f);
    public Level level = new Level();
    private int enemiesSpawned = 0;
    private int wormholesSpawned = 0;
    HUD hud = new HUD();
    public void tick(){
        hud.tick();
        for (int i = 0; i < object.size(); i++) {

            GameObject tempObject = object.get(i);
            tempObject.tick();

        }
        hud.setLevel(level.getLevel());

        if(!soundtrack.isLooping()) soundtrack.loop();

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

    public void addEnemy(GameObject object) {
        if(level.shouldSpawnEnemy(enemiesSpawned)){
            addObject(object);
            enemiesSpawned++;
        }
    }
    public void removeEnemy(GameObject object) {
        removeObject(object);
        enemiesSpawned--;
    }

    public void addWormhole(Wormhole wormhole){
        System.out.println("Spawn Wormhole");
        if(level.shouldSpawnWormhole(wormholesSpawned)) {
            System.out.println("Should Spawn Wormhole");

            addObject(wormhole);
            wormholesSpawned++;
        }
    }

    public void removeWormhole(Wormhole wormhole){
        removeObject(wormhole);
    }

    public void nextLevel(){
        level.nextLevel();
        wormholesSpawned = 0;
    }

}
