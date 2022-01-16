package me.felix.tired.bridge;

import lombok.Getter;
import me.felix.tired.bridge.event.EventManager;
import me.felix.tired.bridge.managers.ModuleManager;
import me.felix.tired.bridge.setting.SettingsManager;

@Getter
public class Managers {
    private ModuleManager moduleManager;
    private SettingsManager settingsManager;
    private EventManager eventManager;
}
