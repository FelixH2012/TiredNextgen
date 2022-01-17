package tired.jdk.api.abstracts.shader

import me.felix.tired.bridge.util.ShaderUtil
import net.minecraft.client.shader.Framebuffer
import org.lwjgl.opengl.GL11
import tired.jdk.intern.hooks.MCHook
import tired.jdk.intern.hooks.MCHook.MC

abstract class Shader(val shaderProgram: ShaderProgram) : MCHook {
    protected var pass = 0
    protected var width = 0f
    protected var height = 0f
    protected var framebuffer = Framebuffer(MC.displayWidth, MC.displayHeight, false)

    open fun renderShader(x: Float, y: Float, width: Float, height: Float) {
        this.width = width
        this.height = height
        framebuffer = ShaderUtil.createFramebuffer(framebuffer)
        framebuffer.framebufferClear()
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        MC.framebuffer.bindFramebuffer(false)
        doShaderPass(MC.framebuffer)
        pass++
    }

    private fun doShaderPass(framebufferIn: Framebuffer) {
        shaderProgram.setupShader()
        setUniforms()
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebufferIn.framebufferTexture)
        shaderProgram.renderTexture()
        shaderProgram.stopShader()
    }

    abstract fun setUniforms()

    open fun pass(): Int {
        return pass
    }

    open fun pass(pass: Int) {
        this.pass = pass
    }
}