package me.felix.tired.modification;

import lombok.Getter;
import lombok.Setter;
import me.felix.tired.api.Tired;
import me.felix.tired.bridge.event.Listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Getter
@Setter
public abstract class Module implements Listener {

    private final String name;
    private final ModuleCategory category;
    private int key;

    boolean toggled;

    public Module() {
        final Info info = getClass().getAnnotation(Info.class);
        this.name = info.name();
        this.category = info.category();
        this.key = info.defaultKey();
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean state) {
        this.toggled = state;
        if (state) {
            onEnable();
            Tired.addListener(this.getClass());
        } else {
            onDisable();
            Tired.removeListener(this.getClass());
        }
    }

    public abstract void onEnable();

    public abstract void onDisable();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Info {
        String name();

        ModuleCategory category();

        int defaultKey() default -1;
    }
}
