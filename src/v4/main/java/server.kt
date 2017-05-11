package plugin.extensions.v4.core

import org.springframework.stereotype.Component
import plugin.extensions.v4.ExtensionHolder

@Component
class ServerBean(
        val extensionHolder: ExtensionHolder
) {
  fun run() {
    extensionHolder.extensions.forEach {
      println("Extension is here: ${it.javaClass.simpleName}")
    }
  }
}
