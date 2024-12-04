package ru.sibsutis.uniassist.di

import dagger.Component
import ru.sibsutis.core.di.CoreModule
import ru.sibsutis.student.di.StudentModule
import ru.sibsutis.student.presentation.StudentScheduleViewModel
import ru.sibsutis.uniassist.MainActivity

@Component(modules = [StudentModule::class, CoreModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}