package ru.sibsutis.core.di

import dagger.Component
import ru.sibsutis.core.network.KtorClient
import javax.inject.Singleton

@Component(modules = [CoreModule::class])
@Singleton
interface CoreComponent {
    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
    }

    fun getKtorClient(): KtorClient
}