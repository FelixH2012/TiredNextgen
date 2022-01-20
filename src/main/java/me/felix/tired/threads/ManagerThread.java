package me.felix.tired.threads;

import me.felix.tired.bridge.rendering.clickgui.ClickGUIRenderer;
import me.felix.tired.main.Main;
import org.reflections.Reflections;
import tired.jdk.api.Tired;
import tired.jdk.api.abstracts.Module;

public class ManagerThread extends Thread {

    @Override
    public void run() {
        final Reflections reflections = new Reflections("me.felix.tired");

        /* Module */
        reflections.getTypesAnnotatedWith(Module.Info.class).forEach(clazz -> {
            try {
                Tired.INSTANCE.addModule((Module)clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        Main.instance.clickGUIRenderer = new ClickGUIRenderer();
        Main.instance.clickGUIRenderer.add();
        super.run();
    }
}
