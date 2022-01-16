package me.felix.tired.bridge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public interface MCHook {
    Minecraft MC = Minecraft.getMinecraft();
    EntityPlayerSP player = MC.thePlayer;
}
