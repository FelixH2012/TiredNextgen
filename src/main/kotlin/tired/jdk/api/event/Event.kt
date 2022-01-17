package tired.jdk.api.event

import tired.jdk.api.Tired

open class Event {
    var canceled = false

    fun <T : Event> execute(): T {
        Tired.getTired().listeners.forEach {
            for (method in it.declaredMethods) {
                if (method.isAnnotationPresent(EventTargeto::class.java)) {
                    if(method.parameterCount == 1) {
                        if(method.parameters[0].type == this::class.java) {
                            try {
                                method.isAccessible = true
                                method.invoke(method.declaringClass.newInstance(), this)
                            } catch (ignored: Exception) { }
                        }
                    }
                }
            }
        }
        return this as T
    }
}