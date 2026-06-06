package com.funeraria.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.funeraria.app.ui.theme.*
import com.funeraria.app.ui.viewmodel.*
import com.funeraria.app.navigation.Screen

data class HomeModule(
    val name: String,
    val icon: ImageVector,
    val color: Color,
    val route: String,
    val emoji: String
)

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    clienteVm: ClienteViewModel,
    contratoVm: ContratoViewModel,
    productoVm: ProductoViewModel,
    pagoVm: PagoViewModel,
    onNavigate: (String) -> Unit
) {
    val currentUser by authViewModel.currentUser.collectAsState()
    val clienteState by clienteVm.state.collectAsState()
    val contratoState by contratoVm.state.collectAsState()
    val productoState by productoVm.state.collectAsState()
    val pagoState by pagoVm.state.collectAsState()

    val modules = listOf(
        HomeModule("Clientes",    Icons.Filled.People,       Color(0xFF6C63FF), Screen.ClienteList.route, "👨‍👩‍👧"),
        HomeModule("Difuntos",    Icons.Filled.PersonOff,    Color(0xFF546E7A), Screen.DifuntoList.route, "🕊"),
        HomeModule("Productos",   Icons.Filled.Inventory2,   Color(0xFF42A5F5), Screen.ProductoList.route,"📦"),
        HomeModule("Servicios",   Icons.Filled.MiscellaneousServices, Color(0xFF26A69A), Screen.ServicioList.route,"🔧"),
        HomeModule("Contratos",   Icons.Filled.Description,  Color(0xFFD4AF37), Screen.ContratoList.route,"📋"),
        HomeModule("Empleados",   Icons.Filled.Badge,        Color(0xFFAB47BC), Screen.EmpleadoList.route,"👔"),
        HomeModule("Proveedores", Icons.Filled.LocalShipping,Color(0xFFEF5350), Screen.ProveedorList.route,"🚚"),
        HomeModule("Pagos",       Icons.Filled.Payments,     Color(0xFF4CAF50), Screen.PagoList.route,"💰"),
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(BackgroundColor),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // ── Header ──────────────────────────────────────────────────────────
        item {
            Box(
                modifier = Modifier.fillMaxWidth().background(Brush.verticalGradient(listOf(SurfaceColor, BackgroundColor)))
                    .padding(24.dp)
            ) {
                Column {
                    Text("Bienvenido,", style = MaterialTheme.typography.bodyLarge, color = TextSecondary)
                    Spacer(Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = currentUser?.username ?: "Usuario",
                            style = MaterialTheme.typography.headlineMedium,
                            color = TextPrimary,
                            fontWeight = FontWeight.Black
                        )
                        Spacer(Modifier.width(8.dp))
                        if (currentUser?.isStaff == true) {
                            Surface(color = AccentGold.copy(alpha = 0.2f), shape = RoundedCornerShape(20.dp)) {
                                Text("Admin", color = AccentGold, style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                            }
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                    Text("Sistema de Gestión Funeraria", style = MaterialTheme.typography.bodySmall, color = AccentGold)
                }
            }
        }

        // ── Stats Row ────────────────────────────────────────────────────────
        item {
            Spacer(Modifier.height(8.dp))
            Text("Resumen", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold,
                color = TextPrimary, modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(Modifier.height(12.dp))
            LazyRow(contentPadding = PaddingValues(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(listOf(
                    Triple("Clientes",  clienteState.totalCount.toString(),  Color(0xFF6C63FF)),
                    Triple("Contratos", contratoState.totalCount.toString(), Color(0xFFD4AF37)),
                    Triple("Productos", productoState.totalCount.toString(), Color(0xFF42A5F5)),
                    Triple("Pagos",     pagoState.totalCount.toString(),     Color(0xFF4CAF50)),
                )) { (label, count, color) ->
                    StatCard(label, count, color)
                }
            }
        }

        // ── Modules Grid ─────────────────────────────────────────────────────
        item {
            Spacer(Modifier.height(24.dp))
            Text("Módulos", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold,
                color = TextPrimary, modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(Modifier.height(12.dp))
        }

        items(modules.chunked(2)) { row ->
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                row.forEach { m ->
                    ModuleCard(m, onClick = { onNavigate(m.route) }, modifier = Modifier.weight(1f))
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun StatCard(label: String, count: String, color: Color) {
    Surface(color = SurfaceColor, shape = RoundedCornerShape(18.dp), modifier = Modifier.width(120.dp)) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start) {
            Box(modifier = Modifier.size(8.dp).clip(RoundedCornerShape(4.dp)).background(color))
            Spacer(Modifier.height(8.dp))
            Text(count, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black, color = TextPrimary)
            Text(label, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
        }
    }
}

@Composable
private fun ModuleCard(module: HomeModule, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        color = SurfaceColor,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(18.dp), horizontalAlignment = Alignment.Start) {
            Box(
                modifier = Modifier.size(48.dp).clip(RoundedCornerShape(14.dp)).background(module.color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(module.icon, null, tint = module.color, modifier = Modifier.size(26.dp))
            }
            Spacer(Modifier.height(12.dp))
            Text(module.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = TextPrimary)
            Spacer(Modifier.height(4.dp))
            Text("Ver módulo →", style = MaterialTheme.typography.labelSmall, color = TextTertiary)
        }
    }
}
