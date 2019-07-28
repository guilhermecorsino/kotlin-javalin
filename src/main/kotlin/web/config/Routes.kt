package web.config

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import config.DependencyManager

fun Javalin.mapRoutes(): Javalin =
    this.routes {
        path("accounts") {
            get { ctx -> DependencyManager.accountController.getAllAccounts(ctx) }

            post { ctx -> DependencyManager.accountController.createAccount(ctx) }

            path(":giverId") {
                post("transfer") { ctx -> DependencyManager.transferController.transfer(ctx) }
            }
        }
    }