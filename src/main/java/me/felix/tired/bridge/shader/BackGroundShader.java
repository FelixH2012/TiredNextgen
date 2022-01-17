package me.felix.tired.bridge.shader;

import org.lwjgl.opengl.GL20;
import tired.jdk.api.abstracts.shader.Shader;
import tired.jdk.api.abstracts.shader.ShaderProgram;

public class BackGroundShader extends Shader {

    public BackGroundShader(int pass) {
        super(new ShaderProgram("test.glsl"));
        this.setPass(pass);
    }

    @Override
    public void renderShader(float x, float y, float width, float height) {
        super.renderShader(x, y, width, height);
    }

    @Override
    public void setUniforms() {
        GL20.glUniform1f(getShaderProgram().getUniform("time"), getPass() / 200f);
        GL20.glUniform2f(getShaderProgram().getUniform("resolution"), MC.displayWidth, MC.displayHeight);
    }


}
