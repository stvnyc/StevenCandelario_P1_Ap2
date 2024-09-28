package edu.ucne.stevencandelario_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.stevencandelario_p1_ap2.data.local.dao.ventasDao
import edu.ucne.stevencandelario_p1_ap2.data.local.entities.ventaEntity

@Database(
    entities = [
        ventaEntity::class,
    ],
    version = 5,
    exportSchema = false
)

abstract class  PrimerParcialDb : RoomDatabase() {
    abstract fun ventaDao(): ventasDao
}