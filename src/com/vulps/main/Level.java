package com.vulps.main;

public class Level {

    private int level = 1;
    private int scoreLimit = 1000;
    private int enemieMax = 1;
    private int wormholeMax = 1;

    public Level(){
        setLevel(10);
    }
    public Level(int level){
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int nextLevel(){
        int nextLevel = getLevel() + 1;
        setLevel(nextLevel);
        return nextLevel;
    }

    public void setLevel(int level) {
        this.level = level;
        enemieMax = this.level;
        wormholeMax = (int) Math.ceil((double) this.level / 5);
    }

    public int getScoreLimit() {
        return scoreLimit;
    }

    public void setScoreLimit(int scoreLimit) {
        this.scoreLimit = scoreLimit;
    }

    public int getEnemyMax() {
        return enemieMax;
    }

    public void setEnemyMax(int enemieMax) {
        this.enemieMax = enemieMax;
    }

    public int getWormholeMax() {
        return wormholeMax;
    }

    public void setWormholeMax(int wormholeMax) {
        this.wormholeMax = wormholeMax;
    }

    public Boolean shouldSpawnEnemy (int currentEnemies){
        if(currentEnemies < enemieMax) return true;
        else return false;
    }

    public Boolean shouldSpawnWormhole(int currentWormholes){
        if(currentWormholes < wormholeMax) return true;
        else return false;
    }

}
