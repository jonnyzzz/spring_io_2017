package plugin.extensions.v1

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

interface Extension {
  //Extension API
}
interface ExtensionRegistry {
  fun register(e: Extension)
}
interface ExtensionHolder {
  val extensions: List<Exception>
}



fun loadPlugins(pluginId: String, parent: ApplicationContext) {
  ClassPathXmlApplicationContext(
          arrayOf("classpath*:/META-INF/$pluginId/config*.xml"),
          true,
          parent)



}

fun main(args: Array<String>) {

}



fun usesAll() {
  ExtensionRegistry::class
  ExtensionHolder::class
  ExtensionRegistry::register
  ExtensionHolder::extensions
}


