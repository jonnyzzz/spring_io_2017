package plugin.extensions.core

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.event.ContextStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class PluginLoader {
  init {
    println("I'm plugin loader")
  }

  @EventListener
  fun onContextStarted(e: ContextStartedEvent) {
    println("PluginLoader: loading plugins...")

    for (name in listOf("plugin_1", "plugin_2")) {
      val pluginContext = AnnotationConfigApplicationContext()
      pluginContext.parent = e.applicationContext
      pluginContext.displayName = "plugin: $name"
      pluginContext.scan("plugin.extensions." + name)
      pluginContext.refresh()
    }
  }
}
