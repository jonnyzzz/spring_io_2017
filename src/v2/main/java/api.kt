package plugin.extensions.v2

annotation class ProvidedByPlugin
annotation class ProvidedByCore

@ProvidedByPlugin
interface Extension {
  //Extension API
}
@ProvidedByCore
interface ExtensionRegistry {
  fun register(e: Extension)
}

@ProvidedByCore
interface ExtensionHolder {
  val extensions: List<Exception>
}

@ProvidedByCore
interface CoreService {
  fun `loren ipsum`()
}


fun allUsed() {
  ExtensionRegistry::register
  ExtensionHolder::extensions
}
