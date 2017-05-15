package plugin.extensions.core

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

abstract class PluginLoader(val name: String) : InitializingBean {
  @Autowired lateinit var parentContext : ApplicationContext
  lateinit var pluginContext: AnnotationConfigApplicationContext

  override fun afterPropertiesSet() {
    pluginContext = AnnotationConfigApplicationContext()
    pluginContext.parent = parentContext
    pluginContext.displayName = "plugin: $name"
    pluginContext.scan("plugin.extensions." + name)
    pluginContext.refresh()
  }
}
