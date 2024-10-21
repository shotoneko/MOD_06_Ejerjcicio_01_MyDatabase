package modulo_05.sprint.mydatabase.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import modulo_05.sprint.mydatabase.viewmodels.ContactViewModel

//@Composable
//fun NavManager(viewModel: ContactViewModel) {
//    val navController = rememberNavController()
//    NavHost(navController, startDestination = "Home") {
//        composable("Home") {
//            HomeScreen(viewModel, navController)
//        }
//
//    }
//}