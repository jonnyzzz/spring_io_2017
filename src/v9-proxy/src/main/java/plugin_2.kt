package plugin.extensions.plugin_2

import org.springframework.stereotype.Component
import plugin.extensions.Extension
import plugin.extensions.ServiceFromThePlugin
import plugin.extensions.ServiceFromThePlugin2

@Component
class Plugin2Component(
        service: ServiceFromThePlugin
) {
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
class ServiceFromThePlugin2Impl(
        s : ServiceFromThePlugin
) : ServiceFromThePlugin2 {

  init {
    println("Plugin 2 Stack")
    Thread.currentThread().stackTrace.drop(1).forEach {
      println("  ${it.className}.${it.methodName}")
    }
    println()
  }

  override fun test() {
    //
  }
}
