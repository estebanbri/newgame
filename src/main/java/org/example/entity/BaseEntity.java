package org.example.entity;

import org.example.enums.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class BaseEntity {

    protected Point coordinates;
    protected int speed;
    protected Direction direction;
    protected BufferedImage up1;
    protected BufferedImage up2;
    protected BufferedImage down1;
    protected BufferedImage down2;
    protected BufferedImage right1;
    protected BufferedImage right2;
    protected BufferedImage left1;
    protected BufferedImage left2;
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

    public abstract void update();

    public abstract void draw(Graphics2D g2);

    abstract void loadImages();
}
