package me.felix.tired.modification.visual;

import tired.jdk.api.Tired;
import tired.jdk.api.event.EventTargeto;
import tired.jdk.api.abstracts.Module;
import tired.jdk.api.event.list.Render2DEvent;
import tired.jdk.intern.hooks.FontHook;

@Module.Info(name = "HUD", category = Module.Category.MISC)
public class HUD extends Module implements FontHook {

    @EventTargeto
    public void onRender(Render2DEvent e) {
        int index = 0;
        for (Module module : Tired.INSTANCE.getModules()) {
            if (module.isToggled()) {
                final double width = fontRenderer.getStringWidth(module.getName());
                fontRenderer.drawStringWithShadow(module.getName(), width, index, -1);
                index += fontRenderer.getHeight();
            }

        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
