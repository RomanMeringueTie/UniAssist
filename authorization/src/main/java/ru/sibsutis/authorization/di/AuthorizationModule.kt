package ru.sibsutis.authorization.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.sibsutis.authorization.data.repository.AuthorizationRepository
import ru.sibsutis.authorization.data.repository.AuthorizationRepositoryImpl
import ru.sibsutis.authorization.data.service.AuthorizationService
import ru.sibsutis.authorization.domain.GetTokenUseCase
import ru.sibsutis.authorization.domain.GetTokenUseCaseImpl
import ru.sibsutis.authorization.presentation.AuthorizationViewModel
import ru.sibsutis.authorization.presentation.BackgroundAuthorizationViewModel
import ru.sibsutis.core.network.KtorClient

@Module
class AuthorizationModule {

    @Provides
    fun provideAuthorizationService(ktorClient: KtorClient) =
        AuthorizationService(ktorClient)

    @Provides
    fun provideAuthorizationRepository(authorizationService: AuthorizationService): AuthorizationRepository =
        AuthorizationRepositoryImpl(authorizationService)

    @Provides
    fun provideGetTokenUseCase(authorizationRepository: AuthorizationRepository): GetTokenUseCase =
        GetTokenUseCaseImpl(authorizationRepository)

    @Provides
    fun provideAuthorizationViewModel(context: Context, getTokenUseCase: GetTokenUseCase) =
        AuthorizationViewModel(getTokenUseCase, context)

    @Provides
    fun provideBackgroundAuthorizationViewModel(context: Context, getTokenUseCase: GetTokenUseCase) =
        BackgroundAuthorizationViewModel(getTokenUseCase, context)

}