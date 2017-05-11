package plugin.extensions.v5.plugin_2

import org.springframework.stereotype.Component
import plugin.extensions.v5.Extension

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
