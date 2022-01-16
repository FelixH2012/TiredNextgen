package me.felix.tired.modification.list.movement;

import me.felix.tired.bridge.event.EventTargeto;
import me.felix.tired.bridge.event.list.RotationEvent;
import me.felix.tired.bridge.event.list.UpdateEvent;
import me.felix.tired.modification.Module;
import me.felix.tired.modification.ModuleCategory;
import org.lwjgl.input.Keyboard;

@Module.Info(name = "Sprint", category = ModuleCategory.MOVEMENT, defaultKey = Keyboard.KEY_SPACE)
public class Sprint extends Module {

    @EventTargeto
    public void onUpdate(UpdateEvent event) {
        player.setSprinting(true);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
