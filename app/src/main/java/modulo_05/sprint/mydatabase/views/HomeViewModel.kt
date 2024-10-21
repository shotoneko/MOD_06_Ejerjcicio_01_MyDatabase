package modulo_05.sprint.mydatabase.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import modulo_05.sprint.mydatabase.repository.ContactRepository
import modulo_05.sprint.mydatabase.room.Contact
import javax.inject.Inject
/**
 * Recibe todos los contactos de la base de datos y los mapea a [HomeUiState]
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val contactRepository: ContactRepository) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        contactRepository.getAllContacts().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun deleteItem(contact: Contact) {
        viewModelScope.launch {
            contactRepository.deleteContact(contact)
        }
    }
    fun editItem(contact: Contact) {
        // Navigate to the edit screen, passing the contact ID// You'll need to implement the navigation logic in your UI
        // For example, using Jetpack Compose Navigation:
        // navController.navigate("edit_screen/${contact.id}")
    }

}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<Contact> = listOf())