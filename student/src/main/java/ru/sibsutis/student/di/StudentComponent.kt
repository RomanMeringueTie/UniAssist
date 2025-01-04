package ru.sibsutis.student.di

import dagger.Component
import ru.sibsutis.core.di.CoreComponent
import ru.sibsutis.core.di.CoreModule
import ru.sibsutis.student.presentation.StudentClassViewModel
import ru.sibsutis.student.presentation.StudentScheduleViewModel
import javax.inject.Singleton

@Component(modules = [StudentModule::class, CoreModule::class])
interface StudentComponent {
    @Component.Builder
    interface Builder {
        fun build(): StudentComponent
    }

    fun getScheduleViewModel(): StudentScheduleViewModel

    fun getClassViewModel(): StudentClassViewModel
}