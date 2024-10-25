package org.example.entity;

import org.example.enums.Direction;
import org.example.handler.KeyHandler;
import org.example.behavior.Moveable;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.scene.BaseGameScene.TILE_SIZE;

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
            this.direction = Direction.UP;
        }
        if (KeyHandler.down) {
            this.moveDown();
            this.direction = Direction.DOWN;
        }
        if (KeyHandler.right) {
            this.moveRight();
            this.direction = Direction.RIGHT;
        }
        if (KeyHandler.left) {
            this.moveLeft();
            this.direction = Direction.LEFT;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage currentImage = null;
        switch (this.direction) {
            case Direction.UP -> currentImage = this.up1;
            case Direction.DOWN -> currentImage = this.down1;
            case Direction.RIGHT -> currentImage = this.right1;
            case Direction.LEFT -> currentImage = this.left1;
        }
        g2.drawImage(currentImage, this.coordinates.x, this.coordinates.y, TILE_SIZE, TILE_SIZE, null);
    }
    @Override
    void loadImages() {
        this.up1 = this.getImage("Player/Walking-sprites/boy_up_1.png");
        this.up2 = this.getImage("Player/Walking-sprites/boy_up_2.png");
        this.down1 = this.getImage("Player/Walking-sprites/boy_down_1.png");
        this.down2 = this.getImage("Player/Walking-sprites/boy_down_2.png");
        this.right1 = this.getImage("Player/Walking-sprites/boy_right_1.png");
        this.right2 = this.getImage("Player/Walking-sprites/boy_right_2.png");
        this.left1 = this.getImage("Player/Walking-sprites/boy_left_1.png");
        this.left2 = this.getImage("Player/Walking-sprites/boy_left_2.png");
    }

}
