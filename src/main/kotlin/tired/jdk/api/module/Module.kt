package tired.jdk.api.module

import tired.jdk.api.Tired
import tired.jdk.api.event.Listener

abstract class Module : Listener {

    val name: String
    val category: Category
    var key: Int

    private var toggled = false

    init {
        val info = this.javaClass.getAnnotation(Info::class.java)
        this.name = info.name
        this.category = info.category
        this.key = info.defaultKey
    }

    fun isToggled() : Boolean {
        return toggled
    }

    fun setState(state: Boolean) {
        toggled = state
        if(state) {
            Tired.addListener(this.javaClass)
            onEnable()
        } else {
            Tired.removeListener(this.javaClass)
            onDisable()
        }
    }

    abstract fun onEnable()
    abstract fun onDisable()

    annotation class Info(val name: String, val category: Category, val defaultKey: Int = -1)

    enum class Category(val displayName: String) {
        COMBAT("Combat"), MOVEMENT("Movement"), VISUAL("Visual"), WORLD("World"), PLAYER("Player"), MISC("Misc")
    }
}