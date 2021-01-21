package hey.jusang.msatest

import org.springframework.stereotype.Component
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

    override fun getCustomer(id: Int): Customer? = customers[id]

    override fun createCustomer(customer: Customer) {
        customers[customer.id] = customer
    }

    override fun deleteCustomer(id: Int) {
        customers.remove(id)
    }

    override fun updateCustomer(id: Int, customer: Customer) {
        deleteCustomer(id)
        createCustomer(customer)
    }

    override fun searchCustomers(nameFilter: String): List<Customer> =
        customers.filter { it.value.name.contains(nameFilter, true) }.values.toList()
}