package tired.jdk.api

import tired.jdk.api.event.Listener
import tired.jdk.api.abstracts.Module
import tired.jdk.intern.Plugin

object Tired {

    private var listeners = ArrayList<Class<out Listener>>()
    private val modules = ArrayList<Module>()
    private val plugins = ArrayList<Plugin>()

    fun addModule(module: Module) {
        modules.add(module)
    }

    fun getModules() : ArrayList<Module> {
        return modules
    }

    fun <T : Module?> getModule(clazz: Class<T>?): T {
        return modules.stream().filter { module: Module ->
            module.javaClass == clazz
        }.findAny().orElse(null) as T
    }

    fun <T : Module?> getModule(name: String?): T {
        return modules.stream().filter { module: Module ->
            module.name.equals(name, true)
        }.findAny().orElse(null) as T
    }

    fun getListeners() : ArrayList<Class<out Listener>> {
        return listeners
    }

    fun addListener(listener: Class<out Listener>) {
        if(!listeners.contains(listener))
            listeners.add(listener)
    }

    fun removeListener(listener: Class<out Listener>) {
        listeners.remove(listener)
    }
}