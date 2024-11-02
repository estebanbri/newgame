package org.example.entity;

import org.example.handler.InanimateSpriteHandler;

import java.awt.*;

import static org.example.scene.BaseGameScene.TILE_SIZE;

public class Tile extends BaseEntity {
    private final InanimateSpriteHandler inanimateSpriteHandler = new InanimateSpriteHandler();
    private final int mapIndex;
    public Tile(Point coordinates, int mapIndex) {
        super(coordinates);
        this.mapIndex = mapIndex;
        this.loadSprites();
    }

    @Override
    public void update() {
        // TODO mejorar arquitectura para que no pase esto de metodos vacios
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(inanimateSpriteHandler.getSprite(), this.coordinates.x, this.coordinates.y, TILE_SIZE, TILE_SIZE, null);
    }

    @Override
    void loadSprites() {
        this.inanimateSpriteHandler.addSprite("Tile/" + mapIndex + ".png");
    }
}
