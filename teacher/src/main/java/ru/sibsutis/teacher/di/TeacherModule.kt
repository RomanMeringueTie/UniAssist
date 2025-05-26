package ru.sibsutis.teacher.di

import dagger.Module
import dagger.Provides
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.teacher.data.repository.TeacherRepository
import ru.sibsutis.teacher.data.repository.TeacherRepositoryImpl
import ru.sibsutis.teacher.data.service.TeacherService
import ru.sibsutis.teacher.domain.GetTeacherClassUseCase
import ru.sibsutis.teacher.domain.GetTeacherClassUseCaseImpl
import ru.sibsutis.teacher.domain.GetTeacherScheduleUseCase
import ru.sibsutis.teacher.domain.GetTeacherScheduleUseCaseImpl
import ru.sibsutis.teacher.domain.SendTeacherMarkUseCase
import ru.sibsutis.teacher.domain.SendTeacherMarkUseCaseImpl
import ru.sibsutis.teacher.domain.SendTeacherTaskUseCase
import ru.sibsutis.teacher.domain.SendTeacherTaskUseCaseImpl
import ru.sibsutis.teacher.presentation.TeacherClassViewModel
import ru.sibsutis.teacher.presentation.TeacherScheduleViewModel
import ru.sibsutis.teacher.ui.ClassConverter

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
    fun provideTeacherScheduleViewModel(
        classConverter: ClassConverter,
        getTeacherScheduleUseCase: GetTeacherScheduleUseCase
    ) =
        TeacherScheduleViewModel(classConverter, getTeacherScheduleUseCase)

    @Provides
    fun provideClassConverter() = ClassConverter()

    @Provides
    fun provideGetTeacherClassUseCase(teacherRepository: TeacherRepository): GetTeacherClassUseCase =
        GetTeacherClassUseCaseImpl(teacherRepository)

    @Provides
    fun provideSendTeacherTaskUseCase(
        teacherRepository: TeacherRepository,
        getTeacherClassUseCase: GetTeacherClassUseCase
    ): SendTeacherTaskUseCase =
        SendTeacherTaskUseCaseImpl(teacherRepository, getTeacherClassUseCase)

    @Provides
    fun provideSendTeacherMarkUseCase(
        teacherRepository: TeacherRepository,
        getTeacherClassUseCase: GetTeacherClassUseCase
    ): SendTeacherMarkUseCase =
        SendTeacherMarkUseCaseImpl(teacherRepository, getTeacherClassUseCase)

    @Provides
    fun provideTeacherClassViewModelFactory(
        classConverter: ClassConverter,
        getTeacherClassUseCase: GetTeacherClassUseCase,
        sendTeacherTaskUseCase: SendTeacherTaskUseCase,
        sendTeacherMarkUseCase: SendTeacherMarkUseCase,
    ): (String) -> TeacherClassViewModel = { id ->
        TeacherClassViewModel(classConverter, getTeacherClassUseCase, sendTeacherTaskUseCase, sendTeacherMarkUseCase, id)
    }
}