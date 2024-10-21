package modulo_05.sprint.mydatabase.repository

import kotlinx.coroutines.flow.Flow
import modulo_05.sprint.mydatabase.room.Contact

interface ContactRepository  {

    fun getAllContacts(): Flow<List<Contact>>
    fun getContactById(id: Int): Flow<Contact>

    suspend fun insertContact(contact: Contact)
    suspend fun updateContact(contact: Contact)
    suspend fun deleteContact(contact: Contact)
}