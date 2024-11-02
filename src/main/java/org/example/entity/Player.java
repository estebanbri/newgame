package org.example.entity;

import org.example.enums.Direction;
import org.example.handler.KeyHandler;
import org.example.behavior.Moveable;
import org.example.handler.AnimatedSpriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.scene.BaseGameScene.TILE_SIZE;

public class Player extends BaseEntity implements Moveable {
    private static final Logger log = LoggerFactory.getLogger(Player.class);
    private final AnimatedSpriteHandler animatedSpriteHandler = new AnimatedSpriteHandler();
    public Player(Point coordinates, int speed) {
        super(coordinates, speed);
        loadSprites();
    }
    @Override
    public void moveRight() {
        this.isMoving = true;
        this.coordinates.x += this.speed;
        this.direction = Direction.RIGHT;
    }

    @Override
    public void moveLeft() {
        this.isMoving = true;
        this.coordinates.x -= this.speed;
        this.direction = Direction.LEFT;
    }

    @Override
    public void moveUp() {
        this.isMoving = true;
        this.coordinates.y -= this.speed;
        this.direction = Direction.UP;
    }

    @Override
    public void moveDown() {
        this.isMoving = true;
        this.coordinates.y += this.speed;
        this.direction = Direction.DOWN;
    }
    @Override
    public void update() {
        if (KeyHandler.up) {
            this.moveUp();
        } else if (KeyHandler.down) {
            this.moveDown();
        }else if (KeyHandler.right) {
            this.moveRight();
        }else if (KeyHandler.left) {
            this.moveLeft();
        } else {
            this.isMoving = false;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage currentImage = null;
        if (!this.isMoving) {
            currentImage = this.animatedSpriteHandler.getIdleSprite(Direction.DOWN);
        } else {
            switch (this.direction) {
                case Direction.UP   -> currentImage = getNextAnimatedSprite(Direction.UP);
                case Direction.DOWN -> currentImage = getNextAnimatedSprite(Direction.DOWN);
                case Direction.RIGHT-> currentImage = getNextAnimatedSprite(Direction.RIGHT);
                case Direction.LEFT -> currentImage = getNextAnimatedSprite(Direction.LEFT);
            }
        }
        g2.drawImage(currentImage, this.coordinates.x, this.coordinates.y, TILE_SIZE, TILE_SIZE, null);
    }
    @Override
    void loadSprites() {
        this.animatedSpriteHandler.setDirection(Direction.UP);
        this.animatedSpriteHandler.addSprite("Player/Walking-sprites/boy_up_1.png", "Player/Walking-sprites/boy_up_2.png");
        this.animatedSpriteHandler.setDirection(Direction.DOWN);
        this.animatedSpriteHandler.addSprite("Player/Walking-sprites/boy_down_1.png", "Player/Walking-sprites/boy_down_2.png");
        this.animatedSpriteHandler.setDirection(Direction.RIGHT);
        this.animatedSpriteHandler.addSprite("Player/Walking-sprites/boy_right_1.png", "Player/Walking-sprites/boy_right_2.png");
        this.animatedSpriteHandler.setDirection(Direction.LEFT);
        this.animatedSpriteHandler.addSprite("Player/Walking-sprites/boy_left_1.png", "Player/Walking-sprites/boy_left_2.png");
        log.info("Player sprites loaded...");
    }

    private BufferedImage getNextAnimatedSprite(Direction direction) {
        this.animatedSpriteHandler.setDirection(direction);
        return this.animatedSpriteHandler.getSprite();
    }
}
