package me.felix.tired.bridge.setting;


import me.felix.tired.api.Tired;
import me.felix.tired.modification.Module;

import java.util.function.Supplier;

public class Setting {

    private final String name;
    private final Module parent;
    private final Supplier<Boolean> dependency;

    public Setting(String name, Module parent, Supplier<Boolean> dependency) {
        this.name = name;
        this.parent = parent;
        this.dependency = dependency;
        Tired.getTired().getManagers().getSettingsManager().rSetting(this);
    }


    public Setting(String name, Module parent, Supplier<Boolean> dependency, int HUE) {
        this.name = name;
        this.parent = parent;
        this.dependency = dependency;
        Tired.getTired().getManagers().getSettingsManager().rSetting(this);
    }

    public Object getValue() {
        return null;
    }

    public String getName() {
        return this.name;
    }

    public Module getModule() {
        return this.parent;
    }

    public boolean isVisible() {
        if (dependency != null) {
            return dependency.get();
        }
        return true;
    }
}