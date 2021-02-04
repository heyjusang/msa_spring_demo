package hey.jusang.msatest

import org.springframework.boot.actuate.health.AbstractHealthIndicator
import org.springframework.boot.actuate.health.Health
import org.springframework.stereotype.Component

@Component
class GreetingsServiceHealthIndicator(val greetingsService: ExceptionGreetingsService) : AbstractHealthIndicator() {
    override fun doHealthCheck(builder: Health.Builder?) {
        val lastMessage = try {
            val message = greetingsService.getGreeting()
            builder?.up()
            message
        } catch (exception: Exception) {
            builder?.down()
            "ERROR:$exception"
        }

        builder?.withDetail("lastMessage", lastMessage)
    }
}