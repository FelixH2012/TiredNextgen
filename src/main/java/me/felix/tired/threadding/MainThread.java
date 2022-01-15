package me.felix.tired.threadding;

import me.felix.tired.Main;
import me.felix.tired.logger.Logger;

public class MainThread extends Thread {

    @Override
    public void run() {
        Logger.doLog("Initialized " + Main.CLIENT_NAME, Logger.LoggingType.CONSOLE);
        super.run();
    }
}
