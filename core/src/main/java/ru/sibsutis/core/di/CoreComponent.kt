package ru.sibsutis.core.di

import dagger.BindsInstance
import dagger.Component
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.core.utils.TokenProvider
import javax.inject.Singleton

@Component(modules = [CoreModule::class])
@Singleton
interface CoreComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun tokenProvider(tokenProvider: TokenProvider): Builder
        fun build(): CoreComponent
    }

    fun getKtorClient(): KtorClient
}