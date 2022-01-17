package me.felix.tired.modification.list.movement;

import org.lwjgl.input.Keyboard;
import tired.jdk.api.event.EventTargeto;
import tired.jdk.api.event.list.UpdateEvent;
import tired.jdk.api.module.Module;

@Module.Info(name = "Sprint", category = Module.Category.MOVEMENT, defaultKey = Keyboard.KEY_SPACE)
public class Sprint extends Module {

    @EventTargeto
    public void onUpdate(UpdateEvent event) {
        getPlayer().setSprinting(true);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
