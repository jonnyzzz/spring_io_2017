package plugin.extensions.core

import org.springframework.stereotype.Component
import plugin.extensions.ServiceFromThePlugin1
import plugin.extensions.ServiceFromThePlugin2

@Component
class ServerBean(
        val ext1 : ServiceFromThePlugin1,
        val ext2 : ServiceFromThePlugin2
) {
  fun run() {

    ext1.test()

    ext2.test()
  }
}
