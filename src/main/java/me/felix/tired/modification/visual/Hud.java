package me.felix.tired.modification.visual;


import me.felix.tired.api.Tired;
import me.felix.tired.bridge.event.EventTargeto;
import me.felix.tired.bridge.event.list.Render2DEvent;
import me.felix.tired.bridge.hooks.FontHook;
import me.felix.tired.modification.Module;
import me.felix.tired.modification.ModuleCategory;

@Module.Info(name = "HUD", category = ModuleCategory.MISC)
public class Hud extends Module implements FontHook {

    @EventTargeto
    public void onRender(Render2DEvent e) {
        int index = 0;
        for (Module module : Tired.getTired().getModules()) {
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
