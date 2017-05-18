package plugin.extensions.plugin_2

import com.google.common.collect.ImmutableList
import org.springframework.stereotype.Component
import plugin.extensions.ServiceFromThePlugin2


@Component
class ServiceFromThePlugin2Impl : ServiceFromThePlugin2 {
  init {
    println("Plugin 2 Exported")
  }

  override fun test() : List<String> {
    println("Plugin 2 #test()")

    return ImmutableList.of("X", "Y", "Z")
  }
}



@Component
class PluginGuavaVersion {
  init {
    println()
    val clazz = ImmutableList::class.java
    val guava = clazz.getResource("/${clazz.name.replace(".", "/")}.class").toString()

    when {
      guava.contains("guava-21.0.jar") -> println("Plugin 2 Guava 21(!) URL $guava")
      guava.contains("guava-14.0.jar") -> println("Plugin 2 Guava 14    URL $guava")
      else ->                             println("Plugin 2 Guava ???   URL $guava")
    }
    println()
  }
}
