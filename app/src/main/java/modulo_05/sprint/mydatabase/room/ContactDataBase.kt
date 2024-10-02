package modulo_05.sprint.mydatabase.room


import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import modulo_05.sprint.mydatabase.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDatabaseDAO
}