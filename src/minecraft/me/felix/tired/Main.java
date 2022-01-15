package me.felix.tired;

/*
Tired is a Multithreading Minecraft modification which provides a good gaming experience
@Tired-Client.de©
*/

import me.felix.tired.threadding.MainThread;

public class Main {

    public static final String CLIENT_NAME = "Tired";

    public void start() {
        Thread mainThread = new Thread(new MainThread());
        mainThread.start();
    }

}
