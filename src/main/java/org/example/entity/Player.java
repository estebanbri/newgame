package org.example.entity;

import org.example.enums.Direction;
import org.example.handler.KeyHandler;
import org.example.behavior.Moveable;
import org.example.handler.SpriteHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.scene.BaseGameScene.TILE_SIZE;

public class Player extends BaseEntity implements Moveable {
    private final SpriteHandler spriteHandler = new SpriteHandler();
    public Player(Point coordinates, int speed) {
        super(coordinates, speed);
        loadSprites();
    }
    @Override
    public void moveRight() {
        this.isMoving = true;
        this.coordinates.x += this.speed;
    }

    @Override
    public void moveLeft() {
        this.isMoving = true;
        this.coordinates.x -= this.speed;
    }

    @Override
    public void moveUp() {
        this.isMoving = true;
        this.coordinates.y -= this.speed;
    }

    @Override
    public void moveDown() {
        this.isMoving = true;
        this.coordinates.y += this.speed;
    }
    @Override
    public void update() {
        if (KeyHandler.up) {
            this.moveUp();
            this.direction = Direction.UP;
        } else if (KeyHandler.down) {
            this.moveDown();
            this.direction = Direction.DOWN;
        }else if (KeyHandler.right) {
            this.moveRight();
            this.direction = Direction.RIGHT;
        }else if (KeyHandler.left) {
            this.moveLeft();
            this.direction = Direction.LEFT;
        } else {
            this.isMoving = false;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage currentImage = null;
        if (!this.isMoving) {
            currentImage = this.spriteHandler.getIdleSprite(Direction.DOWN);
        } else {
            switch (this.direction) {
                case Direction.UP   -> currentImage = this.spriteHandler.getAnimatedSprite(Direction.UP);
                case Direction.DOWN -> currentImage = this.spriteHandler.getAnimatedSprite(Direction.DOWN);
                case Direction.RIGHT-> currentImage = this.spriteHandler.getAnimatedSprite(Direction.RIGHT);
                case Direction.LEFT -> currentImage = this.spriteHandler.getAnimatedSprite(Direction.LEFT);
            }
        }
        g2.drawImage(currentImage, this.coordinates.x, this.coordinates.y, TILE_SIZE, TILE_SIZE, null);
    }
    @Override
    void loadSprites() {
        this.spriteHandler.addSprites(Direction.UP, "Player/Walking-sprites/boy_up_1.png", "Player/Walking-sprites/boy_up_2.png");
        this.spriteHandler.addSprites(Direction.DOWN, "Player/Walking-sprites/boy_down_1.png", "Player/Walking-sprites/boy_down_2.png");
        this.spriteHandler.addSprites(Direction.RIGHT, "Player/Walking-sprites/boy_right_1.png", "Player/Walking-sprites/boy_right_2.png");
        this.spriteHandler.addSprites(Direction.LEFT, "Player/Walking-sprites/boy_left_1.png", "Player/Walking-sprites/boy_left_2.png");
    }
}
