package ru.sibsutis.teacher.di

import dagger.Module
import dagger.Provides
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.teacher.data.repository.TeacherRepository
import ru.sibsutis.teacher.data.repository.TeacherRepositoryImpl
import ru.sibsutis.teacher.data.service.TeacherService
import ru.sibsutis.teacher.domain.GetTeacherScheduleUseCase
import ru.sibsutis.teacher.domain.GetTeacherScheduleUseCaseImpl
import ru.sibsutis.teacher.presentation.TeacherScheduleViewModel

@Module
internal class TeacherModule {
    @Provides
    fun provideTeacherService(ktorClient: KtorClient) = TeacherService(ktorClient)

    @Provides
    fun provideTeacherRepository(teacherService: TeacherService) : TeacherRepository =
        TeacherRepositoryImpl(teacherService)

    @Provides
    fun provideGetTeacherScheduleUseCase(teacherRepository: TeacherRepository) : GetTeacherScheduleUseCase =
        GetTeacherScheduleUseCaseImpl(teacherRepository)

    @Provides
    fun provideTeacherScheduleViewModel(getTeacherScheduleUseCase: GetTeacherScheduleUseCase) =
        TeacherScheduleViewModel(getTeacherScheduleUseCase)
}