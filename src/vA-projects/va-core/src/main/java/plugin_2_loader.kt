package plugin.extensions.core

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import plugin.extensions.ServiceFromThePlugin2


@Component
class Plugin_2Loader : PluginLoader("plugin_2") {
  @Bean
  fun getSharedBean2() = pluginContext.getBean(ServiceFromThePlugin2::class.java)
}
