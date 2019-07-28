package config

import config.modules.accountModule
import config.modules.transferModule
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class DependencyInjector {

    companion object : KoinComponent {
        fun inject() {
            startKoin {
                modules(
                    listOf(
                        accountModule,
                        transferModule
                    )
                )
            }
        }
    }
}