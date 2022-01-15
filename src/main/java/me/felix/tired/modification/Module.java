package me.felix.tired.modification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Module {

    public final String name = getClass().getAnnotation(Info.class).name();

    public int key = getClass().getAnnotation(Info.class).key();

    public final ModuleCategory moduleCategory = getClass().getAnnotation(Info.class).category();

    private boolean state;

    public abstract void onState();

    public abstract void onUndo();

    public void executeMod() {
        if (!state) {
            doEvent();
            setState(true);
            return;
        }
        undoEvent();
        setState(false);
    }

    public void doEvent() {
        //   EventManager.register(this);
        this.onState();
    }

    public void undoEvent() {
        //EventManager.unregister(this);
        this.onUndo();
    }

}
