package tired.jdk.intern.hooks;

import me.felix.tired.bridge.font.FontRendering;
import me.felix.tired.main.Main;
import me.felix.tired.main.ThreadGetter;
import me.felix.tired.threads.MainThread;
import tired.jdk.api.Tired;

import java.awt.*;

public interface FontHook {
    FontRendering fontRenderer = new FontRendering(new Font("arial", Font.PLAIN, 23), true, true);

}
