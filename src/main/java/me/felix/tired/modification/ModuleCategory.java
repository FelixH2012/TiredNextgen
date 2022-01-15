package me.felix.tired.modification;

import lombok.Getter;

public enum ModuleCategory {

    COMBAT("Combat"), MOVEMENT("Movement"), WORLD("World"), VISUAL("Visual"), MISC("Misc");

    @Getter
    private final String cleanName;

    ModuleCategory(String cleanName) {
        this.cleanName = cleanName;
    }

}
