package modulo_05.sprint.mydatabase.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import modulo_05.sprint.mydatabase.model.Contact


@Dao
interface ContactDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactById(id: Int): Flow<Contact>

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<Contact>>
}