package edu.ucne.stevencandelario_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ventas")
data class ventaEntity(
    @PrimaryKey
    val ventasId: Int? = null,
    val nombreEmpresa: String = "",
    val galones: Float? = null,
    val descuestoGalon: Float? = null,
    val precio: Float? = null,
    val totalDescontado: Float? = null,
    val total: Float? = null
)