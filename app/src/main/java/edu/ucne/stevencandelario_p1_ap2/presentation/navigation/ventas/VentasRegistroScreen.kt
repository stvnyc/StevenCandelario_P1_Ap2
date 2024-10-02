package edu.ucne.stevencandelario_p1_ap2.presentation.navigation.ventas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.stevencandelario_p1_ap2.presentation.ViewModel

@Composable
fun VentasRegistroScreen(
    viewModel: ViewModel = hiltViewModel(),
    goBack: () -> Unit,
    onGoToVentasListScreen: () -> Unit,
    ventasId: Int
) {

    LaunchedEffect(ventasId) {
        viewModel.selectedVenta(ventasId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    VentasRegistroBodyScreen(
        uiState = uiState,
        onNombreEmpresaChange = viewModel::onNombreEmpresaChange,
        onGalonChange = viewModel::onGalonChange,
        onDescuentoGalonChange = viewModel::onDescuentoGalonChange,
        onPrecioChange = viewModel::onPrecioChange,
        saveVenta = {
            viewModel.save()
        },
        nuevaVenta = viewModel::nuevo,
        onGoToVentasListScreen = onGoToVentasListScreen,
        goBack = goBack,
        ventasId = ventasId
    )
}

@Composable
fun VentasRegistroBodyScreen(
    uiState: UiState,
    onNombreEmpresaChange: (String) -> Unit,
    onGalonChange: (Double) -> Unit,
    onDescuentoGalonChange: (Double) -> Unit,
    onPrecioChange: (Double) -> Unit,
    saveVenta: () -> Unit,
    nuevaVenta: () -> Unit,
    onGoToVentasListScreen: () -> Unit,
    goBack: () -> Unit,
    ventasId: Int
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onGoToVentasListScreen,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = if (ventasId > 0) "Editar Ventas" else "Registrar Ventas")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    label = { Text(text = "Descripción") },
                    value = uiState.nombreEmpresa,
                    onValueChange = onNombreEmpresaChange,
                    modifier = Modifier.fillMaxWidth()
                )
                if (uiState.messageNombreEmpresa != null) {
                    Text(
                        text = uiState.messageNombreEmpresa,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }

                OutlinedTextField(
                    label = { Text(text = "Galones") },
                    value = uiState.galones?.toString() ?: "",
                    onValueChange = {onGalonChange(it.toDouble())},
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                if (uiState.messageGalones != null) {
                    Text(
                        text = uiState.messageGalones,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }

                OutlinedTextField(
                    label = { Text(text = "Descuento por Galón") },
                    value = uiState.descuestoGalon?.toString() ?: "",
                    onValueChange = { onDescuentoGalonChange(it.toDouble()) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                if (uiState.messageDescuestoGalon != null) {
                    Text(
                        text = uiState.messageDescuestoGalon,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }

                OutlinedTextField(
                    label = { Text(text = "Precio") },
                    value = uiState.precio?.toString() ?: "",
                    onValueChange = { onPrecioChange(it.toDouble()) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                if (uiState.messagePrecio != null) {
                    Text(
                        text = uiState.messagePrecio,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }

                OutlinedTextField(
                    label = { Text(text = "Total descontado") },
                    value = uiState.totalDescontado?.toString() ?: "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )

                OutlinedTextField(
                    label = { Text(text = "Total") },
                    value = uiState.total?.toString() ?: "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )

                Text(
                    text = uiState.message.orEmpty(),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleMedium
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(onClick = nuevaVenta) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                        Text(text = "Nuevo")
                    }

                    OutlinedButton(onClick = saveVenta) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Text(text = if (ventasId > 0) "Actualizar" else "Guardar")
                    }
                }
            }
        }
    }
}
