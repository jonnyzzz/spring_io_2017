package plugin.extensions.v5.plugin_1

import org.springframework.stereotype.Component
import plugin.extensions.v5.CoreService
import plugin.extensions.v5.Extension

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
) : Extension {
  init {
    println("Plugin 1 Service")

    //call common component
    //TODO: Not from constructor, please!
    core.`loren ipsum`()
  }
}
