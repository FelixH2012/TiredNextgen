package me.felix.tired.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;

@AllArgsConstructor @Getter
public class Plugin {
    final String name, version, main;
    final String[] author;

    public void load(File file) throws IOException {
        if(file.exists()) {
            //TODO: plugin.yml read
        } else {
            throw new FileNotFoundException(file.getName());
        }
    }
}
