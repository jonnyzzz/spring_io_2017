package plugin.extensions.v4

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
  val extensions: List<Extension>
}

@ProvidedByCore
interface CoreService {
  fun `loren ipsum`()
}
