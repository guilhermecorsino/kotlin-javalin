package config

import org.koin.core.KoinComponent
import org.koin.core.inject
import web.controllers.AccountController
import web.controllers.TransferController

class DependencyManager {
    companion object: KoinComponent {
        val accountController by inject<AccountController>()
        val transferController by inject<TransferController>()
    }
}