package org.example.blueheroe.entity;

import org.example.engine.entity.AnimatedBaseEntity;
import org.example.engine.enums.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends AnimatedBaseEntity {

    private int rightCounter;
    private int leftCounter;
    public Enemy(Point coordinates, int speed) {
        super(coordinates, speed);
        loadSprites();
    }
    @Override
    public void update() {
        if (rightCounter < 100) {
            this.moveRight();
            rightCounter++;
        } else  if (leftCounter < 100){
            this.moveLeft();
            leftCounter++;
        } else {
            rightCounter = 0;
            leftCounter = 0;
        }
    }

    @Override
    public void loadSprites() {
        this.animatedSpriteHandler.setDirection(Direction.RIGHT);
        this.animatedSpriteHandler.addSprite("Enemy/orc.png");
        this.animatedSpriteHandler.setDirection(Direction.LEFT);
        this.animatedSpriteHandler.addSprite("Enemy/orc.png");
    }

    @Override
    protected BufferedImage getIdleSprite() {
        return this.animatedSpriteHandler.getIdleSprite(Direction.RIGHT);
    }

}
