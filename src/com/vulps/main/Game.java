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

    public Game(){
        handler = new Handler();
        new Window(WIDTH + 15, HEIGHT + 35, "Wave Defender", this);

        spawner = new Spawn(handler);
        registerHandlers();
        registerObjects();


    }

    private void registerObjects(){

        handler.addObject(new Player(100, 100, ID.Player, handler));
    }

    private void registerHandlers(){
        this.addKeyListener(new KeyInput(handler));

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
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if(running) render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
             //   System.out.println("FPS: " + frames);
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        spawner.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        handler.hud.render(g);

        g.dispose();
        bs.show();

    }

    public static int clamp(int var, int min, int max){
        if(var >= max){
            return max;
        }else if(var <= min){
            return min;
        }else{
            return var;
        }
    }

    public static boolean isPlayerLiving(){
        return HUD.HEALTH > 0;
    }


}
