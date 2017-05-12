package plugin.extensions.core

import org.springframework.stereotype.Component
import plugin.extensions.CoreService
import plugin.extensions.Extension
import plugin.extensions.ExtensionHolder
import plugin.extensions.ExtensionRegistry
import java.util.*

@Component
class AdHocCoreService : CoreService {
  override fun `loren ipsum`() {
    println("CoreService: Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
            "\r\n             Donec a ullamcorper ex. Praesent tincidunt sit amet dolor a " +
            "\r\n             sodales. Sed nec feugiat mi. Vivamus purus diam, interdum " +
            "\r\n             eleifend risus non, pharetra laoreet est. Mauris eu dolor... ")
  }
}

@Component
class Extensions : ExtensionRegistry, ExtensionHolder {
  private val holder = IdentityHashMap<Extension, Extension>()

  override fun register(e: Extension) {
    println("ExtensionRegistry: Registered ${e.javaClass.simpleName}")
    holder.put(e, e)
  }

  override val extensions: List<Extension>
    get() = holder.keys.filterNotNull().toList().sortedBy { it.javaClass.simpleName }
}
