package hey.jusang.msatest

import org.springframework.beans.factory.annotation.Value

class AdvanceService : ServiceInterface {

    @Value("\${service.message.text}")
    private lateinit var text: String
    private var count: Int = 1

    override fun getHello(name: String): String = "$text $name (${count++})"
}