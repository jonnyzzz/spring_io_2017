package plugin.extensions.core

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.event.ContextStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import plugin.extensions.Extension
import plugin.extensions.ExtensionRegistry

@Component
class PluginLoader(
        val detector: PluginDetector,
        val registry: ExtensionRegistry
) {
  init {
    println("I'm plugin loader")
  }

  @EventListener
  fun onContextStarted(e: ContextStartedEvent) {
    println("PluginLoader: loading plugins...")

    val packages = detector.detectPlugins().map { "plugin.extensions." + it }
    val parentContext = e.applicationContext

    packages.forEach {
      val context = AnnotationConfigApplicationContext()
      context.parent = parentContext
      context.displayName = "plugin: $it"
      context.scan(it)
      context.refresh()

      context.getBeansOfType(Extension::class.java)
             .values
             .forEach {
               registry.register(it)
             }
    }
  }
}

@Component
class PluginDetector {
  fun detectPlugins() = listOf("plugin_1", "plugin_2")
}
