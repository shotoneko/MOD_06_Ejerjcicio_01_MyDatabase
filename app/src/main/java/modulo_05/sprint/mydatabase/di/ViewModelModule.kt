package modulo_05.sprint.mydatabase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import modulo_05.sprint.mydatabase.repository.ContactRepository
import modulo_05.sprint.mydatabase.repository.OfflineContactRepository
import modulo_05.sprint.mydatabase.room.ContactDAO

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule { // New module for ViewModel dependencies

    @Provides
    fun provideContactRepository(contactDatabaseDAO: ContactDAO): ContactRepository {
        return OfflineContactRepository(contactDatabaseDAO)
    }
}