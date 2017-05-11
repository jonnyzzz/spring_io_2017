package plugin.extensions.v1.core

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.event.ContextStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import plugin.extensions.v1.CoreService

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
      val context = AnnotationConfigApplicationContext()
      context.parent = e.applicationContext
      context.displayName = "plugin: $it"
      context.scan(CoreService::class.java.`package`.name + "." + it)
      context.refresh()
    }
  }
}

@Component
class PluginDetector {
  fun detectPlugins() = listOf("plugin_1", "plugin_2")
}
