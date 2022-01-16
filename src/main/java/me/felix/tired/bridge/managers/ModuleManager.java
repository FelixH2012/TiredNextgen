package me.felix.tired.bridge.managers;

import lombok.Getter;
import me.felix.tired.modification.Module;
import org.reflections.Reflections;

import java.util.ArrayList;

public class ModuleManager {

    @Getter
    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        final Reflections reflections = new Reflections("me.felix.tired");
        reflections.getTypesAnnotatedWith(Module.Info.class).forEach(clazz -> {
            try {
                modules.add((Module)clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
