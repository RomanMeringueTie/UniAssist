package ru.sibsutis.authorization.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.sibsutis.authorization.data.manager.SecureSharedPrefs
import ru.sibsutis.authorization.presentation.AuthorizationViewModel
import ru.sibsutis.authorization.presentation.BackgroundAuthorizationViewModel
import ru.sibsutis.core.di.CoreComponent
import ru.sibsutis.core.di.CoreScope
import javax.inject.Singleton

@Component(modules = [AuthorizationModule::class], dependencies = [CoreComponent::class])
@CoreScope
interface AuthorizationComponent {
    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AuthorizationComponent
    }

    fun getAuthorizationViewModel(): AuthorizationViewModel
    fun getBackgroundAuthorizationViewModel(): BackgroundAuthorizationViewModel
    fun getSecureSharedPrefs(): SecureSharedPrefs
}