package plugin.extensions.core

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.io.File
import java.net.URLClassLoader

abstract class PluginLoader(
        val name: String
) : InitializingBean {
  @Autowired lateinit var parentContext : ApplicationContext
  lateinit var pluginContext: AnnotationConfigApplicationContext

  override fun afterPropertiesSet() {
    println("PluginLoader: loading plugin $name...")

    val classpath = System.getenv("classpath_$name")
    println("$name Classpath: $classpath")

    val ucp = classpath.split(File.pathSeparatorChar).map { File(it).toURI().toURL() }

    val classloader = URLClassLoader(ucp.toTypedArray(), javaClass.classLoader)

    pluginContext = AnnotationConfigApplicationContext()
    pluginContext.parent = parentContext
    pluginContext.displayName = "plugin: $name"
    pluginContext.classLoader = classloader
    pluginContext.scan("plugin.extensions.$name")
    pluginContext.refresh()
  }
}

