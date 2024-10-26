package org.example.entity;

import org.example.enums.Direction;
import org.example.handler.KeyHandler;
import org.example.behavior.Moveable;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.scene.BaseGameScene.TILE_SIZE;

public class Player extends BaseEntity implements Moveable {

    private BufferedImage[] up;
    private BufferedImage[] down;
    private BufferedImage[] right;
    private BufferedImage[] left;

    private int upSpriteIndex = 0;
    private int downSpriteIndex = 0;
    private int rightSpriteIndex = 0;
    private int leftSpriteIndex = 0;
    public Player(Point coordinates, int speed) {
        super(coordinates, speed);
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
            currentImage = this.down[0];
        } else {
            switch (this.direction) {
                case Direction.UP -> {
                    upSpriteIndex = getSpriteIndex(this.up, upSpriteIndex);
                    currentImage = this.up[upSpriteIndex];
                }
                case Direction.DOWN -> {
                    downSpriteIndex = getSpriteIndex(this.down, downSpriteIndex);
                    currentImage = this.down[downSpriteIndex];
                }
                case Direction.RIGHT -> {
                    rightSpriteIndex = getSpriteIndex(this.right, rightSpriteIndex);
                    currentImage = this.right[rightSpriteIndex];
                }
                case Direction.LEFT -> {
                    leftSpriteIndex = getSpriteIndex(this.left, leftSpriteIndex);
                    currentImage = this.left[leftSpriteIndex];
                }
            }
        }
        g2.drawImage(currentImage, this.coordinates.x, this.coordinates.y, TILE_SIZE, TILE_SIZE, null);
    }
    @Override
    void loadImages() {
        this.up = new BufferedImage[]{this.getImage("Player/Walking-sprites/boy_up_1.png"), this.getImage("Player/Walking-sprites/boy_up_2.png")};
        this.down = new BufferedImage[]{this.getImage("Player/Walking-sprites/boy_down_1.png"), this.getImage("Player/Walking-sprites/boy_down_2.png")};
        this.right = new BufferedImage[]{this.getImage("Player/Walking-sprites/boy_right_1.png"), this.getImage("Player/Walking-sprites/boy_right_2.png")};
        this.left = new BufferedImage[]{this.getImage("Player/Walking-sprites/boy_left_1.png"), this.getImage("Player/Walking-sprites/boy_left_2.png")};
    }
}
