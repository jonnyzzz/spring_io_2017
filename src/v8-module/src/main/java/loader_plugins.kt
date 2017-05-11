package plugin.extensions.core

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component
import plugin.extensions.Extension

abstract class PluginLoader(
        val name: String
) : InitializingBean {
  @Autowired lateinit var registry: ExtensionRegistry
  @Autowired lateinit var parentContext : ApplicationContext

  override fun afterPropertiesSet() {
    println("PluginLoader: loading plugin $name...")

    val context = AnnotationConfigApplicationContext()
    context.parent = parentContext
    context.displayName = "plugin: $name"
    context.scan(Extension::class.java.`package`.name + "." + name)
    context.refresh()

    context.getBeansOfType(Extension::class.java)
            .values
            .forEach {
              registry.register(it)
            }
  }
}

@Component
class Plugin_1Loader : PluginLoader("plugin_1")

@Component
class Plugin_2Loader : PluginLoader("plugin_2")
