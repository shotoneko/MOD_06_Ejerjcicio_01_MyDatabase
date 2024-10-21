package modulo_05.sprint.mydatabase.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import modulo_05.sprint.mydatabase.repository.ContactRepository
import modulo_05.sprint.mydatabase.room.Contact
import java.text.NumberFormat
import javax.inject.Inject

/**
 * ViewModel to validate and insert items in the Room database.
 */
@HiltViewModel
class ItemEntryViewModel @Inject constructor(private val contactRepository: ContactRepository) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var itemUiState by mutableStateOf(ContactUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(itemDetails: ContactDetail) {
        itemUiState =
            ContactUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            contactRepository.insertContact(itemUiState.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: ContactDetail = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && phone.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
data class ContactUiState(
    val itemDetails: ContactDetail = ContactDetail(),
    val isEntryValid: Boolean = false
)

data class ContactDetail(
    val id: Int = 0,
    val name: String = "",
    val phone: String = "",
    val email: String? = "",
    val profile_picture: String? = "",
    val date_of_birth: String? = ""
)

/**
 * Extension function
 */
fun ContactDetail.toItem(): Contact = Contact(
    id = id,
    name = name,
    phone = phone,
    email = email?:"",
    profilePicture = profile_picture?: "",
    dateOfBirth = date_of_birth?: ""
)

/**
 * Extension function
 */
fun Contact.toItemUiState(isEntryValid: Boolean = false): ContactUiState = ContactUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function
 */
fun Contact.toItemDetails(): ContactDetail = ContactDetail(
    id = id,
    name = name,
    phone = phone,
    email = email,
    profile_picture = profilePicture,
    date_of_birth = dateOfBirth
)
