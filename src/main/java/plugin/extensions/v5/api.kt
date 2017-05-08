package plugin.extensions.v5

annotation class ProvidedByPlugin
annotation class ProvidedByCore

@ProvidedByPlugin
interface Extension {
  //Extension API
}

@ProvidedByCore
interface ExtensionHolder {
  val extensions: List<Extension>
}

@ProvidedByCore
interface CoreService {
  fun `loren ipsum`()
}
