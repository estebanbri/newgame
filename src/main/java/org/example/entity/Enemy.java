package org.example.entity;

import org.example.handler.InanimateSpriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

import static org.example.scene.MainScene.TILE_SIZE;

public class Enemy extends BaseEntity {

    private static final Logger log = LoggerFactory.getLogger(Enemy.class);
    private final InanimateSpriteHandler inanimateSpriteHandler = new InanimateSpriteHandler();
    public Enemy(Point coordinates) {
        super(coordinates);
        this.loadSprites();
    }

    @Override
    public void update() {
        // TODO implementar move logic
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(inanimateSpriteHandler.getSprite(), this.coordinates.x, this.coordinates.y, TILE_SIZE, TILE_SIZE, null);
    }

    @Override
    void loadSprites() {
        this.inanimateSpriteHandler.addSprite("Enemy/orc.png");
        log.info("Enemy sprite loaded...");
    }
}
