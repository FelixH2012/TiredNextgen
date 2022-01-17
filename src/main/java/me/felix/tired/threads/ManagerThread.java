package me.felix.tired.threads;

import org.reflections.Reflections;
import tired.jdk.api.Tired;
import tired.jdk.api.module.Module;

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
        super.run();
    }
}
