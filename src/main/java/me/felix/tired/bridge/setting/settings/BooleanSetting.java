package me.felix.tired.bridge.setting.settings;

import lombok.Getter;
import lombok.Setter;
import me.felix.tired.bridge.setting.Setting;
import me.felix.tired.modification.Module;

import java.util.function.Supplier;

@Getter
@Setter
public class BooleanSetting extends Setting {

    private boolean value;

    public BooleanSetting(String name, Module parent, boolean defaultValue, Supplier<Boolean> dependency) {
        super(name, parent, dependency);
        this.value = defaultValue;
    }

    public BooleanSetting(String name, Module parent, boolean defaultValue) {
        this(name, parent, defaultValue, () -> true);
    }

    public void toggle() {
        this.value = !this.value;
    }
}