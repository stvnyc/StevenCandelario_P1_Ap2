package edu.ucne.stevencandelario_p1_ap2.data.repository

import edu.ucne.stevencandelario_p1_ap2.data.local.dao.ventasDao
import edu.ucne.stevencandelario_p1_ap2.data.local.entities.ventaEntity
import javax.inject.Inject

class ventaRepository @Inject constructor(
    private val ventasDao: ventasDao
) {
    suspend fun save(algo: ventaEntity) = ventasDao.save(algo)
    suspend fun delete(algo: ventaEntity) = ventasDao.delete(algo)
    fun getAll() = ventasDao.getAll()
    suspend fun find(id: Int) = ventasDao.find(id)
}