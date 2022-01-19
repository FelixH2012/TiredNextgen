package me.felix.tired.modification.visual;

import me.felix.tired.bridge.util.BlurHelper;
import net.minecraft.client.gui.Gui;
import tired.jdk.api.Tired;
import tired.jdk.api.event.EventTargeto;
import tired.jdk.api.abstracts.Module;
import tired.jdk.api.event.list.Render2DEvent;
import tired.jdk.intern.hooks.FontHook;

@Module.Info(name = "HUD", category = Module.Category.MISC)
public class HUD extends Module implements FontHook {

    @EventTargeto
    public void onRender(Render2DEvent e) {
        renderArray(false);
        BlurHelper.startBlur();
        renderArray(true);
        BlurHelper.stopBlur();
        renderArray(false);
    }

    private void renderArray(final boolean rectangle) {
        int index = 3;
        for (Module module : Tired.INSTANCE.getModules()) {
            if (module.isToggled()) {
                if (rectangle) {
                    Gui.drawRect(0, index, fontRenderer.getStringWidth(module.getName()) + 6, 11 + index, Integer.MIN_VALUE);
                }
                fontRenderer.drawString(module.getName(), 3, index, -1);
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
