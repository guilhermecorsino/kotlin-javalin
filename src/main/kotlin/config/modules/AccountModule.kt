package config.modules

import domains.InMemoryAccountRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import services.AccountService
import web.controllers.AccountController

val accountModule: Module = module {
    single { AccountController(get()) }
    single { InMemoryAccountRepository() }
    single { AccountService(get()) }
}
