package hey.jusang.msatest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExceptionGreetingsController(val greetingsService: ExceptionGreetingsService) {

    @GetMapping("/exception_hello")
    fun message() = greetingsService.getGreeting()
}