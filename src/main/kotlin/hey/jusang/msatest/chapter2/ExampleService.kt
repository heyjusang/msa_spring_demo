package hey.jusang.msatest.chapter2

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component


@Qualifier("serviceInterface")
@ConditionalOnProperty(name = ["service.message.type"], havingValue = "simple")
@Component
class ExampleService : ServiceInterface {

    @Value("\${service.message.text}")
    private lateinit var text: String

    override fun getHello(name: String) = "$text $name"
}