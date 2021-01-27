package hey.jusang.msatest.chapter2

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Qualifier("serviceInterface")
@ConditionalOnProperty(name = ["service.message.type"], havingValue = "advance")
@Component
class AdvanceService : ServiceInterface {

    @Value("\${service.message.text}")
    private lateinit var text: String
    private var count: Int = 1

    override fun getHello(name: String): String = "$text $name (${count++})"
}