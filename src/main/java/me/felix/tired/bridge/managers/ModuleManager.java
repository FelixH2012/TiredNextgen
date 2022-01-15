package me.felix.tired.bridge.managers;

import lombok.Getter;
import me.felix.tired.modification.Module;
import java.util.ArrayList;

public class ModuleManager {

    @Getter
    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
    }
}
