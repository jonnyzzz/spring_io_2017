package plugin.extensions.plugin_1

import org.springframework.stereotype.Component
import plugin.extensions.CoreService
import plugin.extensions.ServiceFromThePlugin
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
    core.`loren ipsum`()
  }
}

@Component
class ServiceFromThePluginImpl : ServiceFromThePlugin {
  override fun test() {
    println("Plugin 1 Stack")
    Thread.currentThread().stackTrace.drop(1).forEach {
      println("  ${it.className}.${it.methodName}")
    }
    println()
  }
}
