package hey.jusang.msatest

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(val customerHandler: CustomerHandler) {
    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/customer".nest {
            GET("/{id}", customerHandler::get)
            POST(customerHandler::create)
            DELETE("/{id}", customerHandler::delete)
        }
        "/customers".nest {
            GET(customerHandler::search)
        }
    }
}