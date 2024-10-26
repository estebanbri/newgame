package org.example.handler;

import org.example.enums.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class SpriteHandler {
    public static final int TICK_NUMBER_TO_CHANGE_TO_NEXT_SPRITE = 12;
    private static int tick;
    private final Map<Direction, SpriteContext> spritesMap = new EnumMap<>(Direction.class);

    public static void tick() {
        tick++;
    }
    public BufferedImage getNextSprite(Direction direction) {
        final var spriteContext = this.spritesMap.get(direction);
        return spriteContext.getNextSprite();
    }

    public void addSprites(Direction direction, String... spritesPath) {
        BufferedImage[] sprites = Arrays.stream(spritesPath).map(this::getSpriteByPath).toArray(BufferedImage[]::new);
        spritesMap.putIfAbsent(direction, new SpriteContext(sprites));
    }

    public BufferedImage getStationarySprite() {
        return this.spritesMap.get(Direction.DOWN).getStationarySprite();
    }

    private BufferedImage getSpriteByPath(String path) {
        try {
            return ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class SpriteContext {
        private int currentSpriteIndex;
        private final BufferedImage[] sprites;

        SpriteContext(BufferedImage[] sprites) {
            this.sprites = sprites;
        }
        BufferedImage getNextSprite() {
            conditionalUpdateCurrentSpriteIndex();
            return this.sprites[this.currentSpriteIndex];
        }

        BufferedImage getStationarySprite() {
            return this.sprites[0];
        }

        /**
         * Este metodo va a servir de actualizar a la siguiente frame condicionalmente en caso de que el siguiente indice arrojado desborda el array
         * de sprites el indice se reinicia a 0 para generar un efecto de array circular de sprites.
         */
        private void conditionalUpdateCurrentSpriteIndex() {
            if(canDrawNextSprite()) {
                currentSpriteIndex++;
            }
            currentSpriteIndex = currentSpriteIndex < sprites.length ? currentSpriteIndex : 0;
        }

        /**
         *  Metodo que controla cuando es el momento de pasar al siguiente sprite basandose en los ticks que se han ejecutado del event loop
         *  Esto es util porque evitamos cambiar de sprite en cada tick del gameloop, ya que el gameloop cada tick lo hace extremadamente rapido
         *  por eso se hace este control para decir cada X cantidad de ticks es el momento de pasar al siguiente sprite.
         * @return true si es el momento de pasar a la siguiente sprite basandose en los ticks generados por el game loop
         */
        private static boolean canDrawNextSprite() {
            if (tick >= TICK_NUMBER_TO_CHANGE_TO_NEXT_SPRITE) {
                tick = 0;
                return true;
            }
            return false;
        }

    }

}
