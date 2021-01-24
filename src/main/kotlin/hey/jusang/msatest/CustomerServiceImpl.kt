package hey.jusang.msatest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl @Autowired constructor(val customerRepository: CustomerRepository) : CustomerService {
    override fun getCustomer(id: Int) = customerRepository.findById(id)

    override fun createCustomer(customer: Mono<Customer>) = customerRepository.create(customer)

    override fun deleteCustomer(id: Int): Mono<Boolean> = customerRepository.deleteById(id).map { it.deletedCount > 0 }

    override fun searchCustomers(nameFilter: String) = customerRepository.findCustomer(nameFilter)
}