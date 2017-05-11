package plugin.extensions.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.event.ContextStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import plugin.extensions.Extension
import plugin.extensions.ServiceFromThePlugin
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Proxy

abstract class PluginLoader(
        val name: String
) {
  @Autowired lateinit var registry: ExtensionRegistry
  @Autowired lateinit var parentContext : ApplicationContext

  val contextInit = mutableListOf<SubContextLoaderProxyHandler>()

  @EventListener
  fun onContextStarted(e: ContextStartedEvent) {
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

    contextInit.forEach { lazy ->
      lazy.setHostInstance(context.getBean(lazy.beanClazz)!!)
    }
  }

  protected inline fun <reified T> getPluginBean() : T {
    val host = SubContextLoaderProxyHandler(T::class.java)

    contextInit += host

    return Proxy.newProxyInstance(javaClass.classLoader,
            arrayOf(T::class.java), host
    ) as T
  }
}

class SubContextLoaderProxyHandler(
        val beanClazz: Class<*>) : InvocationHandler {
  private var myHost : Any? = null

  fun setHostInstance(o: Any) {
    myHost = beanClazz.cast(o)
  }

  @Throws(Throwable::class)
  override fun invoke(proxy: Any,
                      method: Method,
                      args: Array<Any>?): Any {
    if (method.declaringClass == Any::class.java) {
      return method.invoke(this, *(args ?: arrayOf()))
    }

    myHost ?: throw Error("BeanInCreation")
    if (myHost === proxy) {
      throw Error("Component $beanClazz is not implemented")
    }

    try {
      return method.invoke(myHost!!, *(args ?: arrayOf()) )
    } catch (e: InvocationTargetException) {
      throw e.cause!!
    }
  }
}

@Component
class Plugin_1Loader : PluginLoader("plugin_1") {
  @Bean
  fun getSharedBean(): ServiceFromThePlugin
          = getPluginBean()
}

@Component
class Plugin_2Loader : PluginLoader("plugin_2")
