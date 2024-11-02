package org.example.engine.entity;

import org.example.blueheroe.entity.Player;
import org.example.engine.behavior.Movable;
import org.example.engine.enums.Direction;
import org.example.engine.handler.AnimatedSpriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.engine.scene.BaseGameScene.TILE_SIZE;

public abstract class AnimatedBaseEntity extends BaseEntity implements Movable {

    private static final Logger log = LoggerFactory.getLogger(Player.class);
    protected final AnimatedSpriteHandler animatedSpriteHandler = new AnimatedSpriteHandler();
    protected AnimatedBaseEntity(Point coordinates, int speed) {
        super(coordinates, speed);
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage currentImage = null;
        if (!this.isMoving) {
            currentImage = this.getIdleSprite();
        } else {
            switch (this.direction) {
                case UP -> currentImage = getNextAnimatedSprite(Direction.UP);
                case DOWN -> currentImage = getNextAnimatedSprite(Direction.DOWN);
                case RIGHT -> currentImage = getNextAnimatedSprite(Direction.RIGHT);
                case LEFT -> currentImage = getNextAnimatedSprite(Direction.LEFT);
            }
        }
        g2.drawImage(currentImage, this.coordinates.x, this.coordinates.y, TILE_SIZE, TILE_SIZE, null);
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

    private BufferedImage getNextAnimatedSprite(Direction direction) {
        this.animatedSpriteHandler.setDirection(direction);
        return this.animatedSpriteHandler.getSprite();
    }

    protected abstract BufferedImage getIdleSprite();

}
