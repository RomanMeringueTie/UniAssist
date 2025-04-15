package ru.sibsutis.core.di

import dagger.Module
import dagger.Provides
import ru.sibsutis.core.network.KtorClient

@Module
class CoreModule {
    @Provides
    fun provideKtorClient() = KtorClient()
}