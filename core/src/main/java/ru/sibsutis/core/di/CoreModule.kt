package ru.sibsutis.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.core.utils.SecureSharedPrefs
import javax.inject.Singleton

@Module
class CoreModule {
    @Provides
    @Singleton
    fun provideKtorClient() = KtorClient()

    @Provides
    fun provideSecureSharedPrefs(context: Context) = SecureSharedPrefs(context)
}