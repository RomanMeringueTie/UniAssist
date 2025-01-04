package ru.sibsutis.student.di

import dagger.Module
import dagger.Provides
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.student.data.repository.StudentRepository
import ru.sibsutis.student.data.repository.StudentRepositoryImpl
import ru.sibsutis.student.data.service.StudentService
import ru.sibsutis.student.domain.GetStudentClassUseCase
import ru.sibsutis.student.domain.GetStudentScheduleUseCase
import ru.sibsutis.student.presentation.StudentClassViewModel
import ru.sibsutis.student.presentation.StudentScheduleViewModel

@Module
class StudentModule {

    @Provides
    fun provideStudentService(ktorClient: KtorClient) = StudentService(ktorClient)

    @Provides
    fun provideStudentRepository(studentService: StudentService): StudentRepository =
        StudentRepositoryImpl(studentService)

    @Provides
    fun provideGetStudentScheduleUseCase(studentRepository: StudentRepository) =
        GetStudentScheduleUseCase(studentRepository)

    @Provides
    fun provideStudentScheduleViewModel(getStudentScheduleUseCase: GetStudentScheduleUseCase) =
        StudentScheduleViewModel(getStudentScheduleUseCase)

    @Provides
    fun provideGetStudentClassUseCase(studentRepository: StudentRepository) =
        GetStudentClassUseCase(studentRepository)

    @Provides
    fun provideStudentClassViewModel(getStudentClassUseCase: GetStudentClassUseCase) =
        StudentClassViewModel(getStudentClassUseCase)

}