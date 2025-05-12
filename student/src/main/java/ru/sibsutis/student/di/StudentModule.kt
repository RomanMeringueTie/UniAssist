package ru.sibsutis.student.di

import dagger.Module
import dagger.Provides
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.student.data.repository.StudentRepository
import ru.sibsutis.student.data.repository.StudentRepositoryImpl
import ru.sibsutis.student.data.service.StudentService
import ru.sibsutis.student.domain.GetStudentClassUseCase
import ru.sibsutis.student.domain.GetStudentClassUseCaseImpl
import ru.sibsutis.student.domain.GetStudentScheduleUseCase
import ru.sibsutis.student.domain.GetStudentScheduleUseCaseImpl
import ru.sibsutis.student.domain.SendStudentResponseUseCase
import ru.sibsutis.student.domain.SendStudentResponseUseCaseImpl
import ru.sibsutis.student.presentation.StudentScheduleViewModel
import ru.sibsutis.student.ui.ClassConverter

@Module
class StudentModule {
    @Provides
    fun provideStudentService(ktorClient: KtorClient) = StudentService(ktorClient)

    @Provides
    fun provideStudentRepository(studentService: StudentService): StudentRepository =
        StudentRepositoryImpl(studentService)

    @Provides
    fun provideGetStudentScheduleUseCase(studentRepository: StudentRepository): GetStudentScheduleUseCase =
        GetStudentScheduleUseCaseImpl(studentRepository)

    @Provides
    fun provideSendStudentResponseUseCase(studentRepository: StudentRepository): SendStudentResponseUseCase =
        SendStudentResponseUseCaseImpl(studentRepository)

    @Provides
    fun provideClassConverter() = ClassConverter()

    @Provides
    fun provideStudentScheduleViewModel(
        classConverter: ClassConverter,
        getStudentScheduleUseCase: GetStudentScheduleUseCase
    ) =
        StudentScheduleViewModel(classConverter, getStudentScheduleUseCase)

    @Provides
    fun provideGetStudentClassUseCase(studentRepository: StudentRepository): GetStudentClassUseCase =
        GetStudentClassUseCaseImpl(studentRepository)

}