package com.vulps.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(Game.isPlayerLiving()) {
            GameObject player = handler.getObject(ID.Player);

            switch (key) {
                case KeyEvent.VK_W -> player.setVelY(-5);
                case KeyEvent.VK_A -> player.setVelX(-5);
                case KeyEvent.VK_S -> player.setVelY(5);
                case KeyEvent.VK_D -> player.setVelX(5);
                case KeyEvent.VK_ESCAPE -> System.exit(0);
            }
        }else{
            if (key == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(Game.isPlayerLiving()) {
            GameObject player = handler.getObject(ID.Player);

            switch (key) {
                case KeyEvent.VK_W, KeyEvent.VK_S -> player.setVelY(0);
                case KeyEvent.VK_A, KeyEvent.VK_D -> player.setVelX(0);
            }
        }
    }
}
