package me.felix.tired.modification.list.movement;

import org.lwjgl.input.Keyboard;
import tired.jdk.api.abstracts.Module;
import tired.jdk.api.event.EventTargeto;
import tired.jdk.api.event.list.UpdateEvent;

@Module.Info(name = "Sprint", category = Module.Category.MOVEMENT, defaultKey = Keyboard.KEY_NONE)
public class Sprint extends Module {

    @EventTargeto
    public void onUpdate(UpdateEvent event) {
        MC.thePlayer.setSprinting(true);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {

    }
}
