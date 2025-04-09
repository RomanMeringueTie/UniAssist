package ru.sibsutis.uniassist.di

import dagger.Module
import dagger.Provides
import ru.sibsutis.core.utils.SecureSharedPrefs
import ru.sibsutis.uniassist.presentation.MainActivityViewModel

@Module
class AppModule {

    @Provides
    fun provideMainActivityViewModel(secureSharedPrefs: SecureSharedPrefs) =
        MainActivityViewModel(secureSharedPrefs)

}