package plugin.extensions.plugin_2

import org.springframework.stereotype.Component
import plugin.extensions.Extension
import plugin.extensions.ServiceFromThePlugin1
import plugin.extensions.ServiceFromThePlugin2

@Component
class Plugin2Component(
        service: ServiceFromThePlugin1
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

@Component
class ServiceFromThePlugin2Impl(
        s : ServiceFromThePlugin1
) : ServiceFromThePlugin2 {

  init {
    println("Plugin 2 Stack")
    Thread.currentThread().stackTrace.drop(1).take(5).forEach {
      println("  ${it.className}.${it.methodName}")
    }
    println()
  }

  override fun test() {
    println("Plugin 2 #test()")
  }
}
