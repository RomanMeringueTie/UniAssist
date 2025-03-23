package ru.sibsutis.student.di

import dagger.Component
import ru.sibsutis.core.di.CoreComponent
import ru.sibsutis.student.domain.GetStudentClassUseCase
import ru.sibsutis.student.presentation.StudentScheduleViewModel
import ru.sibsutis.student.ui.ClassConverter

@Component(modules = [StudentModule::class], dependencies = [CoreComponent::class])
interface StudentComponent {
    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): StudentComponent
    }

    fun getScheduleViewModel(): StudentScheduleViewModel
    fun getGetStudentClassUseCase(): GetStudentClassUseCase
    fun getClassConverter(): ClassConverter
}