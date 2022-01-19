package me.felix.tired.bridge.font;

import net.minecraft.util.ResourceLocation;
import tired.jdk.api.Logger;
import tired.jdk.intern.hooks.MCHook;

import java.awt.*;
import java.io.InputStream;
import java.util.HashMap;

public class FontLoader implements MCHook {

    private final HashMap<String, HashMap<Float, FontRendering>> fonts = new HashMap<>();

    public FontRendering bebas = this.getFont("BebasNeue-Regular", 25.0f);
    public FontRendering bold = this.getFont("BoldF", 25.0f);

    public FontRendering getFont(String name, float size) {
        FontRendering unicodeFont = null;
        try {
            if (this.fonts.containsKey(name) && this.fonts.get(name).containsKey(size)) {
                return this.fonts.get(name).get(size);
            }
            Logger.INSTANCE.doLog("" + ClassLoader.getSystemClassLoader().getResourceAsStream("resTired/BebasNeue-Regular.ttf"), Logger.Type.CONSOLE);
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("resTired/" + name + ".ttf");
            Font font;
            assert inputStream != null;
            font = Font.createFont(0, inputStream);
            unicodeFont = new FontRendering(font.deriveFont(size), true, true);
            HashMap<Float, FontRendering> map = new HashMap<>();
            if (this.fonts.containsKey(name)) {
                map.putAll(fonts.get(name));
            }
            map.put(size, unicodeFont);
            this.fonts.put(name, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unicodeFont;
    }

}
