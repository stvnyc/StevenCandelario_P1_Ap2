package edu.ucne.stevencandelario_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.stevencandelario_p1_ap2.data.local.entities.ventaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ventasDao {
    @Upsert
    suspend fun save(venta: ventaEntity)

    @Query(
        """
        SELECT * FROM ventas WHERE ventasId=:id
        LIMIT 1
    """
    )
    suspend fun find(id: Int): ventaEntity

    @Delete
    suspend fun delete(venta: ventaEntity)
    @Query("""SELECT * FROM ventas""")
    fun getAll(): Flow<List<ventaEntity>>
}