package web.controllers

import io.javalin.http.Context
import web.requests.TransferRequest
import services.AccountService
import services.command.TransferCommand

class TransferController(
    private val service: AccountService
) {
     fun transfer(ctx: Context) {
        val request = ctx.body<TransferRequest>()

        val command = TransferCommand(
            ctx.pathParam("giverId"),
            request.beneficiaryId,
            request.value
        )

        service.transfer(command)
    }
}