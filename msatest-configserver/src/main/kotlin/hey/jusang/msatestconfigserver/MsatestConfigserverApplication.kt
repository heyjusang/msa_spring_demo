package hey.jusang.msatestconfigserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class MsatestConfigserverApplication

fun main(args: Array<String>) {
	runApplication<MsatestConfigserverApplication>(*args)
}
