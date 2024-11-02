package org.example.engine.handler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface SpriteHandler {

    default BufferedImage getSpriteByPath(String path) {
        try {
            return ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    BufferedImage getSprite();
    void addSprite(String... spritePath);
}
