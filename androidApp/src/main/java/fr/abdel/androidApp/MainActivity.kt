package fr.abdel.androidApp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import fr.abdel.shared.Greeting
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.navigation.compose.*

fun greet(): String {
    return Greeting().greeting()
}

sealed class Screens(val route: String, val label: String, val icon: ImageVector? = null) {
    object DessertsScreen : Screens("Desserts", "Desserts", Icons.Default.List)
}

class MainActivity : AppCompatActivity() {
    //@SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainLayout()
            }
        }
    }
}

@Composable
fun DessertListView(bottomBar: @Composable () -> Unit) {
    Text(greet())
}


@Composable
fun MainLayout() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(Screens.DessertsScreen)
    val bottomBar: @Composable () -> Unit = { BottomNavigation(navController, bottomNavigationItems) }

    NavHost(navController, startDestination = Screens.DessertsScreen.route) {
        composable(Screens.DessertsScreen.route) {
            DessertListView(bottomBar)
        }
    }
}

@Composable
private fun BottomNavigation(
    navController: NavHostController,
    items: List<Screens>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.icon?.let { Icon(screen.icon, screen.label) } },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }

}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val KEY_ROUTE =  "android-support-nav:controller:route"
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}
