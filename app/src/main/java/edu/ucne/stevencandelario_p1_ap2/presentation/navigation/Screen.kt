package edu.ucne.stevencandelario_p1_ap2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object VentasListScreen : Screen()
    @Serializable
    data class VentasRegistroScreen(val Id: Int) : Screen()
}