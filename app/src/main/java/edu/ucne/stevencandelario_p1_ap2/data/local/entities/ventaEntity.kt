package edu.ucne.stevencandelario_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ventas")
data class ventaEntity(
    @PrimaryKey
    val ventasId: Int? = null,
    val nombreEmpresa: String = "",
    val galones: Double? = null,
    val descuestoGalon: Double? = null,
    val precio: Double? = null,
    val totalDescontado: Double = 0.0,
    val total: Double = 0.0
)