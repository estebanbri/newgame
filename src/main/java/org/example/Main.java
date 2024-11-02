package org.example;


import org.example.core.GameLoop;
import org.example.core.GameWindow;
import org.example.entity.Enemy;
import org.example.entity.BaseEntity;
import org.example.entity.Player;
import org.example.handler.TilesHandler;
import org.example.scene.MainScene;

import java.util.List;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        BaseEntity player = new Player(new Point(100, 100), 2);

        BaseEntity enemy = new Enemy(new Point(200, 200));
        TilesHandler tilesHandler = new TilesHandler("/Map/level1.txt");
        List<BaseEntity> tileEntities = tilesHandler.getTiles();

        List<BaseEntity> baseEntityList = List.of(player, enemy);
        tileEntities.addAll(baseEntityList);

        MainScene mainScene = new MainScene(tileEntities);
        GameWindow.addScene(mainScene);

        GameLoop.playScene(mainScene);
    }
}