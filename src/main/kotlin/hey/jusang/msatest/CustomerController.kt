package hey.jusang.msatest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController @Autowired constructor(val customerService: CustomerService) {
    @GetMapping(value = ["/customers"])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        customerService.searchCustomers(nameFilter)

    @GetMapping(value = ["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Any> {
        val customer: Customer? = customerService.getCustomer(id)

        return if (customer != null)
            ResponseEntity(customer, HttpStatus.OK)
        else
            ResponseEntity(
                ErrorResponse("Customer Not Found", "customer '$id' not found"),
                HttpStatus.NOT_FOUND
            )

        // using @ExceptionHandler of CustomerNotFoundException in @ControllerAdvice
        /*
        val customer: Customer =
            customerService.getCustomer(id) ?: throw CustomerNotFoundException("customer '$id' not found")

        return ResponseEntity(customer, HttpStatus.OK)
         */
    }

    @PostMapping(value = ["/customer"])
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Unit?> {
        customerService.createCustomer(customer)
        return ResponseEntity(null, HttpStatus.CREATED)
    }

    @DeleteMapping(value = ["/customer/{id}"])
    fun deleteCustomer(@PathVariable id: Int): ResponseEntity<Unit?> {
        var status: HttpStatus = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(null, status)
    }

    @PutMapping(value = ["/customer/{id}"])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer): ResponseEntity<Unit?> {
        var status: HttpStatus = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.updateCustomer(id, customer)
            status = HttpStatus.ACCEPTED
        }

        return ResponseEntity(null, status)
    }
}