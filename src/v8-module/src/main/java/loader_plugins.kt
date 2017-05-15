package plugin.extensions.core

import org.springframework.beans.factory.BeanNameAware
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import plugin.extensions.Extension

abstract class PluginLoader(
        val name: String
) : InitializingBean, BeanNameAware {
  @Autowired lateinit var registry: ExtensionRegistry
  @Autowired lateinit var parentContext : ApplicationContext
  lateinit var pluginContext: AnnotationConfigApplicationContext
  lateinit var beanFactoryName : String

  override fun setBeanName(name: String?) {
    beanFactoryName = name!!
  }

  override fun afterPropertiesSet() {
    println("PluginLoader: loading plugin $name...")

    pluginContext = AnnotationConfigApplicationContext()
    pluginContext.parent = parentContext
    pluginContext.displayName = "plugin: $name"
    pluginContext.scan("plugin.extensions." + name)

/*
    (pluginContext.beanFactory as DefaultListableBeanFactory).autowireCandidateResolver =
            object : SimpleAutowireCandidateResolver() {
              override fun isAutowireCandidate(bdHolder: BeanDefinitionHolder,
                                               descriptor: DependencyDescriptor)
                      = super.isAutowireCandidate(bdHolder, descriptor) &&
                      beanFactoryName != bdHolder.beanDefinition.factoryBeanName
            }
*/
    
    pluginContext.refresh()

    pluginContext.getBeansOfType(Extension::class.java)
            .values
            .forEach {
              registry.register(it)
            }

  }
}
