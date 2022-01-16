package me.felix.tired.api;

import lombok.Getter;
import me.felix.tired.bridge.Managers;
import me.felix.tired.bridge.event.Listener;
import me.felix.tired.bridge.managers.ShaderManager;
import me.felix.tired.modification.Module;

import java.util.ArrayList;

@Getter
public class Tired {

    @Getter
    private static Tired tired;

    private final Managers managers = new Managers();
    private final ArrayList<Plugin> plugins = new ArrayList<>();
    @Getter
    private final ShaderManager shaderManager = new ShaderManager();
    public Tired() {
        tired = this;
    }

    public ArrayList<Module> getModules() {
        return tired.managers.getModuleManager().getModules();
    }

    public static void addListener(Class<? extends Listener> listener) {
        if(!tired.managers.getEventManager().eventClasses.contains(listener))
            tired.managers.getEventManager().eventClasses.add(listener);
    }

    public static void removeListener(Class<? extends Listener> listener) {
        tired.managers.getEventManager().eventClasses.remove(listener);
    }
}
