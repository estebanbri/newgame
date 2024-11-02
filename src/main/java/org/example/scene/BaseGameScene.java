package org.example.scene;

import org.example.handler.KeyHandler;
import org.example.entity.BaseEntity;
import org.example.handler.AnimatedSpriteHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class BaseGameScene extends JPanel {
    // Screen settings
    private static final int ORIGINAL_TILES_IZE = 16; // 16x16 tile
    private static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILES_IZE * SCALE; // 48x48 tile

    // ratio es 4:3
    public static final int MAX_SCREEN_COL = 16; // 16 tiles horizontally
    public static final int MAX_SCREEN_ROW = 12; // 12 tiles vertically

    private static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768px
    private static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576px

    protected final List<BaseEntity> baseEntityList;

    BaseGameScene(List<BaseEntity> baseEntityList) {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true); // mejora la performance del rendering
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true); // asi puede ser focuseable para recibir inputs de teclas (key)
        this.baseEntityList = baseEntityList;
    }

    public void update() {
        for(BaseEntity baseEntity : this.baseEntityList) {
            baseEntity.update();
        }
    }

    @Override
    protected void paintComponent(Graphics g) { // DRAW
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        AnimatedSpriteHandler.tick();
        for(BaseEntity baseEntity : this.baseEntityList) {
            baseEntity.draw(g2);
        }
        g2.dispose();
    }

    public abstract String getSceneTitle();
}
