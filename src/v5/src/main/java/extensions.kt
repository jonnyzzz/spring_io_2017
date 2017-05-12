package plugin.extensions.core

import org.springframework.stereotype.Component
import plugin.extensions.Extension
import plugin.extensions.ExtensionHolder
import java.util.*

interface ExtensionRegistry {
  fun register(e: Extension)
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
