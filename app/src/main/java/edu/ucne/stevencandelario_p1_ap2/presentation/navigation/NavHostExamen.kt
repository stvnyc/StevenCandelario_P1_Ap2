package edu.ucne.stevencandelario_p1_ap2.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostExamen(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.ListScreen) {
        composable<Screen.ListScreen> {
            Scaffold() { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    OutlinedButton(onClick = {navHostController.navigate(Screen.Registro(0))}
                    ){
                        Text(text = "Ir")
                    }
                }
            }
        }

        composable<Screen.Registro> {
            Scaffold() { innerPadding ->
                Text(
                    text = "Estas en la siguiente pantalla",
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}