package org.example.blueheroe.scene;

import org.example.engine.entity.BaseEntity;
import org.example.engine.scene.BaseGameScene;

import java.util.List;

public class FirstLevelScene extends BaseGameScene {
    public FirstLevelScene(List<BaseEntity> baseEntityList) {
        super(baseEntityList);
    }

    @Override
    public String getSceneTitle() {
        return "1er Nivel";
    }
}
