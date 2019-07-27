package config

import org.koin.core.context.startKoin

class DependencyInjector {

    companion object {
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