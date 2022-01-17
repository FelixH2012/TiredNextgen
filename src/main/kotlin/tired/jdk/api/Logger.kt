package tired.jdk.api

import me.felix.tired.logger.Logger.LoggingType
import me.felix.tired.main.Main
import net.minecraft.util.ChatComponentText

object Logger : MCHook {
    private const val ANSI_RESET = "\u001B[0m"
    private const val ANSI_BLUE = "\u001B[34m"

    enum class Type {
        CONSOLE, PRIVATE_CHAT, INGAME_CHAT
    }

    fun doLog(text: String, loggingType: Type) {
        when (loggingType) {
            Type.CONSOLE -> println("[" + ANSI_BLUE + Main.NAME + ANSI_RESET + "] " + text)
            Type.PRIVATE_CHAT -> MC.ingameGUI.chatGUI
                .printChatMessage(ChatComponentText("§8[" + "§c" + Main.NAME + "§8] §f" + text))
            Type.INGAME_CHAT -> player.sendChatMessage(text)
        }
    }
}