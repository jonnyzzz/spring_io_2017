package plugin.extensions.plugin_1

import org.springframework.stereotype.Component
import plugin.extensions.CoreService
import plugin.extensions.ServiceFromThePlugin1
import plugin.extensions.ServiceFromThePlugin2

@Component
class Plugin1Component(
        s : ServiceFromThePlugin2
) {
  init {
    println("Plugin 1 Component")
  }
}

@Component
class Plugin1Service(
        core: CoreService,
        bean: Plugin1Component
) {
  init {
    println("Plugin 1 Service")

    //call common component
    //TODO: Not from constructor, please!
    core.service()
  }
}

@Component
class ServiceFromThePluginImpl : ServiceFromThePlugin1 {
  init {
    println("Plugin 1 Create")
    Thread.currentThread().stackTrace.drop(1).take(5).forEach {
      println("  ${it.className}.${it.methodName}")
    }
    println()
  }

  override fun test() {
    println("Plugin 1 #test()")
  }
}
