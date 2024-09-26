package edu.ucne.stevencandelario_p1_ap2.presentation.navigation.ventas

import edu.ucne.stevencandelario_p1_ap2.data.local.entities.ventaEntity

data class UiState(
    val ventasId: Int? = null,
    val nombreEmpresa: String = "",
    val galones: Int? = null,
    val descuestoGalon: Int? = null,
    val precio: Int? = null,
    val totalDescontado: Int? = null,
    val total: Float? = null,
    val message: String? = null,
    val venta: List<ventaEntity> = emptyList()
)

fun UiState.toEntity() = ventaEntity(
    ventasId = ventasId,
    nombreEmpresa = nombreEmpresa,
    galones = galones ?: 0,
    descuestoGalon = descuestoGalon,
    precio = precio,
    totalDescontado = totalDescontado,
    total = total
)