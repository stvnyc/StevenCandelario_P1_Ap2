package edu.ucne.stevencandelario_p1_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.stevencandelario_p1_ap2.presentation.navigation.NavHostExamen
import edu.ucne.stevencandelario_p1_ap2.ui.theme.StevenCandelario_P1_Ap2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StevenCandelario_P1_Ap2Theme {
                val navHost = rememberNavController()
                NavHostExamen(navHost)
            }
        }
    }
}