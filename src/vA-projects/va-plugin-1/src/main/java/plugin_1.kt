package plugin.extensions.plugin_1

import org.springframework.stereotype.Component
import plugin.extensions.CoreService
import plugin.extensions.ServiceFromThePlugin1

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
class ServiceFromThePlugin1Impl : ServiceFromThePlugin1 {
  override fun test() {
  }
  init {
    println("Plugin 1 Exported")
  }
}
