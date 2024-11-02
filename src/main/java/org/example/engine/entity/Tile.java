package org.example.engine.entity;

import java.awt.*;

public class Tile extends InanimateBaseEntity {
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
    public void loadSprites() {
        this.inanimateSpriteHandler.addSprite("Tile/" + mapIndex + ".png");
    }
}
