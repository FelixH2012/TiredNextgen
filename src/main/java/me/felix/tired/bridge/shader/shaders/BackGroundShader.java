package me.felix.tired.bridge.shader.shaders;

import me.felix.tired.bridge.hooks.MCHook;
import me.felix.tired.bridge.shader.Shader;
import me.felix.tired.bridge.shader.ShaderProgram;
import org.lwjgl.opengl.GL20;

public class BackGroundShader extends Shader implements MCHook {


    public BackGroundShader(int pass) {
        super(new ShaderProgram("fragment/test.glsl"));
        this.pass = pass;
    }

    @Override
    public void renderShader(float x, float y, float width, float height) {
        super.renderShader(x, y, width, height);
    }
    @Override
    public void setUniforms() {
        GL20.glUniform1f(shaderProgram.getUniform("time"), pass / 200f);
        GL20.glUniform2f(shaderProgram.getUniform("resolution"), MC.displayWidth, MC.displayHeight);
    }


}
