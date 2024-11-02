package org.example.blueheroe.entity;

import org.example.engine.entity.AnimatedBaseEntity;
import org.example.engine.enums.Direction;
import org.example.engine.handler.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends AnimatedBaseEntity {
    public Player(Point coordinates, int speed) {
        super(coordinates, speed);
        loadSprites();
    }

    @Override
    public void update() {
        if (KeyHandler.UP) {
            this.moveUp();
        } else if (KeyHandler.DOWN) {
            this.moveDown();
        }else if (KeyHandler.RIGHT) {
            this.moveRight();
        }else if (KeyHandler.LEFT) {
            this.moveLeft();
        } else {
            this.isMoving = false;
        }
    }

    @Override
    public void loadSprites() {
        this.animatedSpriteHandler.setDirection(Direction.UP);
        this.animatedSpriteHandler.addSprite("Player/Walking-sprites/boy_up_1.png", "Player/Walking-sprites/boy_up_2.png");
        this.animatedSpriteHandler.setDirection(Direction.DOWN);
        this.animatedSpriteHandler.addSprite("Player/Walking-sprites/boy_down_1.png", "Player/Walking-sprites/boy_down_2.png");
        this.animatedSpriteHandler.setDirection(Direction.RIGHT);
        this.animatedSpriteHandler.addSprite("Player/Walking-sprites/boy_right_1.png", "Player/Walking-sprites/boy_right_2.png");
        this.animatedSpriteHandler.setDirection(Direction.LEFT);
        this.animatedSpriteHandler.addSprite("Player/Walking-sprites/boy_left_1.png", "Player/Walking-sprites/boy_left_2.png");
    }
    @Override
    protected BufferedImage getIdleSprite() {
        return this.animatedSpriteHandler.getIdleSprite(Direction.DOWN);
    }


}
