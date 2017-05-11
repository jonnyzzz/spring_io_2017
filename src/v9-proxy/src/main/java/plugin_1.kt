package plugin.extensions.plugin_1

import org.springframework.stereotype.Component
import plugin.extensions.CoreService
import plugin.extensions.ServiceFromThePlugin

@Component
class Plugin1Component {
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
    println("Service from Plugin 1:")
    Thread.currentThread().stackTrace.drop(1).take(8).forEach {
      println("  ${it.className}.${it.methodName}")
    }
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
