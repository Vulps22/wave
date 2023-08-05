package com.vulps.main;

import javax.swing.*;
import java.awt.*;

public class Menu {

    private final int WIDTH, HEIGHT;
    private final Component parent;
    public Rectangle playBounds;

    private int score;
    public Boolean visible = true;
    public boolean isMouseOverPlay = false;

    // Colors for the glowing effect
    private final Color glowColor = new Color(255, 0, 0); // Semi-transparent white
    private final int glowRadius = 20; // Radius of the glow effect


    public Menu(int WIDTH, int HEIGHT, Component parent) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.parent = parent;

        // Set the bounds of the "PLAY" text
        Font font = new Font("Arial", Font.BOLD, 48);
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth("PLAY");
        int textHeight = fontMetrics.getHeight();
        int x = (WIDTH - textWidth) / 2;
        int y = (HEIGHT - textHeight) / 2;
        playBounds = new Rectangle(x, y - textHeight, textWidth, textHeight);
    }

    public void tick() {
        // Check for mouse hover and update the menu state if needed
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();

        SwingUtilities.convertPointFromScreen(mouseLocation, parent);

        int mouseX = (int) mouseLocation.getX();
        int mouseY = (int) mouseLocation.getY();

        isMouseOverPlay = playBounds.contains(mouseX, mouseY);
    }


    public void render(Graphics graphics) {
        Font font = new Font("Arial", Font.BOLD, 48);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth("PLAY");
        int textHeight = fontMetrics.getHeight();

        int x = (WIDTH - textWidth) / 2;
        int y = (HEIGHT - textHeight) / 2;

        if (visible) {
            // Draw the "PLAY" text
            graphics.setFont(font);

            if (isMouseOverPlay) {
                // Set the color to red when the mouse hovers over the play button
                graphics.setColor(Color.RED);
            } else {
                // Set the color to white for normal state
                graphics.setColor(Color.WHITE);
            }

            // Draw the "PLAY" text
            graphics.drawString("PLAY", x, y);

            graphics.setFont(new Font("Arial", Font.ITALIC, 12));
            graphics.setColor(Color.white);
            graphics.drawString("© 2023 Vulps23. All Rights Reserved", 10, HEIGHT - 10);

            font = new Font("Arial", Font.ITALIC, 25);
            graphics.setFont(font);
            fontMetrics = graphics.getFontMetrics(font);
            textWidth = fontMetrics.stringWidth("Last Score: " + score);
            x = (WIDTH - textWidth) / 2;

            graphics.setColor(Color.white);
            graphics.drawString("Last Score: " + score, x, y - 150);

            textWidth = fontMetrics.stringWidth("Level: " + score / 1000);
            x = (WIDTH - textWidth) / 2;

            graphics.setColor(Color.white);
            graphics.drawString("Level: " + score / 1000, x, y - 100);
        }
    }

    public void setVisible(boolean visible, int score){
        this.score = score;
        this.visible = visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

}
