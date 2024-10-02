package modulo_05.sprint.mydatabase.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import modulo_05.sprint.mydatabase.model.Contact
import modulo_05.sprint.mydatabase.room.ContactDatabaseDAO
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDatabaseDAO: ContactDatabaseDAO)  {
    suspend fun addContact(contact: Contact) = contactDatabaseDAO.insert(contact)
    suspend fun updateContact(contact: Contact) = contactDatabaseDAO.update(contact)
    suspend fun deleteContact(contact: Contact) = contactDatabaseDAO.delete(contact)
    fun getAllContacts(): Flow<List<Contact>> = contactDatabaseDAO.getAllContacts().flowOn(
        Dispatchers.IO).conflate()
    fun getContactById(id: Int): Flow<Contact> = contactDatabaseDAO.getContactById(id).flowOn(
        Dispatchers.IO).conflate()
}