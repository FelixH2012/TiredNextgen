package me.felix.tired.threads;

import me.felix.tired.bridge.Managers;
import me.felix.tired.bridge.event.EventManager;
import me.felix.tired.bridge.managers.ModuleManager;
import me.felix.tired.api.Tired;

import java.lang.reflect.Field;

public class ManagerThread extends Thread {

    @Override
    public void run() {
        addManager(new ModuleManager());
        addManager(new EventManager());
        super.run();
    }

    private void addManager(Object manager) {
        Class<? extends Managers> clazz = Tired.getTired().getManagers().getClass();
        for(Field field : clazz.getDeclaredFields()) {
            if(field.getName().equalsIgnoreCase(manager.getClass().getSimpleName())) {
                field.setAccessible(true);
                try {
                    field.set(Tired.getTired().getManagers(), manager);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
