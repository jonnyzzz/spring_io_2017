package plugin.extensions.plugin_2

import org.springframework.stereotype.Component
import plugin.extensions.Extension
import plugin.extensions.ExtensionRegistry

@Component
class Plugin2Component {
  init {
    println("Plugin 2 Component")
  }
}

@Component
class Extension2Impl : Extension {
  init {
    println("Plugin 2 Extension 2")
  }
}

@Component
class Extension1Impl(
        registry: ExtensionRegistry
) : Extension {
  init {
    registry.register(this)

    println("Plugin 2 Extension 2+Reg")
  }
}
