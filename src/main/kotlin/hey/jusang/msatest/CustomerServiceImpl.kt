package hey.jusang.msatest

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomers: Array<Customer> = arrayOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice", Customer.Telephone("+46", "9123456789"))
        )
    }

    private val customers = ConcurrentHashMap(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int): Mono<Customer> = customers[id]?.toMono() ?: Mono.empty()

    override fun createCustomer(customerMono: Mono<Customer>): Mono<Customer> =
        customerMono.flatMap {
            if (customers[it.id] == null) {
                customers[it.id] = it
                it.toMono()
            }
            else {
                Mono.error(CustomerExistException("Customer ${it.id} already exist"))
            }
        }

    override fun searchCustomers(nameFilter: String): Flux<Customer> =
        customers.filter { it.value.name.contains(nameFilter, true) }.values.toFlux()
}