package edu.ucne.stevencandelario_p1_ap2.presentation.navigation.ventas

import edu.ucne.stevencandelario_p1_ap2.data.local.entities.ventaEntity

data class UiState(
    val ventasId: Int? = null,
    val nombreEmpresa: String = "",
    val galones: Double? = null,
    val descuestoGalon: Double? = null,
    val precio: Double? = null,
    val totalDescontado: Double = 0.0,
    val total: Double = 0.0,
    val message: String? = null,
    val venta: List<ventaEntity> = emptyList()
)

fun UiState.toEntity() = ventaEntity(
    ventasId = ventasId,
    nombreEmpresa = nombreEmpresa,
    galones = galones,
    descuestoGalon = descuestoGalon,
    precio = precio,
    totalDescontado = totalDescontado,
    total = total
)