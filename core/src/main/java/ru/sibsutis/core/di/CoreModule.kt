package ru.sibsutis.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.core.utils.SecureSharedPrefs

@Module
class CoreModule {
    @Provides
    fun provideKtorClient() = KtorClient()

    @Provides
    fun provideSecureSharedPrefs(context: Context) = SecureSharedPrefs(context)
}