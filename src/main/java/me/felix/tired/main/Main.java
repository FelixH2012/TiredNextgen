package me.felix.tired.main;

/*
Tired is a Multithreading Minecraft modification which provides a good gaming experience
@Tired-Client.de©
*/

import me.felix.tired.bridge.font.FontLoader;
import me.felix.tired.bridge.managers.ShaderManager;
import me.felix.tired.bridge.rendering.clickgui.ClickGUIRenderer;
import me.felix.tired.threads.MainThread;
import me.felix.tired.threads.ManagerThread;
import org.lwjgl.opengl.Display;
import tired.jdk.api.Tired;

import java.util.StringJoiner;

public class Main {

    public static final String NAME = "Tired";
    public static final String VERSION = "b69";
    public static final String[] AUTHORS = new String[]{"Felix1337", "Kroko"};

    public static Main instance;

    public ClickGUIRenderer clickGUIRenderer;

    public static FontLoader fontLoader;

    public void start() {
        instance = this;
        final StringJoiner authors = new StringJoiner(", ");
        for (String author : AUTHORS) {
            authors.add(author);
        }

        Display.setTitle(NAME + " " + VERSION + " | by " + authors);
        ManagerThread managerThread = new ManagerThread();
        managerThread.start();
        new Thread(new MainThread()).start();
        new ShaderManager();
        fontLoader = new FontLoader();
    }

}
