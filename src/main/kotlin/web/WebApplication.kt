package web

import config.DependencyInjector
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import org.koin.core.KoinComponent
import org.koin.core.inject
import web.config.setDefaultErrorHandler
import web.controllers.AccountController
import web.controllers.TransferController

class WebApplication : KoinComponent {

    fun start() {
        DependencyInjector.inject()

        val app = Javalin.create()

        val accountController by inject<AccountController>()
        val transferController by inject<TransferController>()

        app.routes {
            path("accounts") {
                get { ctx -> accountController.getAllAccounts(ctx) }

                post { ctx -> accountController.createAccount(ctx) }

                path(":giverId") {
                    post("transfer") { ctx -> transferController.transfer(ctx) }
                }
            }
        }

        app.setDefaultErrorHandler()

        app.start()
    }
}