package me.felix.tired.threads;

import me.felix.tired.logger.Logger;
import me.felix.tired.main.Main;

public class MainThread extends Thread {

    @Override
    public void run() {
        Logger.doLog("Initialized " + Main.NAME, Logger.LoggingType.CONSOLE);
        Logger.doLog("Tired Coded By Tired coding team, see more on: https://Tired-client.de/", Logger.LoggingType.CONSOLE);
        super.run();
    }
}
