package me.felix.tired.bridge.setting.settings;

import lombok.Getter;
import lombok.Setter;
import me.felix.tired.bridge.setting.Setting;
import me.felix.tired.modification.Module;

import java.util.function.Supplier;

@Getter
@Setter
public class NumberSetting extends Setting {

    private double value;
    private double min;
    private double max;
    private double inc;
    public boolean dragged = false;

    public NumberSetting(String name, Module parent, double defaultValue, double min, double max, double inc, Supplier<Boolean> dependency) {
        super(name, parent, dependency);
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.inc = inc;
    }

    public NumberSetting(String name, Module parent, double defaultValue, double min, double max, double inc) {
        this(name, parent, defaultValue, min, max, inc, () -> true);
    }

    @Override
    public Double getValue() {
        return this.value;
    }

}