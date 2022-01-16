package me.felix.tired.bridge.util;

import lombok.experimental.UtilityClass;
import me.felix.tired.bridge.hooks.MCHook;
import me.felix.tired.logger.Logger;
import net.minecraft.client.shader.Framebuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@UtilityClass
public class ShaderUtil implements MCHook {

    public Framebuffer createFramebuffer(Framebuffer framebuffer) {
        if (framebuffer == null || framebuffer.framebufferWidth != MC.displayWidth || framebuffer.framebufferHeight != MC.displayHeight) {
            if (framebuffer != null) {
                framebuffer.deleteFramebuffer();
            }
            return new Framebuffer(MC.displayWidth, MC.displayHeight, true);
        }
        return framebuffer;
    }

    public String readShader(String fileName) {
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(String.format("assets/minecraft/shaders2/%s", fileName))));
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null)
                stringBuilder.append(line).append('\n');
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            Logger.doLog("Unable to read Shader source! " + e, Logger.LoggingType.CONSOLE);
        }

        return stringBuilder.toString();
    }


}
