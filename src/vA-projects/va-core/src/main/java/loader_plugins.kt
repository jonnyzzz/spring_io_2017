package plugin.extensions.core

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

abstract class PluginLoader(val name: String) : InitializingBean {
  @Autowired lateinit var parentContext : ApplicationContext
  var context = null as AnnotationConfigApplicationContext?

  protected inline fun <reified T> getPluginBean()
          = context?.getBean(T::class.java)?: error("No bean")

  override fun afterPropertiesSet() {
    val context = AnnotationConfigApplicationContext()
    this.context = context
    context.parent = parentContext
    context.displayName = "plugin: $name"
    context.scan("plugin.extensions." + name)
    context.refresh()
  }
}
