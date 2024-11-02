package org.example.blueheroe;


import org.example.blueheroe.scene.FirstLevelScene;
import org.example.engine.core.GameLoop;
import org.example.engine.core.GameWindow;
import org.example.blueheroe.entity.Enemy;
import org.example.engine.entity.BaseEntity;
import org.example.blueheroe.entity.Player;
import org.example.engine.handler.TilesHandler;

import java.util.List;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        BaseEntity player = new Player(new Point(100, 100), 2);

        BaseEntity enemy = new Enemy(new Point(200, 200), 1);
        TilesHandler tilesHandler = new TilesHandler("/Map/level1.txt");
        List<BaseEntity> tileEntities = tilesHandler.getTiles();

        List<BaseEntity> baseEntityList = List.of(player, enemy);
        tileEntities.addAll(baseEntityList);

        FirstLevelScene firstLevelScene = new FirstLevelScene(tileEntities);
        GameWindow.addScene(firstLevelScene);
        GameLoop.playScene(firstLevelScene);
    }
}