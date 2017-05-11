package plugin.extensions

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


@ProvidedByPlugin
interface ServiceFromThePlugin {
  fun test()
}
