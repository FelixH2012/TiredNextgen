package tired.jdk.api

import me.felix.tired.main.Main
import net.minecraft.util.ChatComponentText
import tired.jdk.intern.hooks.MCHook

object Logger : MCHook {
    private const val ANSI_RESET = "\u001B[0m"
    private const val ANSI_BLUE = "\u001B[34m"

    enum class Type {
        CONSOLE, PRIVATE_CHAT, INGAME_CHAT
    }

    fun doLog(text: String, loggingType: Type) {
        when (loggingType) {
            Type.CONSOLE -> println("[" + ANSI_BLUE + Main.NAME + ANSI_RESET + "] " + text)
            Type.PRIVATE_CHAT -> mc.ingameGUI.chatGUI
                .printChatMessage(ChatComponentText("§8[" + "§c" + Main.NAME + "§8] §f" + text))
            Type.INGAME_CHAT -> player.sendChatMessage(text)
        }
    }
}