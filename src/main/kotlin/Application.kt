import domains.AccountManager
import exceptions.ApiException
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.Context
import requests.TransferRequest
import services.AccountService
import services.command.TransferCommand
import web.controllers.AccountController

fun main(args: Array<String>) {

    val manager = AccountManager()
    val service = AccountService(manager)
    val controller = AccountController(service)

    val app = Javalin.create().start(7000)

    app.routes {
        path("accounts") {
            get { ctx ->
                controller.getAllAccounts(ctx)
            }

            post { ctx ->
                controller.createAccount(ctx)
            }

            path(":giverId") {
                post("transfer") { ctx ->
                    transfer(ctx, service)
                }
            }
        }
    }

    app.exception(ApiException::class.java) { e, ctx ->
        ctx.status(e.httpStatus())
        ctx.result(e.message())
    }
}

private fun transfer(ctx: Context, service: AccountService) {
    val request = ctx.body<TransferRequest>()

    val command = TransferCommand(
        ctx.pathParam("giverId"),
        request.beneficiaryId,
        request.value
    )

    service.transfer(command)
}