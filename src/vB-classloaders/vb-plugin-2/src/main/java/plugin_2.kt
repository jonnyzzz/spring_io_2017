package plugin.extensions.plugin_2

import org.springframework.stereotype.Component
import plugin.extensions.Extension
import plugin.extensions.ServiceFromThePlugin2

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
class ServiceFromThePlugin2Impl : ServiceFromThePlugin2 {
  override fun test() {
  }

  init {
    println("Plugin 2 Exported")
  }
}
