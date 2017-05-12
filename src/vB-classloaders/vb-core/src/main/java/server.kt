package plugin.extensions.core

import org.springframework.stereotype.Component
import plugin.extensions.ExtensionHolder
import plugin.extensions.ServiceFromThePlugin1
import plugin.extensions.ServiceFromThePlugin2

@Component
class ServerBean(
        val extensionHolder: ExtensionHolder,
        val ext1 : ServiceFromThePlugin1,
        val ext2 : ServiceFromThePlugin2
) {
  fun run() {
    extensionHolder.extensions.forEach {
      println("Extension is here: ${it.javaClass.simpleName}")
    }



    ext1.test()

    ext2.test()
  }
}
