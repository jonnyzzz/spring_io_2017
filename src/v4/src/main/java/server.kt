package plugin.extensions.core

import org.springframework.stereotype.Component
import plugin.extensions.ExtensionHolder

@Component
class ServerBean(
        val extensionHolder: ExtensionHolder
) {
  fun run() {
    extensionHolder.extensions.forEach {
      println("Detected Extension: ${it.javaClass.simpleName}")
    }
  }
}
