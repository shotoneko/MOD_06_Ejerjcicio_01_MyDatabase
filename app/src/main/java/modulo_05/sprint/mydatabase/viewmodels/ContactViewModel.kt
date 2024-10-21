package modulo_05.sprint.mydatabase.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import modulo_05.sprint.mydatabase.room.Contact
import modulo_05.sprint.mydatabase.repository.ContactRepository
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val contactRepository: ContactRepository) : ViewModel() {

    var contactUIState by mutableStateOf(ContactUIState())
        private set

    private fun validateInput(uiState: ContactDetails = contactUIState.contactDetails): Boolean {
        return uiState.name.isNotEmpty() && uiState.phone.isNotEmpty()
    }
    suspend fun saveContact() {
        if (validateInput()) {
            contactRepository.insertContact(contactUIState.contactDetails.toContact())
        }
    }
    fun updateUiState(contactDetails: ContactDetails) {
        contactUIState =
            ContactUIState(
                contactDetails = contactDetails,
                isEntryValid = validateInput(contactDetails)
            )
    }
}

data class ContactUIState(
    val contactDetails: ContactDetails = ContactDetails(),
    val isEntryValid: Boolean = false
)
data class ContactDetails(
    val id: Int = 0,
    val name: String = "",
    val phone: String = "",
    val email: String? = null,
    val profilePicture: String? = null,
    val dateOfBirth: String? = null
)
fun ContactDetails.toContact(): Contact = Contact(
    id = id,
    name = name,
    phone = phone ?: "",
    email = email ?: "",
    profilePicture ?: "",
    dateOfBirth = dateOfBirth ?: "",
)
fun Contact.toContactDetails(): ContactDetails = ContactDetails(
    id = id,
    name = name,
    phone = phone,
    email = email,
    profilePicture = profilePicture,
    dateOfBirth = dateOfBirth
)
fun Contact.toContactUIState(isEntryValid: Boolean = false): ContactUIState = ContactUIState(
    contactDetails = this.toContactDetails(),
    isEntryValid = isEntryValid
)