package org.example.engine.core;

import org.example.engine.scene.BaseGameScene;

import javax.swing.*;

public class GameWindow extends JFrame {
    private static GameWindow gameWindow;

    private GameWindow () {}
    private GameWindow(BaseGameScene gameScene) {
        this.setTitle(gameScene.getSceneTitle());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(gameScene);
        this.pack(); // indica que la ventana se ajuste al size prefered por su subcomponente y se ajuste al layout de su subcomponentes (en este caso al de mi GamePanel)
    }

    public static void addScene(BaseGameScene mainScene) {
        if (gameWindow == null) {
            gameWindow = new GameWindow(mainScene);
        }
    }


}
