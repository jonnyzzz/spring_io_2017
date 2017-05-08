package plugin.extensions.v1.core

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component

fun main(args: Array<String>) = RootContext().start()

class RootContext {
  fun start() {
    val root = AnnotationConfigApplicationContext()
    root.scan(RootContext::class.java.`package`.name)

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
