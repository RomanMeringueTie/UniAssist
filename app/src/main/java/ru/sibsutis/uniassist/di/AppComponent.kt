package ru.sibsutis.uniassist.di

import dagger.Component
import ru.sibsutis.core.di.CoreComponent
import ru.sibsutis.core.di.CoreScope
import ru.sibsutis.uniassist.presentation.MainActivityViewModel
import javax.inject.Singleton

@Component(modules = [AppModule::class], dependencies = [CoreComponent::class])
@CoreScope
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AppComponent
    }

    fun getMainActivityViewModel(): MainActivityViewModel
}