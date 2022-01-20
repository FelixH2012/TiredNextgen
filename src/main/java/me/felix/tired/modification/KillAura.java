package me.felix.tired.modification;

import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;
import tired.jdk.api.abstracts.Module;
import tired.jdk.api.event.EventTargeto;
import tired.jdk.api.event.list.UpdateEvent;

@Module.Info(name = "KillAura", category = Module.Category.COMBAT, defaultKey = Keyboard.KEY_R)
public class KillAura extends Module {

    private double range = 4;

    private Entity tar;

    @EventTargeto
    public void onUpdate(UpdateEvent e) {


    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
