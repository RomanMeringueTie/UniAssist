package ru.sibsutis.authorization.di

import dagger.Component
import ru.sibsutis.authorization.presentation.AuthorizationViewModel
import ru.sibsutis.authorization.presentation.BackgroundAuthorizationViewModel
import ru.sibsutis.core.di.CoreComponent

@Component(modules = [AuthorizationModule::class], dependencies = [CoreComponent::class])
interface AuthorizationComponent {
    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AuthorizationComponent
    }

    fun getAuthorizationViewModel(): AuthorizationViewModel
    fun getBackgroundAuthorizationViewModel(): BackgroundAuthorizationViewModel
}