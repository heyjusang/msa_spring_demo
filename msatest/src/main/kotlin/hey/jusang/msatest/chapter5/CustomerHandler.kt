package hey.jusang.msatest.chapter5

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI

@Component
class CustomerHandler(val customerService: CustomerService) {
    fun get(serverRequest: ServerRequest) =
        customerService.getCustomer(serverRequest.pathVariable("id").toInt())
            .flatMap { ServerResponse.ok().body(fromValue(it)) }
            .switchIfEmpty(ServerResponse.notFound().build())

    fun create(serverRequest: ServerRequest) =
        customerService.createCustomer(serverRequest.bodyToMono())
            .flatMap { ServerResponse.created(URI.create("/functional/customer/${it.id}")).body(fromValue(it)) }
            .onErrorResume(Exception::class.java) {
                ServerResponse.badRequest()
                    .body(fromValue(ErrorResponse("error creating customer", it.message ?: "error")))
            }

    fun delete(serverRequest: ServerRequest) =
        customerService.deleteCustomer(serverRequest.pathVariable("id").toInt())
            .flatMap {
                if (it) ServerResponse.ok().build() else ServerResponse.notFound().build()
            }

    // TODO: flatMap
    fun search(serverRequest: ServerRequest) =
        ServerResponse.ok().body(
            customerService.searchCustomers(serverRequest.queryParam("nameFilter").orElse("")),
            Customer::class.java
        )
}