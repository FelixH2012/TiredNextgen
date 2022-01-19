package me.felix.tired.bridge.shader;

import me.felix.tired.bridge.util.ShaderUtil;
import me.felix.tired.bridge.util.StencilUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import tired.jdk.api.abstracts.shader.ShaderProgram;
import tired.jdk.intern.hooks.MCHook;

import static org.lwjgl.opengl.GL11.*;

public class BlurShader implements MCHook {

    private final ShaderProgram blurShader = new ShaderProgram("blur.frag");

    private static Framebuffer blurBuffer = new Framebuffer(1, 1, false);

    private float radius;

    public BlurShader(float radius) {
        this.radius = radius;
    }

    public void blur() {
        blur(0, 0, -1, -1);
    }

    public void blur(float x, float y, float width, float height) {

        StencilUtil.checkSetupFBO(Minecraft.getMinecraft().getFramebuffer());

        blurBuffer = ShaderUtil.createFramebuffer(blurBuffer);

        blurShader.setupShader();
        setupUniforms(1, 0);
        blurBuffer.framebufferClear();
        blurBuffer.bindFramebuffer(true);
        glBindTexture(GL_TEXTURE_2D, MC.getFramebuffer().framebufferTexture);
        blurShader.renderTexture();
        blurBuffer.unbindFramebuffer();


        blurShader.setupShader();
        setupUniforms(0, 1);
        MC.getFramebuffer().bindFramebuffer(true);
        glBindTexture(GL_TEXTURE_2D, blurBuffer.framebufferTexture);
        blurShader.renderTexture();
        blurShader.stopShader();
    }

    public void setupUniforms(float x, float y) {
        blurShader.setUniform("currentTexture", 0);
        blurShader.setUniform("texelSize", (float) (1.0 / MC.displayWidth), (float) (1.0 / MC.displayHeight));
        blurShader.setUniform("coords", x, y);
        blurShader.setUniform("blurRadius", radius / 2);
        blurShader.setUniform("blursigma", (float) 2.7);
    }

    public float radius() {
        return radius;
    }

    public void radius(float radius) {
        this.radius = radius;
    }
}
