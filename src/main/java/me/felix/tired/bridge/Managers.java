package me.felix.tired.bridge;

import lombok.Getter;
import me.felix.tired.bridge.event.EventManager;
import me.felix.tired.bridge.managers.ModuleManager;

@Getter
public class Managers {
    private ModuleManager moduleManager;
    private EventManager eventManager;
}
