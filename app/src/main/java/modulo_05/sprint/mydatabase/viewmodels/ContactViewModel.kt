package modulo_05.sprint.mydatabase.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import modulo_05.sprint.mydatabase.model.Contact
import modulo_05.sprint.mydatabase.repository.ContactRepository
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val contactRepository: ContactRepository) : ViewModel() {
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts = _contacts.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.getAllContacts().collect {
                if(it.isNullOrEmpty()) {
                    _contacts.value = emptyList()
                } else {
                    _contacts.value = it

                }
            }
        }
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.addContact(contact)
        }
    }
    fun updateContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.updateContact(contact)
        }
    }
    fun deleteContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.deleteContact(contact)
        }
    }


}