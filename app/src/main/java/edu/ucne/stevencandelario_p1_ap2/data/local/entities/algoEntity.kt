package edu.ucne.stevencandelario_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "algos")
data class algoEntity(
    @PrimaryKey
    val algoId: Int? = null
)