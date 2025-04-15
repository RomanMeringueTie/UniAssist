package ru.sibsutis.uniassist.di

import dagger.Component
import ru.sibsutis.authorization.di.AuthorizationComponent
import ru.sibsutis.core.di.CoreComponent
import ru.sibsutis.uniassist.presentation.MainActivityViewModel

@Component(
    modules = [AppModule::class],
    dependencies = [CoreComponent::class, AuthorizationComponent::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun authorizationComponent(authorizationComponent: AuthorizationComponent): Builder
        fun build(): AppComponent
    }

    fun getMainActivityViewModel(): MainActivityViewModel
}