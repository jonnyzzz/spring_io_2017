package plugin.extensions.core

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.event.ContextStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import plugin.extensions.Extension

@Component
class PluginLoader(
        val registry: ExtensionRegistry
) {
  init {
    println("I'm plugin loader")
  }

  @EventListener
  fun onContextStarted(e: ContextStartedEvent) {
    println("PluginLoader: loading plugins...")

    val parentContext = e.applicationContext
    for (name in listOf("plugin_1", "plugin_2")) {
      val context = AnnotationConfigApplicationContext()
      context.parent = parentContext
      context.displayName = "plugin: $name"
      context.scan("plugin.extensions.$name")
      context.refresh()

      context.getBeansOfType(Extension::class.java)
              .values
              .forEach {
                registry.register(it)
              }
    }
  }
}
