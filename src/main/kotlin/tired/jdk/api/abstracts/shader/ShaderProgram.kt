package tired.jdk.api.abstracts.shader

import me.felix.tired.bridge.util.ShaderUtil
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.OpenGlHelper
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20
import tired.jdk.api.Logger
import tired.jdk.intern.hooks.MCHook
import tired.jdk.intern.hooks.MCHook.MC

class ShaderProgram(private val fragmentName: String, private val vertexName: String = "vertex.vert") : MCHook {
    constructor(fragmentName: String) : this(fragmentName, "vertex.vert") {}

    private var programID = 0

    init {
        run {
            val program = GL20.glCreateProgram()
            var vertexShaderID: Int
            var vertexSourceCode: String?
            run {
                vertexSourceCode = ShaderUtil.readShader("vertex/$vertexName")
                vertexShaderID = OpenGlHelper.glCreateShader(OpenGlHelper.GL_VERTEX_SHADER)
                GL20.glShaderSource(vertexShaderID, vertexSourceCode)
                GL20.glCompileShader(vertexShaderID)
            }
            if (OpenGlHelper.glGetShaderi(vertexShaderID, OpenGlHelper.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
                Logger.doLog(OpenGlHelper.glGetShaderInfoLog(vertexShaderID, 4096), Logger.Type.CONSOLE)
                throw IllegalStateException(
                    String.format(
                        "Vertex Shader (%s) failed to compile!",
                        OpenGlHelper.GL_VERTEX_SHADER
                    )
                )
            }
            val fragmentSource = ShaderUtil.readShader("fragment/$fragmentName")
            val fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER)
            GL20.glShaderSource(fragmentShaderID, fragmentSource)
            GL20.glCompileShader(fragmentShaderID)
            if (OpenGlHelper.glGetShaderi(fragmentShaderID, OpenGlHelper.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
                Logger.doLog(OpenGlHelper.glGetShaderInfoLog(fragmentShaderID, 4096), Logger.Type.CONSOLE)
                throw IllegalStateException(
                    String.format(
                        "Fragment Shader failed to compile!: $fragmentName",
                        OpenGlHelper.GL_FRAGMENT_SHADER
                    )
                )
            }
            Logger.doLog("No problems with the shader detected. ", Logger.Type.CONSOLE)
            OpenGlHelper.glAttachShader(program, vertexShaderID)
            OpenGlHelper.glAttachShader(program, fragmentShaderID)
            OpenGlHelper.glLinkProgram(program)
            this.programID = program
        }
    }

    fun renderTexture() {
        val sr = ScaledResolution(MC)
        val width = sr.scaledWidth_double.toFloat()
        val height = sr.scaledHeight_double.toFloat()
        renderFrameBuffer(0f, 0f, width, height)
    }

    fun renderFrameBuffer(x: Float, y: Float, width: Float, height: Float) {
        if (MC.gameSettings.ofFastRender) return
        GlStateManager.glBegin(OpenGlHelper.GL_QUADS)
        GL11.glTexCoord2f(0f, 1f)
        GL11.glVertex2f(x, y)
        GL11.glTexCoord2f(0f, 0f)
        GL11.glVertex2f(x, height)
        GL11.glTexCoord2f(1f, 0f)
        GL11.glVertex2f(width, height)
        GL11.glTexCoord2f(1f, 1f)
        GL11.glVertex2f(width, y)
        GlStateManager.glEnd()
    }

    fun setupShader() {
        GL20.glUseProgram(programID)
    }

    fun stopShader() {
        GL20.glUseProgram(0)
    }


    fun getUniform(name: String?): Int {
        return GL20.glGetUniformLocation(programID, name)
    }

    fun setUniform(name: String, vararg args: Float) {
        val loc = GL20.glGetUniformLocation(programID, name)
        if (args.size > 1) {
            if (args.size > 2) {
                if (args.size > 3) GL20.glUniform4f(loc, args[0], args[1], args[2], args[3]) else GL20.glUniform3f(
                    loc,
                    args[0], args[1], args[2]
                )
            } else GL20.glUniform2f(loc, args[0], args[1])
        } else GL20.glUniform1f(loc, args[0])
    }


    fun setUniform(name: String?, vararg args: Int) {
        val loc = OpenGlHelper.glGetUniformLocation(programID, name)
        if (args.size > 1) GL20.glUniform2i(loc, args[0], args[1]) else OpenGlHelper.glUniform1i(loc, args[0])
    }

    override fun toString(): String {
        return "ShaderProgram{programID=$programID, vertexName='$vertexName', fragmentName='$fragmentName'}"
    }
}