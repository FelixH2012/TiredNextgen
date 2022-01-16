package me.felix.tired.bridge.shader;

import me.felix.tired.bridge.hooks.MCHook;
import me.felix.tired.bridge.util.ShaderUtil;
import me.felix.tired.logger.Logger;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderProgram implements MCHook {

    private final String vertName, fragName;
    private final int programID;

    public ShaderProgram(String vertName, String fragName) {
        this.vertName = vertName;
        this.fragName = fragName;

        {
            final int program = GL20.glCreateProgram();

            int vertexShaderID;
            String vertexSourceCode;
            {
                vertexSourceCode = ShaderUtil.readShader(vertName);
                vertexShaderID = OpenGlHelper.glCreateShader(OpenGlHelper.GL_VERTEX_SHADER);
                GL20.glShaderSource(vertexShaderID, vertexSourceCode);
                GL20.glCompileShader(vertexShaderID);
            }

            if (OpenGlHelper.glGetShaderi(vertexShaderID, OpenGlHelper.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
                Logger.doLog(OpenGlHelper.glGetShaderInfoLog(vertexShaderID, 4096), Logger.LoggingType.CONSOLE);
                throw new IllegalStateException(String.format("Vertex Shader (%s) failed to compile!", OpenGlHelper.GL_VERTEX_SHADER));
            }

            final String fragmentSource = ShaderUtil.readShader(fragName);
            int fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
            GL20.glShaderSource(fragmentShaderID, fragmentSource);
            GL20.glCompileShader(fragmentShaderID);

            if (OpenGlHelper.glGetShaderi(fragmentShaderID, OpenGlHelper.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
                Logger.doLog(OpenGlHelper.glGetShaderInfoLog(fragmentShaderID, 4096), Logger.LoggingType.CONSOLE);
                throw new IllegalStateException(String.format("Fragment Shader failed to compile!: " + fragName, OpenGlHelper.GL_FRAGMENT_SHADER));
            }


            Logger.doLog("No problems with the shader detected. ", Logger.LoggingType.CONSOLE);

            OpenGlHelper.glAttachShader(program, vertexShaderID);
            OpenGlHelper.glAttachShader(program, fragmentShaderID);
            OpenGlHelper.glLinkProgram(program);
            this.programID = program;

        }
    }

    public ShaderProgram(String fragmentName) {
        this("vertex/vertex.vert", fragmentName);
    }

    public void renderTexture() {
        final ScaledResolution sr = new ScaledResolution(MC);
        float width = (float) sr.getScaledWidth_double();
        float height = (float) sr.getScaledHeight_double();
        renderFrameBuffer(0, 0, width, height);
    }

    public void renderFrameBuffer(float x, float y, float width, float height) {
        if (MC.gameSettings.ofFastRender) return;
        GlStateManager.glBegin(OpenGlHelper.GL_QUADS);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, height);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(width, height);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(width, y);
        GlStateManager.glEnd();
    }

    public void setupShader() {
        GL20.glUseProgram(programID);
    }

    public void stopShader() {
        GL20.glUseProgram(0);
    }


    public int getUniform(String name) {
        return GL20.glGetUniformLocation(this.programID, name);
    }

    public void setUniformf(String name, float... args) {
        int loc = GL20.glGetUniformLocation(programID, name);
        if (args.length > 1) {
            if (args.length > 2) {
                if (args.length > 3) GL20.glUniform4f(loc, args[0], args[1], args[2], args[3]);
                else GL20.glUniform3f(loc, args[0], args[1], args[2]);
            } else GL20.glUniform2f(loc, args[0], args[1]);
        } else GL20.glUniform1f(loc, args[0]);
    }


    public void setUniformi(String name, int... args) {
        int loc = OpenGlHelper.glGetUniformLocation(programID, name);
        if (args.length > 1) GL20.glUniform2i(loc, args[0], args[1]);
        else OpenGlHelper.glUniform1i(loc, args[0]);
    }

    @Override
    public String toString() {
        return "ShaderProgram{" + "programID=" + programID + ", vertexName='" + vertName + '\'' + ", fragmentName='" + fragName + '\'' + '}';
    }

}
