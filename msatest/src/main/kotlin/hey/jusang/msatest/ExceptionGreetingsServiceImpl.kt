package hey.jusang.msatest

import org.springframework.stereotype.Service
import java.util.*

@Service
class ExceptionGreetingsServiceImpl : ExceptionGreetingsService {
    companion object {
        private val greetingsMessages = arrayOf("Hello", "Ola", "Namaste", "Hola")
    }

    private fun random(max: Int): Int = Random().nextInt(max) + 1

    // ArrayIndexOutOfBoundsException
    override fun getGreeting() = greetingsMessages[random(4)]
}