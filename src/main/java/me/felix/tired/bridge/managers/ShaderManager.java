package me.felix.tired.bridge.managers;


import lombok.Getter;
import me.felix.tired.bridge.shader.shaders.BackGroundShader;

public class ShaderManager {

    @Getter
    private BackGroundShader backGroundShader;



    public ShaderManager() {
        setupShaders();
    }

    public void setupShaders() {
        backGroundShader = new BackGroundShader(0);
    }

}
