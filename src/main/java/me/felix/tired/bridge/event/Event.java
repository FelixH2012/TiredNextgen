package me.felix.tired.bridge.event;

import me.felix.tired.api.Tired;

import java.lang.reflect.Method;

public class Event {
    public boolean canceled;
    //private final ArrayList<Parameter> parameters = new ArrayList<>();

    public Event() {
    }

    public <T extends Event> T execute() {
        Tired.getTired().getManagers().getEventManager().eventClasses.forEach(listenerClass -> {
            for (Method method : listenerClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(EventTargeto.class)) {
                    if (method.getParameterCount() == 1) {
                        if (method.getParameters()[0].getType() == this.getClass()) {
                            try {
                                method.setAccessible(true);
                                method.invoke(method.getDeclaringClass().newInstance(), this);
                            } catch (Exception ignored) {}
                        }
                        /*for(int i = 0; i < method.getParameters().length; i++) {
                        parameters.clear();
                        final Parameter parameter = method.getParameters()[i];
                        assert false;
                        if(parameter.getType().isInstance(this)) {
                            parameters.add(i,parameter);
                        } else {
                            parameters.add(i,null);
                        }
                    }*/
                    }
                }
            }
        });
        return (T) this;
    }
}
