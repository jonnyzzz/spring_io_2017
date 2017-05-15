package plugin.extensions.plugin_1

import com.google.common.collect.ImmutableList
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


@Component
class PluginGuavaVersion {
  init {
    println()

    val clazz = ImmutableList::class.java
    val guava = clazz.getResource("/" + clazz.name.replace(".", "/") + ".class").toString()

    when {
      guava.contains("guava-21.0.jar") -> println("Plugin 1 Guava 21(!) URL " + guava)
      guava.contains("guava-14.0.jar") -> println("Plugin 1 Guava 14    URL " + guava)
      else ->                             println("Plugin 1 Guava ???   URL " + guava)
    }

    println()
  }
}
