package ru.sibsutis.uniassist.di

import dagger.Component
import ru.sibsutis.core.di.CoreModule

@Component(modules = [CoreModule::class])
interface AppComponent {
}