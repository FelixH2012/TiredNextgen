package tired.jdk.api

import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP

interface MCHook {
    val MC: Minecraft get() = Minecraft.getMinecraft()
    val player: EntityPlayerSP get() = MC.thePlayer
}