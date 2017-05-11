package plugin.extensions.plugin_1

import com.google.common.collect.ImmutableList
import org.springframework.stereotype.Component
import plugin.extensions.CoreService
import plugin.extensions.ServiceFromThePlugin1

@Component
class Plugin1Component {
  init {
    println("Plugin 1 Component")
  }
}

@Component
class Plugin1Service(
        core: CoreService,
        bean: Plugin1Component
) {
  init {
    println("Plugin 1 Service")

    //call common component
    //TODO: Not from constructor, please!
    core.`loren ipsum`()
  }
}


@Component
class ServiceFromThePlugin1Impl : ServiceFromThePlugin1 {
  override fun test() {
  }
  init {
    println("Plugin 1 Exported")
  }
}


@Component
class Plugin1Guava {
  init {
    println("Plugin 1 Guava")
    try {
      //execute Guava 21 API
      ImmutableList.sortedCopyOf(listOf("a","b","c"))
    } catch (t : Throwable) {
      println("Plugin 1 Component: No Guava 21")
    }
  }
}
