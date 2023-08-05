package com.vulps.main;

public class Level {

    private int level = 1;
    private int scoreLimit = 1000;
    private int enemyMax = 1;
    private int wormholeMax = 1;

    public Level(){
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
        enemyMax = this.level;
        wormholeMax = (int) Math.ceil((double) this.level / 5);
    }

    public int getScoreLimit() {
        return scoreLimit;
    }

    public void setScoreLimit(int scoreLimit) {
        this.scoreLimit = scoreLimit;
    }

    public int getEnemyMax() {
        return enemyMax;
    }

    public void setEnemyMax(int enemieMax) {
        this.enemyMax = enemieMax;
    }

    public int getWormholeMax() {
        return wormholeMax;
    }

    public void setWormholeMax(int wormholeMax) {
        this.wormholeMax = wormholeMax;
    }

    public Boolean shouldSpawnEnemy(int currentEnemies){
        return currentEnemies < enemyMax;
    }

    public Boolean shouldSpawnWormhole(int currentWormholes){
        return currentWormholes < wormholeMax;
    }

}
