package edu.ucne.stevencandelario_p1_ap2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.stevencandelario_p1_ap2.presentation.navigation.ventas.VentasListScreen
import edu.ucne.stevencandelario_p1_ap2.presentation.navigation.ventas.VentasRegistroScreen

@Composable
fun VentasNavHost(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.VentasListScreen) {
        composable<Screen.VentasListScreen> {
            VentasListScreen(
                createVenta = { navHostController.navigate(Screen.VentasRegistroScreen(0)) },
                goToVentasRegistroScreen = { navHostController.navigate(Screen.VentasRegistroScreen(it)) }
            )
        }

        composable<Screen.VentasRegistroScreen> {
            val ventasId = it.toRoute<Screen.VentasRegistroScreen>().Id
            VentasRegistroScreen(
                onGoToVentasListScreen = {navHostController.navigateUp() },
                goBack = { navHostController.navigateUp() },
                ventasId = ventasId
            )
        }
    }
}