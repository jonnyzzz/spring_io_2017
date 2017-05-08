package plugin.extensions.v5.core

import org.springframework.stereotype.Component
import plugin.extensions.v5.ExtensionHolder

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
