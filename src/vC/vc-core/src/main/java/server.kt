package plugin.extensions.core

import org.springframework.stereotype.Component
import plugin.extensions.ServiceFromThePlugin1
import plugin.extensions.ServiceFromThePlugin2

@Component
class ServerBean(
        val ext1 : ServiceFromThePlugin1,
        val ext2 : ServiceFromThePlugin2
) {
  fun run() {
    val la = ext1.test()
    val lb = ext2.test()

    println("Type1: ${la.javaClass}")
    println("Type1: ${la.javaClass.classLoader}")
    println("Type2: ${lb.javaClass}")
    println("Type2: ${lb.javaClass.classLoader}")

    println("Type1 == Type2: ${la.javaClass.equals(lb.javaClass)}")

    (la + lb).forEach {
      println(">> $it")
    }
  }
}
