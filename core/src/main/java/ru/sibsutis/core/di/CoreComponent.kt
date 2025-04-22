package ru.sibsutis.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.core.utils.SecureSharedPrefs
import ru.sibsutis.core.utils.TokenProvider
import javax.inject.Singleton

@Component(modules = [CoreModule::class])
@Singleton
interface CoreComponent {
    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
        @BindsInstance
        fun tokenProvider(tokenProvider: TokenProvider): Builder
        @BindsInstance
        fun context(context: Context): Builder
    }

    fun getKtorClient(): KtorClient
    fun getSecureSharedPrefs(): SecureSharedPrefs
}