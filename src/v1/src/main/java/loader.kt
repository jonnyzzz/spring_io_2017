package plugin.extensions.core

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component


class RootContext {
  fun start() {
    val root = AnnotationConfigApplicationContext()
    root.scan("plugin.extensions.core")

    println()
    println("RootContext: RefreshEvent")
    root.refresh()

    println()
    println("RootContext: StartEvent")
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
