package plugin.extensions.v5.core

import org.springframework.stereotype.Component
import plugin.extensions.v5.CoreService

@Component
class AdHocCoreService : CoreService {
  override fun `loren ipsum`() {
    println("CoreService: Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
            "\r\n             Donec a ullamcorper ex. Praesent tincidunt sit amet dolor a " +
            "\r\n             sodales. Sed nec feugiat mi. Vivamus purus diam, interdum " +
            "\r\n             eleifend risus non, pharetra laoreet est. Mauris eu dolor... ")
  }
}
