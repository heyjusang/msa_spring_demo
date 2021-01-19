package hey.jusang.msatest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class MsatestApplication {
    @Bean
    @Qualifier("serviceInterface")
    @ConditionalOnProperty(name = ["service.message.type"], havingValue = "simple")
    fun exampleService(): ServiceInterface = ExampleService()

    @Bean
    @Qualifier("serviceInterface")
    @ConditionalOnProperty(name = ["service.message.type"], havingValue = "advance")
    fun advanceService(): ServiceInterface = AdvanceService()
}

@Controller
class FirstController @Autowired constructor(@Qualifier("serviceInterface") val service: ServiceInterface) {
    @RequestMapping(value = ["/user/{name}"], method = [RequestMethod.GET])
    @ResponseBody
    fun hello(@PathVariable name: String) = service.getHello(name)
}

fun main(args: Array<String>) {
    runApplication<MsatestApplication>(*args)
}
