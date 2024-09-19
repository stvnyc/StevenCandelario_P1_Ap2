package edu.ucne.stevencandelario_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.stevencandelario_p1_ap2.data.local.entities.algoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface algoDao {
    @Upsert
    suspend fun save(algo: algoEntity)

    @Query("""
        SELECT * FROM algos WHERE algoId=:id
        LIMIT 1
    """
    )
    suspend fun find(id: Int): algoEntity

    @Delete
    suspend fun delete(algo: algoEntity)
    @Query("""SELECT * FROM algos""")
    fun getAll(): Flow<List<algoEntity>>
}