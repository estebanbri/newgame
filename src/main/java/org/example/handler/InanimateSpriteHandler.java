package org.example.handler;

import java.awt.image.BufferedImage;

public class InanimateSpriteHandler implements SpriteHandler {
    private BufferedImage bufferedImage;

    @Override
    public BufferedImage getSprite() {
        return bufferedImage;
    }

    @Override
    public void addSprite(String... spritePath) {
        bufferedImage = this.getSpriteByPath(spritePath[0]);
    }
}
