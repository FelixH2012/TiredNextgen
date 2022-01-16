package me.felix.tired.logger;

import lombok.experimental.UtilityClass;
import me.felix.tired.main.Main;
import me.felix.tired.bridge.MCHook;
import net.minecraft.util.ChatComponentText;

@UtilityClass
public class Logger implements MCHook {

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_BLUE = "\u001B[34m";

    public enum LoggingType {
        CONSOLE,
        PRIVATE_CHAT,
        INGAME_CHAT
    }

    public void doLog(String text, LoggingType loggingType) {
        switch (loggingType) {
            case CONSOLE:
                System.out.println("[" + ANSI_BLUE + Main.NAME + ANSI_RESET + "] " + text);
                break;
            case PRIVATE_CHAT:
                MC.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("§8[" + "§c" + Main.NAME + "§8] §f" + text));
                break;
            case INGAME_CHAT:
                player.sendChatMessage(text);
                break;
        }
    }

}
