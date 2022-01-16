package me.felix.tired.bridge.shader;

import me.felix.tired.bridge.hooks.MCHook;
import me.felix.tired.bridge.util.ShaderUtil;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;

public abstract class Shader implements MCHook {

    protected int pass;
    protected ShaderProgram shaderProgram;
    protected float width, height;
    protected Framebuffer framebuffer = new Framebuffer(MC.displayWidth, MC.displayHeight, false);

    public Shader(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }

    public void renderShader(float x, float y, float width, float height) {
        this.width = width;
        this.height = height;
        this.framebuffer = ShaderUtil.createFramebuffer(framebuffer);
        framebuffer.framebufferClear();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        MC.getFramebuffer().bindFramebuffer(false);
        doShaderPass(MC.getFramebuffer());
        pass++;
    }

    private void doShaderPass(Framebuffer framebufferIn) {
        shaderProgram.setupShader();
        setUniforms();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebufferIn.framebufferTexture);
        shaderProgram.renderTexture();
        shaderProgram.stopShader();
    }

    public abstract void setUniforms();

    public int pass() {
        return pass;
    }

    public void pass(int pass) {
        this.pass = pass;
    }

}
