package plugin.extensions.plugin_2

import org.springframework.stereotype.Component
import plugin.extensions.Extension
import plugin.extensions.ServiceFromThePlugin

@Component
class Plugin2Component(
        service: ServiceFromThePlugin
) {
  init {
    println("Plugin 2 Component")

    service.test()
  }
}

@Component
class Extension2Impl : Extension {
  init {
    println("Plugin 2 Extension 2")
  }
}
