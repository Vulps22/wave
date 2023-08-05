package com.vulps.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = 2726149768967216305L;
    public static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private static Handler handler;
    private final Spawn spawner;
    public Menu menu;

    public Game(){
        handler = new Handler();
        menu = new Menu(WIDTH, HEIGHT, this);

        new Window(WIDTH + 15, HEIGHT + 35, "Vortex Evader", this);

        spawner = new Spawn(handler);
        registerListeners();
    }

    private void registerObjects(){

        handler.addObject(new Player(100, 100, ID.Player, handler));
    }

    private void registerListeners(){
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(new MouseInput(handler, this));

    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running) render();
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
            }
        }
        stop();
    }

    private void tick(){
        if(!isPlayerLiving()){
            if(!menu.visible) {
                menu.setVisible(true, handler.hud.getScore());
            }
        }
        if(menu.visible){
            menu.tick();
            if(handler.level.getLevel() > 5) handler.level.setLevel(5);
            if(handler.object.size() > 1000) handler.object.clear();
        }
        handler.tick();
        spawner.tick();
    }

    private void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(graphics);

        if (menu.visible) {
            menu.render(graphics);
        } else {
            handler.hud.render(graphics);
        }

        graphics.dispose();
        bufferStrategy.show();
    }

    public Handler getHandler(){
        return handler;
    }

    public static int clamp(int var, int min, int max){
        if(var >= max){
            return max;
        }else return Math.max(var, min);
    }

    public static boolean isPlayerLiving(){
        return handler.hud.HEALTH > 0;
    }

    public void restartGame(){
        handler.reset();
        handler.hud.HEALTH = 100;
        handler.hud.setScore(0);
        registerObjects();
    }

}
