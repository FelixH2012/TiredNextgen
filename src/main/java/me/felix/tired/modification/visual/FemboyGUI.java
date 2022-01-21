package me.felix.tired.modification.visual;

import me.felix.tired.bridge.femboydrawer.Drawable;
import tired.jdk.api.abstracts.Module;

@Module.Info(name = "FemboyGUI", category = Module.Category.VISUAL)
public class FemboyGUI extends Module {



    @Override
    public void onEnable() {
        MC.displayGuiScreen(new Drawable());
    }

    @Override
    public void onDisable() {

    }
}
