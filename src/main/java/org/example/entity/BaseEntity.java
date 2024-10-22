package org.example.entity;

import java.awt.*;

public abstract class BaseEntity {

    protected Point coordinates;
    protected int speed;
    public BaseEntity(Point coordinates, int speed) {
        this.coordinates = coordinates;
        this.speed = speed;
    }
    public BaseEntity(Point coordinates) {
        this(coordinates, 0);
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2);
}
