package plugin.extensions.core

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import plugin.extensions.Extension
import java.io.File
import java.net.URLClassLoader

abstract class PluginLoader(
        val name: String
) : InitializingBean {
  @Autowired lateinit var registry: ExtensionRegistry
  @Autowired lateinit var parentContext : ApplicationContext

  @PublishedApi
  internal var context = null as AnnotationConfigApplicationContext?

  override fun afterPropertiesSet() {
    println("PluginLoader: loading plugin $name...")

    val classpath = System.getenv("classpath_$name")
    println("$name Classpath: $classpath")

    val ucp = classpath.split(File.pathSeparatorChar).map { File(it).toURI().toURL() }

    val classloader = URLClassLoader(ucp.toTypedArray(), javaClass.classLoader)

    val context = AnnotationConfigApplicationContext()
    this.context = context

    context.parent = parentContext
    context.displayName = "plugin: $name"
    context.classLoader = classloader
    context.scan(Extension::class.java.`package`.name + "." + name)
    context.refresh()

    context.getBeansOfType(Extension::class.java)
            .values
            .forEach {
              registry.register(it)
            }

  }

  protected inline fun <reified T> getPluginBean()
          = context!!.getBean(T::class.java)!!
}

