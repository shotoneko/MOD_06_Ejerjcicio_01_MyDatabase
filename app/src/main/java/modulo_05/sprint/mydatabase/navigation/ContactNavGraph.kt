package modulo_05.sprint.mydatabase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import modulo_05.sprint.mydatabase.views.HomeDestination
import modulo_05.sprint.mydatabase.views.HomeScreen
import modulo_05.sprint.mydatabase.views.ItemEditDestination
import modulo_05.sprint.mydatabase.views.ItemEditScreen
import modulo_05.sprint.mydatabase.views.ItemEntryDestination
import modulo_05.sprint.mydatabase.views.ItemEntryScreen

@Composable
fun ContactNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    )
    {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToItemUpdate = {navController.navigate("${ItemEditDestination.route}/${it}") })
        }
        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
            )
        }
        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {type = NavType.IntType})
        )
        {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }



//        composable(
//            route = ItemDetailsDestination.routeWithArgs,
//            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
//                type = NavType.IntType
//            })
//        ) {
//            ItemDetailsScreen(
//                navigateToEditItem =
//                {
//                    navController.navigate("${ItemEditDestination.route}/$it")
//                },
//                navigateBack = { navController.navigateUp() })
//        }
//        composable(
//            route = ItemEditDestination.routeWithArgs,
//            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
//                type = NavType.IntType
//            })
//        ) {
//            ItemEditScreen(navigateBack = { navController.popBackStack() },
//                onNavigateUp = { navController.navigateUp() })
//        }
    }
}