package ru.sibsutis.authorization.di

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
import ru.sibsutis.core.utils.SecureSharedPrefs

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
    fun provideAuthorizationViewModel(
        secureSharedPrefs: SecureSharedPrefs,
        getTokenUseCase: GetTokenUseCase
    ) = AuthorizationViewModel(getTokenUseCase, secureSharedPrefs)

    @Provides
    fun provideBackgroundAuthorizationViewModel(
        secureSharedPrefs: SecureSharedPrefs,
        getTokenUseCase: GetTokenUseCase
    ) = BackgroundAuthorizationViewModel(getTokenUseCase, secureSharedPrefs)
}