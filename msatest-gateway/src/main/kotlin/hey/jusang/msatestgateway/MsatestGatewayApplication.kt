package hey.jusang.msatestgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
class MsatestGatewayApplication

fun main(args: Array<String>) {
	runApplication<MsatestGatewayApplication>(*args)
}
