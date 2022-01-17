package tired.jdk.intern.hooks;

import me.felix.tired.bridge.font.FontRendering;

import java.awt.*;

public interface FontHook {
    FontRendering fontRenderer = new FontRendering(new Font("arial", Font.PLAIN, 23), true, true);
}
