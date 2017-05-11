package plugin.extensions.plugin_2

import com.google.common.collect.ImmutableList
import org.springframework.stereotype.Component

@Component
class Plugin2Component {
  init {
    println("Plugin 2 Component")

    val clazz = ImmutableList::class.java
    val guava = clazz.getResource("/" + clazz.name.replace(".", "/") + ".class").toString()

    when {
      guava.contains("guava-21.0.jar") -> println("Plugin 2 Component: Guava 21(!) URL " + guava)
      guava.contains("guava-14.0.jar") -> println("Plugin 2 Component: Guava 14    URL " + guava)
      else ->                             println("Plugin 2 Component: Guava ???   URL " + guava)
    }
  }
}
