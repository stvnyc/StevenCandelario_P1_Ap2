package edu.ucne.stevencandelario_p1_ap2.presentation.navigation.ventas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.stevencandelario_p1_ap2.data.local.entities.ventaEntity
import edu.ucne.stevencandelario_p1_ap2.presentation.ViewModel

@Composable
fun VentasListScreen(
    viewModel: ViewModel = hiltViewModel(),
    goToVentasRegistroScreen: (Int) -> Unit,
    createVenta: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    VentasListBodyScreen(
        uiState,
        goToVentasRegistroScreen,
        createVenta
    )
}

@Composable
fun VentasListBodyScreen(
    uiState: UiState,
    goToVentasRegistroScreen: (Int) -> Unit,
    createVenta: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = createVenta,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar")
            }
        }
    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = "Lista de Ventas",
                fontSize = 28.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(24.dp)
            )
            Row(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(
                    "Id",
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    "Nombre de Empresa",
                    modifier = Modifier
                        .weight(2.5f)
                        .align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    "Galones",
                    modifier = Modifier
                        .weight(2.5f)
                        .align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    "Descuento de Galon",
                    modifier = Modifier
                        .weight(2.5f)
                        .align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    "Precio",
                    modifier = Modifier
                        .weight(2.5f)
                        .align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    "Total descontado",
                    modifier = Modifier
                        .weight(2.5f)
                        .align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    "Total",
                    modifier = Modifier
                        .weight(2.5f)
                        .align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
            ) {
                items(uiState.venta) {
                    VentaRow(it,goToVentasRegistroScreen)
                }
            }
        }
    }
}

@Composable
private fun VentaRow(
    it:ventaEntity,
    goToVentasRegistroScreen: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                goToVentasRegistroScreen(it.ventasId?:0)
            }
            .padding(vertical = 15.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = it.ventasId.toString(),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .weight(2.5f),
            text = it.nombreEmpresa,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .weight(2.5f),
            text = it.galones.toString(),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .weight(2.5f),
            text = it.descuestoGalon.toString(),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .weight(2.5f),
            text = it.precio.toString(),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .weight(2.5f),
            text = it.totalDescontado.toString(),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .weight(2.5f),
            text = it.total.toString(),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
    HorizontalDivider()
}