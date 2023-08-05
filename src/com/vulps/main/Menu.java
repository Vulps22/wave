package com.vulps.main;

import javax.swing.*;
import java.awt.*;

public class Menu {

    private final int WIDTH, HEIGHT;
    private final Component parent;
    public Rectangle playBounds;

    private int score;
    private int plays;
    public Boolean visible = false;
    public boolean isMouseOverPlay = false;

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
           
            // Get the score and level information from the parent game
            int score = this.score;
            int level = score / 1000;

            // Draw the "Last Score" text
            font = new Font("Arial", Font.ITALIC, 25);
            graphics.setFont(font);
            fontMetrics = graphics.getFontMetrics(font);
            textWidth = fontMetrics.stringWidth("Last Score: " + score);
            x = (WIDTH - textWidth) / 2;

            graphics.setColor(Color.white);
            graphics.drawString("Last Score: " + score, x, y - 150);

            // Draw the "Level" text
            textWidth = fontMetrics.stringWidth("Level: " + level);
            x = (WIDTH - textWidth) / 2;
            graphics.setColor(Color.WHITE);
            graphics.drawString("Level: " + level, x, y - 100);

            //Draw the "Plays" text
            font = new Font("Arial", Font.ITALIC, 50);
            graphics.setFont(font);
            fontMetrics = graphics.getFontMetrics(font);

            int plays = this.plays;
            textWidth = fontMetrics.stringWidth(String.valueOf(plays));
            x = (WIDTH - textWidth) / 2;
            graphics.setColor(Color.WHITE);
            graphics.drawString(String.valueOf(plays), x + 300, y - 25);

            //Draw the "Plays" text
            font = new Font("Arial", Font.ITALIC, 30);
            graphics.setFont(font);
            fontMetrics = graphics.getFontMetrics(font);

            textWidth = fontMetrics.stringWidth("Total Plays worldwide");
            x = (WIDTH - textWidth) / 2;
            graphics.setColor(Color.WHITE);
            graphics.drawString("Total Plays Worldwide!", x + 300, y + 25);

        }
    }

    public void setVisible(boolean visible, int score){
        this.score = score;
        // Get the number of plays from the server asynchronously
        new Thread(() -> {
            try {
                // Get the number of plays from the server
                plays = RequestManager.getPlays();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        this.visible = visible;
    }


}
