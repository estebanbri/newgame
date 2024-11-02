package org.example.handler;

import org.example.entity.BaseEntity;
import org.example.entity.Tile;
import org.example.scene.BaseGameScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TilesHandler {
    private static final Logger log = LoggerFactory.getLogger(TilesHandler.class);
    public static final String INDEX_SEPARATOR = " ";

    /**
     *  Este map va a contener los index de tu array de tiles, asi con esto no tenes necesidad de crear muchos objetos Tiles para pintar un mapa,
     *  solamente por ejemplo si tu mapa solo tiene un tile de agua, un tile de pared entonces tu array de tiles va a contener index=0 y index=1
     *  solamente necesitas dos objetos Tile  y este map va a contener un mapa con los index=0 y index=1 de tu array y con esto podes pintar all the map.
     */
    private final int[][] mapTilesIndex;

    public TilesHandler(String mapFilePath) {
        mapTilesIndex = new int[BaseGameScene.MAX_SCREEN_COL][BaseGameScene.MAX_SCREEN_ROW];
        this.loadMap(mapFilePath);
    }


    private void loadMap(String mapFilePath) {
        final InputStream is = Objects.requireNonNull(this.getClass().getResourceAsStream(mapFilePath), "Map file not found...");
        try  (final BufferedReader bf = new BufferedReader(new InputStreamReader(is))){
            int screenCol = 0;
            int screenRow = 0;

            while(screenCol < BaseGameScene.MAX_SCREEN_COL && screenRow < BaseGameScene.MAX_SCREEN_ROW) {
                final String line = bf.readLine();
                while (screenCol < BaseGameScene.MAX_SCREEN_COL) {
                    String[] numbers = line.split(INDEX_SEPARATOR);
                    mapTilesIndex[screenCol][screenRow] = Integer.parseInt(numbers[screenCol]);
                    screenCol++;
                }
                if (screenCol == BaseGameScene.MAX_SCREEN_COL) {
                    screenCol = 0;
                    screenRow++;
                }
            }
        } catch (IOException e) {
            log.error("Error while loading tiles map", e);
        }
    }

    public List<BaseEntity> getTiles() {
        int x = 0;
        int y = 0;
        List<BaseEntity> tileResult = new ArrayList<>();
        for (int[] tilesIndex : mapTilesIndex) { // barriendo por columna
            for (int index : tilesIndex) { // barriendo por fila
                tileResult.add(new Tile(new Point(x, y), index));
                y += BaseGameScene.TILE_SIZE;
            }
            y = 0;
            x += BaseGameScene.TILE_SIZE;
        }
        return tileResult;
    }
}
