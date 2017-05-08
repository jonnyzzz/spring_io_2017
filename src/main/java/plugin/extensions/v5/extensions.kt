package plugin.extensions.v5.core

import org.springframework.stereotype.Component
import plugin.extensions.v5.Extension
import plugin.extensions.v5.ExtensionHolder
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
