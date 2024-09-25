package edu.ucne.stevencandelario_p1_ap2.data.repository

import edu.ucne.stevencandelario_p1_ap2.data.local.dao.algoDao
import edu.ucne.stevencandelario_p1_ap2.data.local.entities.algoEntity
import javax.inject.Inject

class AlgoRepository @Inject constructor(
    private val algoDao: algoDao
) {
    suspend fun save(algo: algoEntity) = algoDao.save(algo)
    suspend fun delete(algo: algoEntity) = algoDao.delete(algo)
    fun getAll() = algoDao.getAll()
    suspend fun find(id: Int) = algoDao.find(id)
}