package hey.jusang.msatestdiscoveryserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class MsatestDiscoveryserverApplication

fun main(args: Array<String>) {
	runApplication<MsatestDiscoveryserverApplication>(*args)
}
