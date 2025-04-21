package ru.sibsutis.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.core.utils.SecureSharedPrefs
import ru.sibsutis.core.utils.TokenProvider
import javax.inject.Singleton

@Module
class CoreModule {
    @Provides
    @Singleton
    fun provideKtorClient(tokenProvider: TokenProvider) = KtorClient(tokenProvider)

    @Provides
    fun provideSecureSharedPrefs(context: Context) = SecureSharedPrefs(context)
}