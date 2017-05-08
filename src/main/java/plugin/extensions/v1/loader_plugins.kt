package plugin.extensions.v1.core

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.event.ContextStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import plugin.extensions.v1.ExtensionRegistry

@Component
class PluginLoader(
        val detector: PluginDetector
) {
  init {
    println("I'm plugin loader")
  }

  @EventListener
  fun onContextStarted(e: ContextStartedEvent) {
    println("PluginLoader: loading plugins...")

    detector.detectPlugins().forEach {
      loadPlugins(e.applicationContext, it)
    }
  }

  private fun loadPlugins(parent: ApplicationContext, name: String) {
    val context = AnnotationConfigApplicationContext()
    context.parent = parent
    context.displayName = "plugin: $name"

    context.scan(ExtensionRegistry::class.java.`package`.name + "." + name)

    context.refresh()
  }
}

@Component
class PluginDetector {
  fun detectPlugins() = listOf("plugin_1", "plugin_2")
}
