package org.example.entity;

import java.awt.*;

import static org.example.scene.MainScene.TILE_SIZE;

public class Enemy extends BaseEntity {

    public Enemy(Point coordinates, int speed) {
        super(coordinates, speed);
    }

    public Enemy(Point coordinates) {
        super(coordinates);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(this.coordinates.x, this.coordinates.y, TILE_SIZE, TILE_SIZE);
    }

    @Override
    void loadSprites() {

    }
}
