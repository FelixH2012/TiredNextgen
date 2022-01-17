package tired.jdk.intern.hooks

import me.felix.tired.bridge.font.FontRendering
import java.awt.Font

interface FontHook {
    val fontRenderer: FontRendering get() = FontRendering(Font("arial", Font.PLAIN, 23), true, true)
}