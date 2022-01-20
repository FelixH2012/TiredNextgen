package me.felix.tired.modification.visual;

import me.felix.tired.bridge.rendering.clickgui.ClickGUIRenderer;
import me.felix.tired.main.Main;
import org.lwjgl.input.Keyboard;
import tired.jdk.api.Tired;
import tired.jdk.api.abstracts.Module;

@Module.Info(name = "ClickGUI", category = Module.Category.VISUAL, defaultKey = Keyboard.KEY_RSHIFT)
public class ClickGUI extends Module {

    private final Main main = new Main();


    @Override
    public void onEnable() {

        MC.displayGuiScreen(Tired.INSTANCE.getClickGUIRenderer());

    }

    @Override
    public void onDisable() {

    }
}
