package tired.jdk.api

import tired.jdk.api.event.Listener
import tired.jdk.api.module.Module
import tired.jdk.intern.Plugin

object Tired {

    private var listeners = ArrayList<Class<out Listener>>()
    private val modules = ArrayList<Module>()
    private val plugins = ArrayList<Plugin>()

    fun addModule(module: Module) {
        modules.add(module)
    }

    fun addListener(listener: Class<out Listener>) {
        if(!listeners.contains(listener))
            listeners.add(listener)
    }

    fun removeListener(listener: Class<out Listener>) {
        listeners.remove(listener)
    }
}