package web.controllers

import io.javalin.http.Context
import requests.CreateAccountRequest
import services.AccountService
import services.command.CreateAccountCommand

class AccountController(
    private val service: AccountService
) {

    fun getAllAccounts(ctx: Context) {
        ctx.json(service.getAllAccounts())
    }

    fun createAccount(ctx: Context) {
        val request = ctx.body<CreateAccountRequest>()

        val command = CreateAccountCommand(
            request.id,
            request.balance
        )

        service.createAccount(command)
    }
}