package plugin.extensions.v1

interface Extension {
  //Extension API
}
interface ExtensionRegistry {
  fun register(e: Extension)
}
interface ExtensionHolder {
  val extensions: List<Exception>
}


