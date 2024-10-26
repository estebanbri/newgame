package org.example.entity;

import org.example.enums.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class BaseEntity {

    protected int frameCount = 0;
    protected Point coordinates;
    protected int speed;
    protected Direction direction;

    protected boolean isMoving = false;
    BaseEntity(Point coordinates, int speed) {
        this.coordinates = coordinates;
        this.speed = speed;
        this.direction = Direction.DOWN;
        loadImages();
    }
    BaseEntity(Point coordinates) {
        this(coordinates, 0);
    }

    protected BufferedImage getImage(String path) {
        try {
            return ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected int getSpriteIndex(BufferedImage[] sprites, int spriteIndex) {
        if(this.canDrawNextSprite()) {
            spriteIndex++;
        }
        return spriteIndex < sprites.length ? spriteIndex : 0;
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2);

    abstract void loadImages();

    private boolean canDrawNextSprite() {
        // Este frameCount nos va a servir para setear cada 12 frame el cambio de sprint, esto es cada 10 frame porque sino la animacion quedaria muy rapida en la animacion de sprites
        frameCount++;
        if (frameCount == 12) {
            frameCount = 0;
            return true;
        }
        return false;
    }
}
