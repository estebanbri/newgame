package org.example.entity;

import org.example.enums.Direction;

import java.awt.*;

public abstract class BaseEntity {
    protected Point coordinates;
    protected int speed;
    protected Direction direction;
    protected boolean isMoving = false;
    BaseEntity(Point coordinates, int speed) {
        this.coordinates = coordinates;
        this.speed = speed;
        this.direction = Direction.DOWN;
    }
    BaseEntity(Point coordinates) {
        this(coordinates, 0);
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2);

    abstract void loadSprites();
}
