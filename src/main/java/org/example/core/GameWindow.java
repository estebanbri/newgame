package org.example.core;

import org.example.scene.BaseGameScene;
import org.example.scene.MainScene;

import javax.swing.*;

public class GameWindow extends JFrame {

    private BaseGameScene gameScene;
    private static GameWindow gameWindow;

    private GameWindow () {}
    private GameWindow(BaseGameScene gameScene) {
        this.setTitle(gameScene.getSceneTitle());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(gameScene);
        this.pack(); // indica que la ventana se ajuste al size prefered por su subcomponente y se ajuste al layout de su subcomponentes (en este caso al de mi GamePanel)
    }

    public static void create(BaseGameScene mainScene) {
        if (gameWindow == null) {
            gameWindow = new GameWindow(mainScene);
        }
    }


}
