package me.felix.tired.bridge.managers;


import lombok.Getter;
import me.felix.tired.bridge.shader.BackGroundShader;

public class ShaderManager {

    @Getter
    private static BackGroundShader backGroundShader;


    public ShaderManager() {
        setupShaders();
    }

    public void setupShaders() {
        backGroundShader = new BackGroundShader(0);
    }

}
