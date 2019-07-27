package web.config

import io.javalin.Javalin
import web.exceptions.ApiException

fun Javalin.setDefaultErrorHandler(): Javalin =
    this.exception(ApiException::class.java) { e, ctx ->
        ctx.status(e.httpStatus())
        ctx.result(e.message())
    }