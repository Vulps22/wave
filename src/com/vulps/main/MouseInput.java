package com.vulps.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private Handler handler;
    private Game game;

    public MouseInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.menu.visible && game.menu.playBounds.contains(e.getPoint())) {
            // Handle the click action for the "PLAY" text
            game.restartGame();
            game.menu.visible = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (game.menu.visible && game.menu.playBounds.contains(e.getX(), e.getY())) {

            game.menu.isMouseOverPlay = true;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (game.menu.visible) {
            game.menu.isMouseOverPlay = false;
        }
    }
}
