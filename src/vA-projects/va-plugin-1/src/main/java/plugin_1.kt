package plugin.extensions.plugin_1

import org.springframework.stereotype.Component
import plugin.extensions.ServiceFromThePlugin1

@Component
class ServiceFromThePlugin1Impl : ServiceFromThePlugin1 {
  init {
    println("Plugin 1 Exported")
  }

  override fun test() {
    println("Plugin 1 #test()")
  }
}
