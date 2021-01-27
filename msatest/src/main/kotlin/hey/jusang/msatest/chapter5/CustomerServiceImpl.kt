package hey.jusang.msatest.chapter5

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomerServiceImpl @Autowired constructor(val customerRepository: CustomerRepository) : CustomerService {
    override fun getCustomer(id: Int) = customerRepository.findById(id)

    override fun createCustomer(customer: Mono<Customer>) = customerRepository.create(customer)

    override fun deleteCustomer(id: Int): Mono<Boolean> = customerRepository.deleteById(id).map { it.deletedCount > 0 }

    override fun searchCustomers(nameFilter: String) = customerRepository.findCustomer(nameFilter)
}