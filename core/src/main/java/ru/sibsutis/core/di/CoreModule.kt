package ru.sibsutis.core.di

import dagger.Module
import dagger.Provides
import ru.sibsutis.core.network.KtorClient

import ru.sibsutis.core.utils.TokenProvider
import javax.inject.Singleton
@Module
class CoreModule {
    @Provides
    @Singleton
    fun provideKtorClient(tokenProvider: TokenProvider) = KtorClient(tokenProvider)
}