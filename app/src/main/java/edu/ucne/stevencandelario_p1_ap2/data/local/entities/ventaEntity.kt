package edu.ucne.stevencandelario_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ventas")
data class ventaEntity(
    @PrimaryKey
    val ventasId: Int? = null,
    val nombreEmpresa: String = "",
    val galones: Int? = null,
    val descuestoGalon: Int? = null,
    val precio: Int? = null,
    val totalDescontado: Int? = null,
    val total: Float? = null
)