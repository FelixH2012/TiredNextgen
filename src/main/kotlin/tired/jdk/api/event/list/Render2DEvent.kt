package tired.jdk.api.event.list

import net.minecraft.client.gui.ScaledResolution
import tired.jdk.api.event.Event

class Render2DEvent(val scaledResolution: ScaledResolution, val partialTicks: Float) : Event() {
}