package org.example.core;

import org.example.scene.BaseGameScene;

public class GameLoop extends Thread {
    private boolean running;
    private BaseGameScene mainScene;

    private static final int FPS = 60;
    private static final double ONE_SECOND_IN_NANO = 1000000000;

    private static GameLoop gameLoop;

    private GameLoop() {}

    private GameLoop(BaseGameScene gameScene) {
        this.mainScene = gameScene;
        this.running = true;
    }

    @Override
    public void run() {
        double delta = 0;
        double drawInterval = ONE_SECOND_IN_NANO / FPS; // The allocated time for a single loop is 0.01666 seconds
        long lastTime = System.nanoTime();
        long currentTime;
        while(running) {
            currentTime = System.nanoTime();
            delta += (currentTime  - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                // 1. UPDATE: update character information sucha as character position
                mainScene.update();
                // 2. DRAW: draw the screen with the updated information
                mainScene.repaint();
                delta--;
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static void playScene(BaseGameScene gameScene) {
        if(gameLoop == null) {
            gameLoop = new GameLoop(gameScene);
        }
        gameLoop.start();;
    }


}
