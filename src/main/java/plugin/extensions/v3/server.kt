package plugin.extensions.v3.core

import org.springframework.stereotype.Component
import plugin.extensions.v3.ExtensionHolder

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
