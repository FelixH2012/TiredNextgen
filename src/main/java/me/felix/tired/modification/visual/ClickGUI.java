package me.felix.tired.modification.visual;

import me.felix.tired.main.Main;
import org.lwjgl.input.Keyboard;
import tired.jdk.api.abstracts.Module;

@Module.Info(name = "ClickGUI", category = Module.Category.VISUAL, defaultKey = Keyboard.KEY_RSHIFT)
public class ClickGUI extends Module {


    @Override
    public void onEnable() {

        MC.displayGuiScreen(Main.instance.clickGUIRenderer);

        setState(false);

    }

    @Override
    public void onDisable() {

    }
}
