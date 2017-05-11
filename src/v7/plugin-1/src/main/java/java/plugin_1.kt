package plugin.extensions.plugin_1

import com.google.common.collect.ImmutableList
import org.springframework.stereotype.Component

@Component
class Plugin1Component {
  init {

    println("Plugin 1 Component")

    try {
      //execute Guava 21 API
      ImmutableList.sortedCopyOf(listOf("a","b","c"))
    } catch (t : Throwable) {
      println("Plugin 1 Component: No Guava 21")
    }
  }
}
