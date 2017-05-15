package plugin.extensions.plugin_1

import org.springframework.stereotype.Component
import plugin.extensions.CoreService
import plugin.extensions.ServiceFromThePlugin
import plugin.extensions.ServiceFromThePlugin2

@Component
class Plugin1Component(
        s: ServiceFromThePlugin2
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
class ServiceFromThePluginImpl : ServiceFromThePlugin {
  init {
    println("Plugin 1 Stack")
    Thread.currentThread().stackTrace.drop(1).forEach {
      println("  ${it.className}.${it.methodName}")
    }
    println()
  }

  override fun test() {
    //
  }
}


@Component
class `It Depends On Exported Service`(
        service:  ServiceFromThePlugin
)

@Component
class `It Depends On Exported Service2`(
        service:  ServiceFromThePluginImpl
)
