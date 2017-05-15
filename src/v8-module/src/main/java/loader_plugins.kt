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

  var context = null as AnnotationConfigApplicationContext?
  var beanFactoryName = null as String?

  override fun setBeanName(name: String?) {
    beanFactoryName = name!!
  }

  override fun afterPropertiesSet() {
    println("PluginLoader: loading plugin $name...")

    val context = AnnotationConfigApplicationContext()
    this.context = context

    context.parent = parentContext
    context.displayName = "plugin: $name"
    context.scan(Extension::class.java.`package`.name + "." + name)

/*
    (context.beanFactory as DefaultListableBeanFactory).autowireCandidateResolver =
            object : SimpleAutowireCandidateResolver() {
              override fun isAutowireCandidate(bdHolder: BeanDefinitionHolder,
                                               descriptor: DependencyDescriptor)
                      = super.isAutowireCandidate(bdHolder, descriptor) &&
                      beanFactoryName != bdHolder.beanDefinition.factoryBeanName
            }
*/
    
    context.refresh()

    context.getBeansOfType(Extension::class.java)
            .values
            .forEach {
              registry.register(it)
            }

  }

  protected inline fun <reified T> getPluginBean()
          = context!!.getBean(T::class.java)!!
}
