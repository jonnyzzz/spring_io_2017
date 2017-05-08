package plugin.extensions.v1.core

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.stereotype.Component
import plugin.extensions.v1.ExtensionHolder
import plugin.extensions.v1.ExtensionRegistry

fun main(args: Array<String>) = RootContext().start()

class RootContext {
  fun start() {
    val root = AnnotationConfigApplicationContext()
    root.scan(RootContext::class.java.`package`.name)
    root.refresh()
    root.start()

    println()
    println("RootContext: Start Completed")
    //wait
    readLine()
  }
}

@Component
class CoreComponent {
  init {
    println("I'm core component")
  }
}

@Component
class PrivateComponent {
  init {
    println("I'm private component")
  }
}



fun loadPlugins(pluginId: String, parent: ApplicationContext) {
  ClassPathXmlApplicationContext(
          arrayOf("classpath*:/META-INF/$pluginId/config*.xml"),
          true,
          parent)



}



fun usesAll() {
  ExtensionRegistry::class
  ExtensionHolder::class
  ExtensionRegistry::register
  ExtensionHolder::extensions
}


