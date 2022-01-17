package tired.jdk.intern

import java.io.File
import java.io.FileNotFoundException

data class Plugin(val name: String, val version: String, val main: String, val authors: ArrayList<String>, val file: File) {
    fun load() {
        if(file.exists()) {

        } else {
            throw FileNotFoundException(file.name)
        }
    }
}