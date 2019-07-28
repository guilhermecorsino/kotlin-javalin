package config.modules

import domains.AccountManager
import org.koin.core.module.Module
import org.koin.dsl.module
import services.AccountService
import web.controllers.AccountController

val accountModule: Module =  module {
    single { AccountController(get()) }
    single { AccountManager() }
    single { AccountService(get()) }
}
