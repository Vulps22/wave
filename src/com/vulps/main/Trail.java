package com.vulps.main;

import java.awt.*;

public class Trail extends GameObject{

    private float alpha = 1;
    private final Color color;
    private final int WIDTH, HEIGHT;
    private final float LIFE;

    public Trail(int x, int y, ID id, int width, int height, float life,  Color color, Handler handler) {
        super(x, y, width, height, id, handler, false);
        this.color = color;
        this.LIFE = life;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    @Override
    public void tick() {

        if(alpha > LIFE){
            alpha -= LIFE - 0.001f;
        } else{
            handler.removeObject(this);
        }

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
