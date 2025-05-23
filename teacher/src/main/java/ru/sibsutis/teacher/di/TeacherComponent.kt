package ru.sibsutis.teacher.di

import dagger.Component
import ru.sibsutis.core.di.CoreComponent
import ru.sibsutis.core.di.CoreScope
import ru.sibsutis.teacher.domain.GetTeacherClassUseCase
import ru.sibsutis.teacher.presentation.TeacherClassViewModel
import ru.sibsutis.teacher.presentation.TeacherScheduleViewModel
import ru.sibsutis.teacher.ui.ClassConverter
import javax.inject.Singleton

@Component(modules = [TeacherModule::class], dependencies = [CoreComponent::class])
@CoreScope
interface TeacherComponent {
    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): TeacherComponent
    }

    fun getScheduleViewModel(): TeacherScheduleViewModel
    fun getTeacherClassViewModelFactory(): (String) -> TeacherClassViewModel
}