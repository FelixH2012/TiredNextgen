package me.felix.tired.modification.list.movement;

import org.lwjgl.input.Keyboard;
import tired.jdk.api.abstracts.Module;
import tired.jdk.api.event.EventTargeto;
import tired.jdk.api.event.list.UpdateEvent;

@Module.Info(name = "Speed", category = Module.Category.MOVEMENT, defaultKey = Keyboard.KEY_G)
public class Speed extends Module {

    @EventTargeto
    public void onUpdate(UpdateEvent e) {
        if (MC.thePlayer.onGround) {
            MC.thePlayer.jump();
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
