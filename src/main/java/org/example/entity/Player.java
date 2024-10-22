package org.example.entity;

import org.example.handler.KeyHandler;
import org.example.behavior.Moveable;

import java.awt.*;

import static org.example.scene.MainScene.TILE_SIZE;

public class Player extends BaseEntity implements Moveable {
    public Player(Point coordinates, int speed) {
        super(coordinates, speed);
    }
    @Override
    public void moveRight() {
        this.coordinates.x += this.speed;
    }

    @Override
    public void moveLeft() {
        this.coordinates.x -= this.speed;
    }

    @Override
    public void moveUp() {
        this.coordinates.y -= this.speed;
    }

    @Override
    public void moveDown() {
        this.coordinates.y += this.speed;
    }
    @Override
    public void update() {
        if (KeyHandler.up) {
            this.moveUp();
        }
        if (KeyHandler.down) {
            this.moveDown();
        }
        if (KeyHandler.right) {
            this.moveRight();
        }
        if (KeyHandler.left) {
            this.moveLeft();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(this.coordinates.x, this.coordinates.y, TILE_SIZE, TILE_SIZE);
    }

}
