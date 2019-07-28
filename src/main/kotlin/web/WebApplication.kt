package web

import config.DependencyInjector
import io.javalin.Javalin
import org.koin.core.KoinComponent
import web.config.mapRoutes
import web.config.setDefaultErrorHandler

class WebApplication : KoinComponent {

    fun start() {
        DependencyInjector.inject()

        Javalin.create()
            .mapRoutes()
            .setDefaultErrorHandler()
            .start()
    }
}