package modulo_05.sprint.mydatabase.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import modulo_05.sprint.mydatabase.repository.ContactRepository
import modulo_05.sprint.mydatabase.repository.OfflineContactRepository
import modulo_05.sprint.mydatabase.room.ContactDAO
import modulo_05.sprint.mydatabase.room.ContactDatabase
import modulo_05.sprint.mydatabase.views.HomeViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesContactDatabaseDAO(contactDatabase: ContactDatabase): ContactDAO {
        return contactDatabase.contactDao()
    }
    @Singleton
    @Provides
    fun providesContactDatabase(@ApplicationContext context: Context): ContactDatabase {
        return Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contact_database"
        ).fallbackToDestructiveMigration().build()
    }

}