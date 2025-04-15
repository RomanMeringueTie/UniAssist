package ru.sibsutis.core.di

import dagger.Component
import ru.sibsutis.core.network.KtorClient

@Component(modules = [CoreModule::class])
interface CoreComponent {
    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
    }

    fun getKtorClient(): KtorClient
}