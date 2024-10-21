package modulo_05.sprint.mydatabase.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import modulo_05.sprint.mydatabase.R
import modulo_05.sprint.mydatabase.room.Contact
import modulo_05.sprint.mydatabase.viewmodels.ContactViewModel
import modulo_05.sprint.mydatabase.navigation.NavigationDestination
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import modulo_05.sprint.mydatabase.MyTopAppBar
import modulo_05.sprint.mydatabase.repository.ContactRepository
import javax.inject.Inject

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}
val LocalHomeViewModel
        = compositionLocalOf<HomeViewModel> { error("No se ha proporcionado HomeViewModel") }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    CompositionLocalProvider(LocalHomeViewModel provides viewModel) {
        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MyTopAppBar(
                    title = stringResource(HomeDestination.titleRes),
                    canNavigateBack = false,
                    scrollBehavior = scrollBehavior
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = navigateToItemEntry,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.item_entry_title)
                    )
                }
            },
        ) { innerPadding ->
            HomeBody(
                itemList = homeUiState.itemList,
                onItemClick = navigateToItemUpdate,
                modifier = modifier.fillMaxSize(),
                contentPadding = innerPadding,
            )
        }
    }
}

@Composable
private fun HomeBody(
    itemList: List<Contact>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (itemList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        } else {
            ContactList(
                itemList = itemList,
               // onItemClick = { onItemClick(it.id) }
                onItemClick = onItemClick,
                contentPadding = contentPadding,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun ContactList(
    itemList: List<Contact>,
    onItemClick: (Int) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(
            items = itemList,
            key = { it.id }
        )
        { item ->
            ItemCard(
                contact = item,
                onEditClick = { onItemClick(item.id) },
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item.id) }
            )
        }

    }
}

val commonModifier = Modifier
    .fillMaxWidth()
    .padding(8.dp)

@Composable
private fun ItemCard(
    contact: Contact,
    onEditClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val localViewModel = LocalHomeViewModel.current

    val fields = listOf(
        // "Name" to contact.name,
        "Phone" to contact.phone,
        "Email" to (contact.email ?: ""),
        "DOB"   to (contact.dateOfBirth.toString() ?: "")
    )
    Card(
        modifier = commonModifier.border(1.dp, Color.Black)

    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            ContactNameField(
                value = contact.name,
                imageUrl = "https://picsum.photos/200/300"
            )

            fields.forEach { (etiqueta, campo) ->
                ContactField(
                    key = etiqueta,
                    value = campo
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Icon(
                    modifier = Modifier.clickable {
                        localViewModel.editItem(contact)
                        onEditClick(contact.id)
                                                  },
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.item_edit)
                )
                Icon(
                    modifier = Modifier.clickable { localViewModel.deleteItem(contact) },
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.item_delete)
                )

            }

        }
    }
}

@Composable
fun ContactField(
    key: String,
    value: String
)
{
    Box(
        modifier = commonModifier.background(Color.LightGray)
    )
    {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("$key: ")
                }
                append(value)
            }
        )
    }
}

@Composable
fun ContactNameField(
    value: String,
    imageUrl: String
)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(colorResource(id = R.color.darkblue))
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray) // Placeholder color
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = value,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right
            )
        }
    }
}