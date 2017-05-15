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
  fun service()
}


@ProvidedByPlugin
interface ServiceFromThePlugin1 {
  fun test()
}

@ProvidedByPlugin
interface ServiceFromThePlugin2 {
  fun test()
}
