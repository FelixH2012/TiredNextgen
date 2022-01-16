package me.felix.tired.bridge.event;

import java.util.ArrayList;

public class EventManager {
    public final ArrayList<Class<? extends Listener>> eventClasses = new ArrayList<>();
}
