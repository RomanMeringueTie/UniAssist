package ru.sibsutis.authorization.di

import dagger.Module
import dagger.Provides
import ru.sibsutis.authorization.data.repository.AuthorizationRepository
import ru.sibsutis.authorization.data.repository.AuthorizationRepositoryImpl
import ru.sibsutis.authorization.data.service.AuthorizationService
import ru.sibsutis.authorization.domain.GetTokenUseCase
import ru.sibsutis.authorization.domain.GetTokenUseCaseImpl
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

}