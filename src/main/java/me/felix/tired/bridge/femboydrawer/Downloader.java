package me.felix.tired.bridge.femboydrawer;

import tired.jdk.intern.hooks.MCHook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Downloader implements MCHook {

    public static void download(String url) throws IOException {

        InputStream in;
        try {
            in = new URL(url).openStream();

            Files.copy(in, new File("assets/minecraft/resTired", url.substring(url.length() - 35) + "-rule34.xxx___" + url.substring(url.length() - 5)).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
