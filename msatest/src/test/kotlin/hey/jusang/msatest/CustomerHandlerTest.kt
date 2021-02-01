package hey.jusang.msatest

import hey.jusang.msatest.chapter5.Customer
import hey.jusang.msatest.chapter5.CustomerService
import org.amshove.kluent.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.reset
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.JsonPathAssertions
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono


infix fun WebTestClient.`do a get request to`(uri: String) = this.get().uri(uri).exchange()
infix fun WebTestClient.ResponseSpec.`and expect that status is`(status: Int) =
    this.expectStatus().isEqualTo(status).expectBody()
infix fun WebTestClient.BodyContentSpec.`and expect that`(expression: String) = jsonPath("\$.$expression")
infix fun JsonPathAssertions.`of body is equal to`(value: Any) = this.isEqualTo(value)

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureWebTestClient
class CustomerHandlerTest {
    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var customerService: CustomerService

    @Test
    fun `mock mvc should be configured`() {

    }

    @Test
    fun `we should GET a customer by id`() {
        When calling customerService.getCustomer(1) `it returns`
                Customer(1, "mock customer").toMono()

        (client `do a get request to` "/customer/1"
                `and expect that status is` 200
                `and expect that` "id" `of body is equal to` 1
                `and expect that` "name" `of body is equal to` "mock customer"
                )

        Verify on customerService that customerService.getCustomer(1) was called
        `Verify no further interactions` on customerService

        reset(customerService)
    }

    @Test
    fun `we should GET a list of customers`() {
        When calling customerService.searchCustomers("") `it returns`
            listOf(Customer(1, "test"), Customer(2, "mocks")).toFlux()

        (client `do a get request to` "/customers"
                `and expect that status is` 200
                `and expect that` "[0].id" `of body is equal to` 1
                `and expect that` "[0].name" `of body is equal to` "test"
                `and expect that` "[1].id" `of body is equal to` 2
                `and expect that` "[1].name" `of body is equal to` "mocks"
                )

        Verify on customerService that customerService.searchCustomers("") was called
        `Verify no further interactions` on customerService

        reset(customerService)
    }
}