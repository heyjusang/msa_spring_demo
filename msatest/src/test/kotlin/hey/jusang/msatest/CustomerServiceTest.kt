package hey.jusang.msatest

import hey.jusang.msatest.chapter5.CustomerService
import org.amshove.kluent.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class CustomerServiceTest {

    @Autowired
    lateinit var customerService: CustomerService

    @Test
    fun `we should get a customer with a valid id`() {
        val customer = customerService.getCustomer(1)
        customer.`should not be null`()
        customer.block()?.name `should equal` "Kotlin"
    }

    @Test
    fun `we should get all customers`() {
        val customers = customerService.searchCustomers("")
        customers.collectList().block()?.size `should not be` 0
    }
}