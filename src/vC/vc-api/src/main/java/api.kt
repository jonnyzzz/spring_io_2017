package plugin.extensions

annotation class ProvidedByPlugin
annotation class ProvidedByCore

@ProvidedByPlugin
interface ServiceFromThePlugin1 {
  fun test()
}

@ProvidedByPlugin
interface ServiceFromThePlugin2 {
  fun test()
}


