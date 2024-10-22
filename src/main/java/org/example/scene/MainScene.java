package org.example.scene;

import org.example.entity.BaseEntity;
import java.util.List;

public class MainScene extends BaseGameScene {
    public MainScene(List<BaseEntity> baseEntityList) {
        super(baseEntityList);
    }

    @Override
    public String getSceneTitle() {
        return "Main Scene";
    }


}
