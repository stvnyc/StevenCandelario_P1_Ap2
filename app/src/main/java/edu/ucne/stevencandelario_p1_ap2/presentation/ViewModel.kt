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
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getVenta()
    }

    fun save(){
        viewModelScope.launch {
            if (_uiState.value.nombreEmpresa.isBlank() && _uiState.value.galones.toString().isBlank()){
                _uiState.update {
                    it.copy(message = "Debe ingresar los campos")
                }
            } else  {
                ventaRepository.save(_uiState.value.toEntity())
            }
        }
    }

    private fun getVenta(){
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
        }
    }

    fun selectedVenta(ventasId:Int){
        viewModelScope.launch {
            if (ventasId > 0){
                val venta = ventaRepository.find(ventasId)
                _uiState.update {
                    it.copy(
                        ventasId = venta?.ventasId,
                        nombreEmpresa = venta?.nombreEmpresa ?: "",
                        galones = venta?.galones
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
                totalDescontado = null,
                total = null,
                message = null
            )
        }
    }

    fun onNombreEmpresaChange(nombreEmpresa: String) {
        _uiState.update {
            it.copy(nombreEmpresa = nombreEmpresa)
        }
    }

    fun onGalonChange(galones: String) {
        val galon = galones.toIntOrNull()
        _uiState.update {
            it.copy(galones = galon)
        }
        calculateTotals()
    }

    fun onDescuentoGalonChange(descuestoGalon: String) {
        val descuento = descuestoGalon.toIntOrNull()
        _uiState.update {
            it.copy(descuestoGalon = descuento)
        }
        calculateTotals()
    }

    fun onPrecioChange(precio: String) {
        val precio = precio.toIntOrNull()
        _uiState.update {
            it.copy(precio = precio)
        }
        calculateTotals()
    }

    fun onTotalDescontadoChange(totalDescontado: String) {
        val totalD = totalDescontado.toIntOrNull()
        _uiState.update {
            it.copy(totalDescontado = totalD)
        }
    }

    fun onVentasIdChange(ventasId: Int) {
        _uiState.update {
            it.copy(ventasId = ventasId)
        }
    }

    fun calculateTotals() {
        val galones = _uiState.value.galones ?: 0
        val descuentoGalon = _uiState.value.descuestoGalon ?: 0
        val precio = _uiState.value.precio ?: 0

        val totalDescontado = galones * descuentoGalon

        val total = (galones * precio) - totalDescontado

        _uiState.update {
            it.copy(
                totalDescontado = totalDescontado,
                total = total.toFloat()
            )
        }
    }

}