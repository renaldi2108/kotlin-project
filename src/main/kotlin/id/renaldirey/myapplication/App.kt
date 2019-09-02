package id.renaldirey.myapplication

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

data class Hello(val from: String, val to: String)

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        routing {
            get("/hello") {
                val name = call.parameters["name"] ?: "renaldi"
                val respond = Hello(from = name, to = "The World!")
                call.respond(respond)
            }
        }
    }

    server.start(wait = false)
}