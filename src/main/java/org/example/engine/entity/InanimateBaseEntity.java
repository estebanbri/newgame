package org.example.engine.entity;

import org.example.engine.handler.InanimateSpriteHandler;

import java.awt.*;

import static org.example.engine.scene.BaseGameScene.TILE_SIZE;

public abstract class InanimateBaseEntity extends BaseEntity {

    protected final InanimateSpriteHandler inanimateSpriteHandler = new InanimateSpriteHandler();
    protected InanimateBaseEntity(Point coordinates) {
        super(coordinates);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(inanimateSpriteHandler.getSprite(), this.coordinates.x, this.coordinates.y, TILE_SIZE, TILE_SIZE, null);
    }
}
