package me.felix.tired.bridge.rendering.clickgui.layers;

import lombok.Getter;

/**
 * Falls du es nicht verstehst kroko, ich rendere mit einem system was ich halt jz "layer" system nenne, das heißt, wir haben
 * mehrere ebenen des clickguis, worauf ich dann jewalig blur einzelnd usw rendern kann.
 */

public class Layer {

    public enum Layers {
        LAYER1, LAYER2
    }

    @Getter
    private Layers layers;

    public void updateLayer(int mouseX, int mouseY) {
    }

    public void renderLayer1() {
    }

    public void renderLayer2() {}

    public void renderLayers(Layers layer, int mouseX, int mouseY) {
        this.layers = layer;

        updateLayer(mouseX, mouseY);

        switch (layer) {
            case LAYER1:
                renderLayer1();
                break;
            case LAYER2:
                renderLayer2();
                break;
        }

    }

}
