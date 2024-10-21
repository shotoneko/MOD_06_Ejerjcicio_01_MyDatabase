package modulo_05.sprint.mydatabase.repository

import kotlinx.coroutines.flow.Flow
import modulo_05.sprint.mydatabase.room.Contact
import modulo_05.sprint.mydatabase.room.ContactDAO
import javax.inject.Inject


class OfflineContactRepository @Inject constructor(private val contactDao: ContactDAO): ContactRepository {
    override fun getAllContacts(): Flow<List<Contact>> = contactDao.getAllContacts()
    override fun getContactById(id: Int): Flow<Contact> = contactDao.getContactById(id)
    override suspend fun insertContact(contact: Contact) = contactDao.insert(contact)
    override suspend fun updateContact(contact: Contact) = contactDao.update(contact)
    override suspend fun deleteContact(contact: Contact) = contactDao.delete(contact)


}