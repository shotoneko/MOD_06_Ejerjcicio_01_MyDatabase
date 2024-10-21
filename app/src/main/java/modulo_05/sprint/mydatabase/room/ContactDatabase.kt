package modulo_05.sprint.mydatabase.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 3, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDAO
}