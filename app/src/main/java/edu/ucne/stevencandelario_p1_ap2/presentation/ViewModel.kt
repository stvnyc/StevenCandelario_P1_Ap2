// ViewModel.kt
package edu.ucne.stevencandelario_p1_ap2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.stevencandelario_p1_ap2.data.repository.ventaRepository
import edu.ucne.stevencandelario_p1_ap2.presentation.navigation.ventas.UiState
import edu.ucne.stevencandelario_p1_ap2.presentation.navigation.ventas.toEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val ventaRepository: ventaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getVenta()
    }

    fun save() {
        viewModelScope.launch {
            val nombreEmpresa = _uiState.value.nombreEmpresa
            val galon = _uiState.value.galones
            val descuentoGalon = _uiState.value.descuestoGalon
            val precio = _uiState.value.precio

            if (nombreEmpresa.isBlank() || galon == null || descuentoGalon == null || precio == null) {
                _uiState.update {
                    it.copy(message = "Debe completar todos los campos")
                }
            } else {
                ventaRepository.save(_uiState.value.toEntity())
                _uiState.update {
                    it.copy(message = "Guardado exitosamente")
                }
            }
        }
    }

    private fun getVenta() {
        viewModelScope.launch {
            ventaRepository.getAll().collect { venta ->
                _uiState.update {
                    it.copy(venta = venta)
                }
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            ventaRepository.delete(_uiState.value.toEntity())
            _uiState.update {
                it.copy(message = "Venta eliminada exitosamente")
            }
        }
    }

    fun selectedVenta(ventasId: Int) {
        viewModelScope.launch {
            if (ventasId > 0) {
                val venta = ventaRepository.find(ventasId)
                _uiState.update {
                    it.copy(
                        ventasId = venta.ventasId,
                        nombreEmpresa = venta.nombreEmpresa,
                        galones = venta.galones,
                        descuestoGalon = venta.descuestoGalon,
                        precio = venta.precio,
                        totalDescontado = venta.totalDescontado,
                        total = venta.total
                    )
                }
            }
        }
    }

    fun nuevo() {
        _uiState.update {
            it.copy(
                ventasId = null,
                nombreEmpresa = "",
                galones = null,
                descuestoGalon = null,
                precio = null,
                totalDescontado = 0.0,
                total = 0.0,
                message = null
            )
        }
    }

    fun onNombreEmpresaChange(nombreEmpresa: String) {
        _uiState.update {
            it.copy(nombreEmpresa = nombreEmpresa)
        }
    }

    fun onGalonChange(galones: Double) {
        val galon = galones
        _uiState.update {
            it.copy(galones = galones)
        }
        calculateTotals()
    }

    fun onDescuentoGalonChange(descuestoGalon: Double) {
        val descuento = descuestoGalon
        _uiState.update {
            it.copy(descuestoGalon = descuestoGalon)
        }
        calculateTotals()
    }

    fun onPrecioChange(precio: Double) {
        val precioDouble = precio
        _uiState.update {
            it.copy(precio = precio)
        }
        calculateTotals()
    }

    fun onVentasIdChange(ventasId: Int) {
        _uiState.update {
            it.copy(ventasId = ventasId)
        }
    }

    private fun calculateTotals() {
        val galones = _uiState.value.galones ?: 0.0
        val descuentoGalon = _uiState.value.descuestoGalon ?: 0.0
        val precio = _uiState.value.precio ?: 0.0

        val totalDescontado = galones * descuentoGalon
        val total = (galones * precio) - totalDescontado

        _uiState.update {
            it.copy(
                totalDescontado = totalDescontado,
                total = total
            )
        }
    }
}
