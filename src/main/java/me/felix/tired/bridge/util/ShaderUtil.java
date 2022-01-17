package me.felix.tired.bridge.util;

import lombok.experimental.UtilityClass;
import net.minecraft.client.shader.Framebuffer;
import tired.jdk.api.Logger;
import tired.jdk.intern.hooks.MCHook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@UtilityClass
public class ShaderUtil implements MCHook {

    public static Framebuffer createFramebuffer(Framebuffer framebuffer) {
        if (framebuffer == null || framebuffer.framebufferWidth != MC.displayWidth || framebuffer.framebufferHeight != MC.displayHeight) {
            if (framebuffer != null) {
                framebuffer.deleteFramebuffer();
            }
            return new Framebuffer(MC.displayWidth, MC.displayHeight, true);
        }
        return framebuffer;
    }

    public static String readShader(String fileName) {
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(String.format("assets/minecraft/shaders/client/%s", fileName))));
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null)
                stringBuilder.append(line).append('\n');
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            Logger.INSTANCE.doLog("Unable to read Shader source! " + e, Logger.Type.CONSOLE);
        }

        return stringBuilder.toString();
    }


}
