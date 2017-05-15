package plugin.extensions.plugin_2

import org.springframework.stereotype.Component
import plugin.extensions.ServiceFromThePlugin2

@Component
class ServiceFromThePlugin2Impl : ServiceFromThePlugin2 {
  override fun test() {
    println("Plugin 2 #test()")
  }

  init {
    println("Plugin 2 Exported")
  }
}
