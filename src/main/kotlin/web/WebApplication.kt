package web

import domains.AccountManager
import web.exceptions.ApiException
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import services.AccountService
import web.controllers.AccountController
import web.controllers.TransferController

class WebApplication {

    fun start() {
        val manager = AccountManager()
        val service = AccountService(manager)
        val accountController = AccountController(service)
        val transferController = TransferController(service)

        val app = Javalin.create().start(7000)

        app.routes {
            path("accounts") {
                get { ctx -> accountController.getAllAccounts(ctx) }

                post { ctx -> accountController.createAccount(ctx) }

                path(":giverId") {
                    post("transfer") { ctx -> transferController.transfer(ctx) }
                }
            }
        }

        app.exception(ApiException::class.java) { e, ctx ->
            ctx.status(e.httpStatus())
            ctx.result(e.message())
        }
    }
}
