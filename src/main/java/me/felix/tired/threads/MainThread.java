package me.felix.tired.threads;

import me.felix.tired.main.Main;
import me.felix.tired.logger.Logger;

public class MainThread extends Thread {

    @Override
    public void run() {
        Logger.doLog("Initialized " + Main.NAME, Logger.LoggingType.CONSOLE);
        super.run();
    }
}
