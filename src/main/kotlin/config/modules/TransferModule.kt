package config.modules

import org.koin.core.module.Module
import org.koin.dsl.module
import web.controllers.TransferController

val transferModule: Module =  module {
    single { TransferController(get()) }
}