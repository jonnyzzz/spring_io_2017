package plugin.extensions.core

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import plugin.extensions.ServiceFromThePlugin1


@Component
class Plugin_1Loader : PluginLoader("plugin_1") {
  @Bean
  fun getSharedBean1() = pluginContext.getBean(ServiceFromThePlugin1::class.java)
}
