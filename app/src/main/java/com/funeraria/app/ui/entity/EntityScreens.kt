package com.funeraria.app.ui.entity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.funeraria.app.data.remote.dto.*
import com.funeraria.app.ui.components.*
import com.funeraria.app.ui.theme.*
import com.funeraria.app.ui.viewmodel.*

// ─── CLIENTE ─────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteListScreen(
    viewModel: ClienteViewModel,
    isAdmin: Boolean,
    onDetail: (Int) -> Unit,
    onNew: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    var search by remember { mutableStateOf("") }
    LaunchedEffect(search) {
        kotlinx.coroutines.delay(400)
        viewModel.loadItems(search.takeIf { it.isNotBlank() })
    }
    EntityListScaffold(
        title = "Clientes", total = state.totalCount, isAdmin = isAdmin, onNew = onNew,
        search = search, onSearch = { search = it }, isLoading = state.isLoading,
        error = state.error, onRetry = { viewModel.loadItems() },
        isEmpty = state.items.isEmpty() && !state.isLoading,
        hasMore = state.hasMore, onLoadMore = { viewModel.loadMore() }
    ) {
        state.items.forEach { c ->
            item {
                EntityCard(
                    title = c.nombreCompleto, subtitle = c.dniCedula,
                    detail = c.email ?: c.telefono ?: "Sin contacto",
                    icon = Icons.Filled.Person, iconTint = Color(0xFF6C63FF)
                ) { onDetail(c.id) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteDetailScreen(id: Int, viewModel: ClienteViewModel, isAdmin: Boolean, onBack: () -> Unit, onEdit: (Int) -> Unit) {
    val detail by viewModel.detail.collectAsState()
    LaunchedEffect(id) { viewModel.loadById(id) }
    var showDelete by remember { mutableStateOf(false) }
    val item = detail.item
    if (showDelete && item != null) ConfirmDeleteDialog(item.nombreCompleto, { viewModel.delete(id) { onBack() }; showDelete = false }, { showDelete = false })
    EntityDetailScaffold("Detalle Cliente", detail.isLoading, detail.error, isAdmin, onBack, { onEdit(id) }, { showDelete = true }) {
        item?.let {
            DetailField("Nombre completo", it.nombreCompleto, Icons.Filled.Person)
            HorizontalDivider(color = DividerColor)
            DetailField("DNI / Cédula", it.dniCedula, Icons.Filled.Badge)
            HorizontalDivider(color = DividerColor)
            DetailField("Teléfono", it.telefono, Icons.Filled.Phone)
            HorizontalDivider(color = DividerColor)
            DetailField("Email", it.email, Icons.Filled.Email)
            HorizontalDivider(color = DividerColor)
            DetailField("Dirección", it.direccion, Icons.Filled.LocationOn)
        }
    }
}

@Composable
fun ClienteFormScreen(id: Int?, viewModel: ClienteViewModel, onBack: () -> Unit) {
    val detail by viewModel.detail.collectAsState()
    val editing = id != null && id >= 0
    LaunchedEffect(id) { if (editing) viewModel.loadById(id!!) else viewModel.clearErrors() }
    var nombre by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    LaunchedEffect(detail.item) {
        detail.item?.let { c ->
            nombre = c.nombreCompleto; dni = c.dniCedula
            telefono = c.telefono ?: ""; email = c.email ?: ""; direccion = c.direccion ?: ""
        }
    }
    EntityFormScaffold(if (editing) "Editar Cliente" else "Nuevo Cliente", detail.isSaving, detail.saveError, onBack, {
        viewModel.save(id, ClienteRequest(nombre, dni, telefono.takeIf { it.isNotBlank() }, email.takeIf { it.isNotBlank() }, direccion.takeIf { it.isNotBlank() }), onBack)
    }, nombre.isNotBlank() && dni.isNotBlank()) {
        FunerariaTextField(nombre, { nombre = it }, "Nombre completo", placeholder = "Ej: Juan Pérez")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(dni, { dni = it }, "DNI / Cédula", placeholder = "1234567890")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(telefono, { telefono = it }, "Teléfono (opcional)", placeholder = "0999999999")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(email, { email = it }, "Email (opcional)", placeholder = "correo@ejemplo.com")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(direccion, { direccion = it }, "Dirección (opcional)", singleLine = false, maxLines = 3, placeholder = "Calle, ciudad")
    }
}

// ─── DIFUNTO ─────────────────────────────────────────────────────────────────
@Composable
fun DifuntoListScreen(viewModel: DifuntoViewModel, isAdmin: Boolean, onDetail: (Int) -> Unit, onNew: () -> Unit) {
    val state by viewModel.state.collectAsState()
    var search by remember { mutableStateOf("") }
    LaunchedEffect(search) { kotlinx.coroutines.delay(400); viewModel.loadItems(search.takeIf { it.isNotBlank() }) }
    EntityListScaffold("Difuntos", state.totalCount, isAdmin, onNew, search, { search = it }, state.isLoading, state.error, { viewModel.loadItems() }, state.items.isEmpty() && !state.isLoading, state.hasMore, { viewModel.loadMore() }) {
        state.items.forEach { d ->
            item { EntityCard(d.nombreCompleto, "Fallecido: ${d.fechaDefuncion}", d.causaFallecimiento ?: "Causa no especificada", Icons.Filled.PersonOff, Color(0xFF546E7A)) { onDetail(d.id) } }
        }
    }
}

@Composable
fun DifuntoDetailScreen(id: Int, viewModel: DifuntoViewModel, isAdmin: Boolean, onBack: () -> Unit, onEdit: (Int) -> Unit) {
    val detail by viewModel.detail.collectAsState()
    LaunchedEffect(id) { viewModel.loadById(id) }
    var showDelete by remember { mutableStateOf(false) }
    val item = detail.item
    if (showDelete && item != null) ConfirmDeleteDialog(item.nombreCompleto, { viewModel.delete(id) { onBack() }; showDelete = false }, { showDelete = false })
    EntityDetailScaffold("Detalle Difunto", detail.isLoading, detail.error, isAdmin, onBack, { onEdit(id) }, { showDelete = true }) {
        item?.let {
            DetailField("Nombre completo", it.nombreCompleto, Icons.Filled.PersonOff)
            HorizontalDivider(color = DividerColor)
            DetailField("Responsable", it.clienteResponsableNombre ?: it.clienteResponsable.toString(), Icons.Filled.Person)
            HorizontalDivider(color = DividerColor)
            DetailField("Fecha nacimiento", it.fechaNacimiento, Icons.Filled.Cake)
            HorizontalDivider(color = DividerColor)
            DetailField("Fecha defunción", it.fechaDefuncion, Icons.Filled.CalendarToday)
            HorizontalDivider(color = DividerColor)
            DetailField("Causa de fallecimiento", it.causaFallecimiento, Icons.Filled.MedicalServices)
            HorizontalDivider(color = DividerColor)
            DetailField("Lugar de velación", it.lugarVelacion, Icons.Filled.LocationOn)
        }
    }
}

@Composable
fun DifuntoFormScreen(id: Int?, viewModel: DifuntoViewModel, onBack: () -> Unit) {
    val detail by viewModel.detail.collectAsState()
    val editing = id != null && id >= 0
    LaunchedEffect(id) { if (editing) viewModel.loadById(id!!) else viewModel.clearErrors() }
    var nombre by remember { mutableStateOf("") }
    var clienteId by remember { mutableStateOf("") }
    var fechaNac by remember { mutableStateOf("") }
    var fechaDef by remember { mutableStateOf("") }
    var causa by remember { mutableStateOf("") }
    var lugar by remember { mutableStateOf("") }
    LaunchedEffect(detail.item) {
        detail.item?.let { d ->
            nombre = d.nombreCompleto; clienteId = d.clienteResponsable.toString()
            fechaNac = d.fechaNacimiento ?: ""; fechaDef = d.fechaDefuncion
            causa = d.causaFallecimiento ?: ""; lugar = d.lugarVelacion ?: ""
        }
    }
    EntityFormScaffold(if (editing) "Editar Difunto" else "Nuevo Difunto", detail.isSaving, detail.saveError, onBack, {
        viewModel.save(id, DifuntoRequest(clienteId.toIntOrNull() ?: 0, nombre, fechaNac.takeIf { it.isNotBlank() }, fechaDef, causa.takeIf { it.isNotBlank() }, lugar.takeIf { it.isNotBlank() }), onBack)
    }, nombre.isNotBlank() && clienteId.isNotBlank() && fechaDef.isNotBlank()) {
        FunerariaTextField(nombre, { nombre = it }, "Nombre completo")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(clienteId, { clienteId = it }, "ID del Cliente responsable", placeholder = "Ej: 1")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(fechaNac, { fechaNac = it }, "Fecha de nacimiento (YYYY-MM-DD)", placeholder = "2000-01-15")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(fechaDef, { fechaDef = it }, "Fecha de defunción (YYYY-MM-DD)", placeholder = "2024-01-15")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(causa, { causa = it }, "Causa de fallecimiento (opcional)")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(lugar, { lugar = it }, "Lugar de velación (opcional)")
    }
}

// ─── PRODUCTO ────────────────────────────────────────────────────────────────
@Composable
fun ProductoListScreen(viewModel: ProductoViewModel, isAdmin: Boolean, onDetail: (Int) -> Unit, onNew: () -> Unit) {
    val state by viewModel.state.collectAsState()
    var search by remember { mutableStateOf("") }
    LaunchedEffect(search) { kotlinx.coroutines.delay(400); viewModel.loadItems(search.takeIf { it.isNotBlank() }) }
    EntityListScaffold("Productos", state.totalCount, isAdmin, onNew, search, { search = it }, state.isLoading, state.error, { viewModel.loadItems() }, state.items.isEmpty() && !state.isLoading, state.hasMore, { viewModel.loadMore() }) {
        state.items.forEach { p ->
            item { EntityCard(p.nombre, p.categoria, "$ ${p.precio} · Stock: ${p.stockDisponible}", Icons.Filled.Inventory2, Color(0xFF42A5F5)) { onDetail(p.id) } }
        }
    }
}

@Composable
fun ProductoDetailScreen(id: Int, viewModel: ProductoViewModel, isAdmin: Boolean, onBack: () -> Unit, onEdit: (Int) -> Unit) {
    val detail by viewModel.detail.collectAsState()
    LaunchedEffect(id) { viewModel.loadById(id) }
    var showDelete by remember { mutableStateOf(false) }
    val item = detail.item
    if (showDelete && item != null) ConfirmDeleteDialog(item.nombre, { viewModel.delete(id) { onBack() }; showDelete = false }, { showDelete = false })
    EntityDetailScaffold("Detalle Producto", detail.isLoading, detail.error, isAdmin, onBack, { onEdit(id) }, { showDelete = true }) {
        item?.let {
            DetailField("Nombre", it.nombre, Icons.Filled.Inventory2)
            HorizontalDivider(color = DividerColor)
            DetailField("Categoría", it.categoria, Icons.Filled.Category)
            HorizontalDivider(color = DividerColor)
            DetailField("Precio", "$ ${it.precio}", Icons.Filled.AttachMoney)
            HorizontalDivider(color = DividerColor)
            DetailField("Stock disponible", it.stockDisponible.toString(), Icons.Filled.Warehouse)
            HorizontalDivider(color = DividerColor)
            DetailField("ID Proveedor", it.proveedor?.toString(), Icons.Filled.LocalShipping)
        }
    }
}

@Composable
fun ProductoFormScreen(id: Int?, viewModel: ProductoViewModel, onBack: () -> Unit) {
    val detail by viewModel.detail.collectAsState()
    val editing = id != null && id >= 0
    LaunchedEffect(id) { if (editing) viewModel.loadById(id!!) else viewModel.clearErrors() }
    var nombre by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Ataud") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("0") }
    var provId by remember { mutableStateOf("") }
    LaunchedEffect(detail.item) { detail.item?.let { p -> nombre = p.nombre; categoria = p.categoria; precio = p.precio.toString(); stock = p.stockDisponible.toString(); provId = p.proveedor?.toString() ?: "" } }
    val categorias = listOf("Ataud", "Urna", "Flores", "Accesorio")
    EntityFormScaffold(if (editing) "Editar Producto" else "Nuevo Producto", detail.isSaving, detail.saveError, onBack, {
        viewModel.save(id, ProductoRequest(nombre, categoria, precio.toDoubleOrNull() ?: 0.0, stock.toIntOrNull() ?: 0, provId.toIntOrNull()), onBack)
    }, nombre.isNotBlank() && precio.isNotBlank()) {
        FunerariaTextField(nombre, { nombre = it }, "Nombre del producto")
        Spacer(Modifier.height(14.dp))
        ChoiceDropdown("Categoría", categorias, categoria) { categoria = it }
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(precio, { precio = it }, "Precio", placeholder = "99.99")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(stock, { stock = it }, "Stock disponible", placeholder = "0")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(provId, { provId = it }, "ID Proveedor (opcional)", placeholder = "Ej: 1")
    }
}

// ─── SERVICIO ────────────────────────────────────────────────────────────────
@Composable
fun ServicioListScreen(viewModel: ServicioViewModel, isAdmin: Boolean, onDetail: (Int) -> Unit, onNew: () -> Unit) {
    val state by viewModel.state.collectAsState()
    var search by remember { mutableStateOf("") }
    LaunchedEffect(search) { kotlinx.coroutines.delay(400); viewModel.loadItems(search.takeIf { it.isNotBlank() }) }
    EntityListScaffold("Servicios", state.totalCount, isAdmin, onNew, search, { search = it }, state.isLoading, state.error, { viewModel.loadItems() }, state.items.isEmpty() && !state.isLoading, state.hasMore, { viewModel.loadMore() }) {
        state.items.forEach { s ->
            item { EntityCard(s.nombre, "Base: $ ${s.precioBase}", s.descripcion ?: "Sin descripción", Icons.Filled.MiscellaneousServices, Color(0xFF26A69A)) { onDetail(s.id) } }
        }
    }
}

@Composable
fun ServicioDetailScreen(id: Int, viewModel: ServicioViewModel, isAdmin: Boolean, onBack: () -> Unit, onEdit: (Int) -> Unit) {
    val detail by viewModel.detail.collectAsState()
    LaunchedEffect(id) { viewModel.loadById(id) }
    var showDelete by remember { mutableStateOf(false) }
    val item = detail.item
    if (showDelete && item != null) ConfirmDeleteDialog(item.nombre, { viewModel.delete(id) { onBack() }; showDelete = false }, { showDelete = false })
    EntityDetailScaffold("Detalle Servicio", detail.isLoading, detail.error, isAdmin, onBack, { onEdit(id) }, { showDelete = true }) {
        item?.let {
            DetailField("Nombre", it.nombre, Icons.Filled.MiscellaneousServices)
            HorizontalDivider(color = DividerColor)
            DetailField("Precio base", "$ ${it.precioBase}", Icons.Filled.AttachMoney)
            HorizontalDivider(color = DividerColor)
            DetailField("Descripción", it.descripcion, Icons.Filled.Description)
        }
    }
}

@Composable
fun ServicioFormScreen(id: Int?, viewModel: ServicioViewModel, onBack: () -> Unit) {
    val detail by viewModel.detail.collectAsState()
    val editing = id != null && id >= 0
    LaunchedEffect(id) { if (editing) viewModel.loadById(id!!) else viewModel.clearErrors() }
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    LaunchedEffect(detail.item) { detail.item?.let { s -> nombre = s.nombre; descripcion = s.descripcion ?: ""; precio = s.precioBase.toString() } }
    EntityFormScaffold(if (editing) "Editar Servicio" else "Nuevo Servicio", detail.isSaving, detail.saveError, onBack, {
        viewModel.save(id, ServicioRequest(nombre, descripcion.takeIf { it.isNotBlank() }, precio.toDoubleOrNull() ?: 0.0), onBack)
    }, nombre.isNotBlank() && precio.isNotBlank()) {
        FunerariaTextField(nombre, { nombre = it }, "Nombre del servicio")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(precio, { precio = it }, "Precio base", placeholder = "150.00")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(descripcion, { descripcion = it }, "Descripción (opcional)", singleLine = false, maxLines = 4)
    }
}

// ─── CONTRATO ────────────────────────────────────────────────────────────────
@Composable
fun ContratoListScreen(viewModel: ContratoViewModel, isAdmin: Boolean, onDetail: (Int) -> Unit, onNew: () -> Unit) {
    val state by viewModel.state.collectAsState()
    var search by remember { mutableStateOf("") }
    LaunchedEffect(search) { kotlinx.coroutines.delay(400); viewModel.loadItems(search.takeIf { it.isNotBlank() }) }
    EntityListScaffold("Contratos", state.totalCount, isAdmin, onNew, search, { search = it }, state.isLoading, state.error, { viewModel.loadItems() }, state.items.isEmpty() && !state.isLoading, state.hasMore, { viewModel.loadMore() }) {
        state.items.forEach { c ->
            item {
                EntityCard(
                    title = "Contrato #${c.id}", subtitle = c.clienteNombre ?: "Cliente #${c.cliente}",
                    detail = "Total: $ ${c.total}", icon = Icons.Filled.Description,
                    iconTint = Color(0xFFD4AF37), badge = c.estadoPago
                ) { onDetail(c.id) }
            }
        }
    }
}

@Composable
fun ContratoDetailScreen(id: Int, viewModel: ContratoViewModel, isAdmin: Boolean, onBack: () -> Unit, onEdit: (Int) -> Unit) {
    val detail by viewModel.detail.collectAsState()
    LaunchedEffect(id) { viewModel.loadById(id) }
    var showDelete by remember { mutableStateOf(false) }
    val item = detail.item
    if (showDelete && item != null) ConfirmDeleteDialog("Contrato #${item.id}", { viewModel.delete(id) { onBack() }; showDelete = false }, { showDelete = false })
    EntityDetailScaffold("Detalle Contrato", detail.isLoading, detail.error, isAdmin, onBack, { onEdit(id) }, { showDelete = true }) {
        item?.let {
            DetailField("N° Contrato", "#${it.id}", Icons.Filled.Description)
            HorizontalDivider(color = DividerColor)
            DetailField("Cliente", it.clienteNombre ?: "ID ${it.cliente}", Icons.Filled.Person)
            HorizontalDivider(color = DividerColor)
            DetailField("Difunto", it.difuntoNombre ?: it.difunto?.toString(), Icons.Filled.PersonOff)
            HorizontalDivider(color = DividerColor)
            DetailField("Fecha emisión", it.fechaEmision, Icons.Filled.CalendarToday)
            HorizontalDivider(color = DividerColor)
            DetailField("Total", "$ ${it.total}", Icons.Filled.AttachMoney)
            HorizontalDivider(color = DividerColor)
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 10.dp)) {
                Text("Estado: ", style = MaterialTheme.typography.labelMedium, color = TextTertiary)
                Spacer(Modifier.width(8.dp))
                StatusBadge(it.estadoPago)
            }
        }
    }
}

@Composable
fun ContratoFormScreen(id: Int?, viewModel: ContratoViewModel, onBack: () -> Unit) {
    val detail by viewModel.detail.collectAsState()
    val editing = id != null && id >= 0
    LaunchedEffect(id) { if (editing) viewModel.loadById(id!!) else viewModel.clearErrors() }
    var clienteId by remember { mutableStateOf("") }
    var difuntoId by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }
    var estadoPago by remember { mutableStateOf("Pendiente") }
    LaunchedEffect(detail.item) { detail.item?.let { c -> clienteId = c.cliente.toString(); difuntoId = c.difunto?.toString() ?: ""; total = c.total.toString(); estadoPago = c.estadoPago } }
    val estados = listOf("Pendiente", "Pagado", "Cancelado")
    EntityFormScaffold(if (editing) "Editar Contrato" else "Nuevo Contrato", detail.isSaving, detail.saveError, onBack, {
        viewModel.save(id, ContratoRequest(clienteId.toIntOrNull() ?: 0, difuntoId.toIntOrNull(), total.toDoubleOrNull() ?: 0.0, estadoPago), onBack)
    }, clienteId.isNotBlank() && total.isNotBlank()) {
        FunerariaTextField(clienteId, { clienteId = it }, "ID del Cliente", placeholder = "Ej: 1")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(difuntoId, { difuntoId = it }, "ID Difunto (opcional)", placeholder = "Ej: 2")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(total, { total = it }, "Total ($)", placeholder = "500.00")
        Spacer(Modifier.height(14.dp))
        ChoiceDropdown("Estado de pago", estados, estadoPago) { estadoPago = it }
    }
}

// ─── EMPLEADO ────────────────────────────────────────────────────────────────
@Composable
fun EmpleadoListScreen(viewModel: EmpleadoViewModel, isAdmin: Boolean, onDetail: (Int) -> Unit, onNew: () -> Unit) {
    val state by viewModel.state.collectAsState()
    var search by remember { mutableStateOf("") }
    LaunchedEffect(search) { kotlinx.coroutines.delay(400); viewModel.loadItems(search.takeIf { it.isNotBlank() }) }
    EntityListScaffold("Empleados", state.totalCount, isAdmin, onNew, search, { search = it }, state.isLoading, state.error, { viewModel.loadItems() }, state.items.isEmpty() && !state.isLoading, state.hasMore, { viewModel.loadMore() }) {
        state.items.forEach { e ->
            item { EntityCard(e.usuarioNombre ?: "Usuario #${e.usuario}", e.cargo, "Turno: ${e.turno}", Icons.Filled.Badge, Color(0xFFAB47BC)) { onDetail(e.id) } }
        }
    }
}

@Composable
fun EmpleadoDetailScreen(id: Int, viewModel: EmpleadoViewModel, isAdmin: Boolean, onBack: () -> Unit, onEdit: (Int) -> Unit) {
    val detail by viewModel.detail.collectAsState()
    LaunchedEffect(id) { viewModel.loadById(id) }
    var showDelete by remember { mutableStateOf(false) }
    val item = detail.item
    if (showDelete && item != null) ConfirmDeleteDialog(item.usuarioNombre ?: "Empleado", { viewModel.delete(id) { onBack() }; showDelete = false }, { showDelete = false })
    EntityDetailScaffold("Detalle Empleado", detail.isLoading, detail.error, isAdmin, onBack, { onEdit(id) }, { showDelete = true }) {
        item?.let {
            DetailField("Usuario", it.usuarioNombre ?: "ID ${it.usuario}", Icons.Filled.Person)
            HorizontalDivider(color = DividerColor)
            DetailField("Cargo", it.cargo, Icons.Filled.Badge)
            HorizontalDivider(color = DividerColor)
            DetailField("Turno", it.turno, Icons.Filled.WatchLater)
        }
    }
}

@Composable
fun EmpleadoFormScreen(id: Int?, viewModel: EmpleadoViewModel, onBack: () -> Unit) {
    val detail by viewModel.detail.collectAsState()
    val editing = id != null && id >= 0
    LaunchedEffect(id) { if (editing) viewModel.loadById(id!!) else viewModel.clearErrors() }
    var usuarioId by remember { mutableStateOf("") }
    var cargo by remember { mutableStateOf("") }
    var turno by remember { mutableStateOf("Manana") }
    LaunchedEffect(detail.item) { detail.item?.let { e -> usuarioId = e.usuario.toString(); cargo = e.cargo; turno = e.turno } }
    val turnos = listOf("Manana", "Tarde", "Noche")
    EntityFormScaffold(if (editing) "Editar Empleado" else "Nuevo Empleado", detail.isSaving, detail.saveError, onBack, {
        viewModel.save(id, EmpleadoRequest(usuarioId.toIntOrNull() ?: 0, cargo, turno), onBack)
    }, usuarioId.isNotBlank() && cargo.isNotBlank()) {
        FunerariaTextField(usuarioId, { usuarioId = it }, "ID del Usuario", placeholder = "Ej: 1")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(cargo, { cargo = it }, "Cargo", placeholder = "Ej: Agente funerario")
        Spacer(Modifier.height(14.dp))
        ChoiceDropdown("Turno", turnos, turno) { turno = it }
    }
}

// ─── PROVEEDOR ───────────────────────────────────────────────────────────────
@Composable
fun ProveedorListScreen(viewModel: ProveedorViewModel, isAdmin: Boolean, onDetail: (Int) -> Unit, onNew: () -> Unit) {
    val state by viewModel.state.collectAsState()
    var search by remember { mutableStateOf("") }
    LaunchedEffect(search) { kotlinx.coroutines.delay(400); viewModel.loadItems(search.takeIf { it.isNotBlank() }) }
    EntityListScaffold("Proveedores", state.totalCount, isAdmin, onNew, search, { search = it }, state.isLoading, state.error, { viewModel.loadItems() }, state.items.isEmpty() && !state.isLoading, state.hasMore, { viewModel.loadMore() }) {
        state.items.forEach { p ->
            item { EntityCard(p.nombreEmpresa, p.tipoProducto, "RUC: ${p.rucNit}", Icons.Filled.LocalShipping, Color(0xFFEF5350)) { onDetail(p.id) } }
        }
    }
}

@Composable
fun ProveedorDetailScreen(id: Int, viewModel: ProveedorViewModel, isAdmin: Boolean, onBack: () -> Unit, onEdit: (Int) -> Unit) {
    val detail by viewModel.detail.collectAsState()
    LaunchedEffect(id) { viewModel.loadById(id) }
    var showDelete by remember { mutableStateOf(false) }
    val item = detail.item
    if (showDelete && item != null) ConfirmDeleteDialog(item.nombreEmpresa, { viewModel.delete(id) { onBack() }; showDelete = false }, { showDelete = false })
    EntityDetailScaffold("Detalle Proveedor", detail.isLoading, detail.error, isAdmin, onBack, { onEdit(id) }, { showDelete = true }) {
        item?.let {
            DetailField("Empresa", it.nombreEmpresa, Icons.Filled.Business)
            HorizontalDivider(color = DividerColor)
            DetailField("RUC / NIT", it.rucNit, Icons.Filled.Tag)
            HorizontalDivider(color = DividerColor)
            DetailField("Tipo de producto", it.tipoProducto, Icons.Filled.Category)
            HorizontalDivider(color = DividerColor)
            DetailField("Contacto", it.contactoNombre, Icons.Filled.Person)
            HorizontalDivider(color = DividerColor)
            DetailField("Teléfono", it.telefono, Icons.Filled.Phone)
            HorizontalDivider(color = DividerColor)
            DetailField("Email", it.email, Icons.Filled.Email)
            HorizontalDivider(color = DividerColor)
            DetailField("Dirección", it.direccion, Icons.Filled.LocationOn)
        }
    }
}

@Composable
fun ProveedorFormScreen(id: Int?, viewModel: ProveedorViewModel, onBack: () -> Unit) {
    val detail by viewModel.detail.collectAsState()
    val editing = id != null && id >= 0
    LaunchedEffect(id) { if (editing) viewModel.loadById(id!!) else viewModel.clearErrors() }
    var empresa by remember { mutableStateOf("") }
    var ruc by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("Varios") }
    var contacto by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    LaunchedEffect(detail.item) { detail.item?.let { p -> empresa = p.nombreEmpresa; ruc = p.rucNit; tipo = p.tipoProducto; contacto = p.contactoNombre ?: ""; telefono = p.telefono ?: ""; email = p.email ?: ""; direccion = p.direccion ?: "" } }
    val tipos = listOf("Ataudes", "Urnas", "Flores", "Accesorios", "Varios")
    EntityFormScaffold(if (editing) "Editar Proveedor" else "Nuevo Proveedor", detail.isSaving, detail.saveError, onBack, {
        viewModel.save(id, ProveedorRequest(empresa, ruc, contacto.takeIf { it.isNotBlank() }, telefono.takeIf { it.isNotBlank() }, email.takeIf { it.isNotBlank() }, direccion.takeIf { it.isNotBlank() }, tipo), onBack)
    }, empresa.isNotBlank() && ruc.isNotBlank()) {
        FunerariaTextField(empresa, { empresa = it }, "Nombre de la empresa")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(ruc, { ruc = it }, "RUC / NIT")
        Spacer(Modifier.height(14.dp))
        ChoiceDropdown("Tipo de producto", tipos, tipo) { tipo = it }
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(contacto, { contacto = it }, "Nombre de contacto (opcional)")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(telefono, { telefono = it }, "Teléfono (opcional)")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(email, { email = it }, "Email (opcional)")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(direccion, { direccion = it }, "Dirección (opcional)", singleLine = false, maxLines = 3)
    }
}

// ─── PAGO ────────────────────────────────────────────────────────────────────
@Composable
fun PagoListScreen(viewModel: PagoViewModel, isAdmin: Boolean, onDetail: (Int) -> Unit, onNew: () -> Unit) {
    val state by viewModel.state.collectAsState()
    var search by remember { mutableStateOf("") }
    LaunchedEffect(search) { kotlinx.coroutines.delay(400); viewModel.loadItems(search.takeIf { it.isNotBlank() }) }
    EntityListScaffold("Pagos", state.totalCount, isAdmin, onNew, search, { search = it }, state.isLoading, state.error, { viewModel.loadItems() }, state.items.isEmpty() && !state.isLoading, state.hasMore, { viewModel.loadMore() }) {
        state.items.forEach { p ->
            item {
                EntityCard(
                    title = "Pago #${p.id}", subtitle = p.contratoCliente ?: "Contrato #${p.contrato}",
                    detail = "$ ${p.monto} · ${p.metodoPago}", icon = Icons.Filled.Payments,
                    iconTint = Color(0xFF4CAF50)
                ) { onDetail(p.id) }
            }
        }
    }
}

@Composable
fun PagoDetailScreen(id: Int, viewModel: PagoViewModel, isAdmin: Boolean, onBack: () -> Unit, onEdit: (Int) -> Unit) {
    val detail by viewModel.detail.collectAsState()
    LaunchedEffect(id) { viewModel.loadById(id) }
    var showDelete by remember { mutableStateOf(false) }
    val item = detail.item
    if (showDelete && item != null) ConfirmDeleteDialog("Pago #${item.id}", { viewModel.delete(id) { onBack() }; showDelete = false }, { showDelete = false })
    EntityDetailScaffold("Detalle Pago", detail.isLoading, detail.error, isAdmin, onBack, { onEdit(id) }, { showDelete = true }) {
        item?.let {
            DetailField("N° Pago", "#${it.id}", Icons.Filled.Payments)
            HorizontalDivider(color = DividerColor)
            DetailField("Contrato cliente", it.contratoCliente ?: "Contrato #${it.contrato}", Icons.Filled.Person)
            HorizontalDivider(color = DividerColor)
            DetailField("Monto", "$ ${it.monto}", Icons.Filled.AttachMoney)
            HorizontalDivider(color = DividerColor)
            DetailField("Método de pago", it.metodoPago, Icons.Filled.CreditCard)
            HorizontalDivider(color = DividerColor)
            DetailField("Fecha de pago", it.fechaPago, Icons.Filled.CalendarToday)
            HorizontalDivider(color = DividerColor)
            DetailField("Referencia", it.referencia, Icons.Filled.Receipt)
            HorizontalDivider(color = DividerColor)
            DetailField("Observaciones", it.observaciones, Icons.Filled.Notes)
        }
    }
}

@Composable
fun PagoFormScreen(id: Int?, viewModel: PagoViewModel, onBack: () -> Unit) {
    val detail by viewModel.detail.collectAsState()
    val editing = id != null && id >= 0
    LaunchedEffect(id) { if (editing) viewModel.loadById(id!!) else viewModel.clearErrors() }
    var contratoId by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var metodo by remember { mutableStateOf("Efectivo") }
    var referencia by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }
    LaunchedEffect(detail.item) { detail.item?.let { p -> contratoId = p.contrato.toString(); monto = p.monto.toString(); metodo = p.metodoPago; referencia = p.referencia ?: ""; observaciones = p.observaciones ?: "" } }
    val metodos = listOf("Efectivo", "Transferencia", "Tarjeta", "Cheque")
    EntityFormScaffold(if (editing) "Editar Pago" else "Nuevo Pago", detail.isSaving, detail.saveError, onBack, {
        viewModel.save(id, PagoRequest(contratoId.toIntOrNull() ?: 0, monto.toDoubleOrNull() ?: 0.0, metodo, referencia.takeIf { it.isNotBlank() }, observaciones.takeIf { it.isNotBlank() }), onBack)
    }, contratoId.isNotBlank() && monto.isNotBlank()) {
        FunerariaTextField(contratoId, { contratoId = it }, "ID del Contrato", placeholder = "Ej: 1")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(monto, { monto = it }, "Monto ($)", placeholder = "250.00")
        Spacer(Modifier.height(14.dp))
        ChoiceDropdown("Método de pago", metodos, metodo) { metodo = it }
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(referencia, { referencia = it }, "Referencia (opcional)", placeholder = "Nro. transferencia")
        Spacer(Modifier.height(14.dp))
        FunerariaTextField(observaciones, { observaciones = it }, "Observaciones (opcional)", singleLine = false, maxLines = 3)
    }
}

// ─── Shared scaffold helpers ──────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EntityListScaffold(
    title: String, total: Int, isAdmin: Boolean, onNew: () -> Unit,
    search: String, onSearch: (String) -> Unit, isLoading: Boolean,
    error: String?, onRetry: () -> Unit, isEmpty: Boolean,
    hasMore: Boolean, onLoadMore: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(title, fontWeight = FontWeight.Black, color = TextPrimary)
                        Text("$total registros", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceColor, titleContentColor = TextPrimary)
            )
        },
        floatingActionButton = {
            if (isAdmin) FloatingActionButton(onClick = onNew, containerColor = PrimaryPurple, contentColor = Color.White) {
                Icon(Icons.Default.Add, "Nuevo")
            }
        },
        containerColor = BackgroundColor
    ) { padding ->
        if (error != null) {
            ErrorScreen(error, onRetry)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).background(BackgroundColor),
                contentPadding = PaddingValues(16.dp)
            ) {
                item { FunerariaSearchBar(search, onSearch, "Buscar $title..."); Spacer(Modifier.height(12.dp)) }
                if (isEmpty && !isLoading) item { EmptyState("No hay $title registrados") }
                content()
                if (isLoading) item { Box(Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) { CircularProgressIndicator(color = PrimaryPurple, modifier = Modifier.size(32.dp)) } }
                if (hasMore && !isLoading) item { Spacer(Modifier.height(8.dp)); TextButton(onClick = onLoadMore, modifier = Modifier.fillMaxWidth()) { Text("Cargar más", color = PrimaryPurple) } }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EntityDetailScaffold(
    title: String, isLoading: Boolean, error: String?,
    isAdmin: Boolean, onBack: () -> Unit, onEdit: () -> Unit, onDelete: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, fontWeight = FontWeight.Bold, color = TextPrimary) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null, tint = TextPrimary) } },
                actions = {
                    if (isAdmin) {
                        IconButton(onClick = onEdit)   { Icon(Icons.Default.Edit,   null, tint = PrimaryPurple) }
                        IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, null, tint = ErrorColor) }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceColor)
            )
        },
        containerColor = BackgroundColor
    ) { padding ->
        when {
            isLoading     -> LoadingScreen()
            error != null -> ErrorScreen(error)
            else -> LazyColumn(modifier = Modifier.fillMaxSize().padding(padding), contentPadding = PaddingValues(16.dp)) {
                item {
                    Surface(color = SurfaceColor, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(20.dp)) { content() }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EntityFormScaffold(
    title: String, isSaving: Boolean, saveError: String?,
    onBack: () -> Unit, onSave: () -> Unit, saveEnabled: Boolean,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, fontWeight = FontWeight.Bold, color = TextPrimary) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null, tint = TextPrimary) } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceColor)
            )
        },
        containerColor = BackgroundColor
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding), contentPadding = PaddingValues(16.dp)) {
            item {
                if (saveError != null) {
                    Surface(color = ErrorColor.copy(0.15f), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
                        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.ErrorOutline, null, tint = ErrorColor, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(10.dp))
                            Text(saveError, color = ErrorColor, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
                Surface(color = SurfaceColor, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        content()
                        Spacer(Modifier.height(24.dp))
                        FunerariaButton("Guardar", onSave, isLoading = isSaving, enabled = saveEnabled && !isSaving)
                    }
                }
            }
        }
    }
}

// ─── Entity Card ─────────────────────────────────────────────────────────────
@Composable
fun EntityCard(
    title: String, subtitle: String, detail: String,
    icon: ImageVector, iconTint: Color,
    badge: String? = null,
    onClick: () -> Unit
) {
    Surface(
        color = SurfaceColor, shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable(onClick = onClick)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(44.dp).background(iconTint.copy(0.15f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) { Icon(icon, null, tint = iconTint, modifier = Modifier.size(24.dp)) }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title,    style = MaterialTheme.typography.titleSmall,  fontWeight = FontWeight.Bold,   color = TextPrimary)
                Text(subtitle, style = MaterialTheme.typography.bodySmall,   color = TextSecondary)
                if (detail.isNotBlank()) Text(detail, style = MaterialTheme.typography.labelSmall, color = TextTertiary)
            }
            if (badge != null) { Spacer(Modifier.width(8.dp)); StatusBadge(badge) }
            Icon(Icons.Default.ChevronRight, null, tint = TextTertiary, modifier = Modifier.size(18.dp))
        }
    }
}

// ─── Choice Dropdown ──────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoiceDropdown(label: String, options: List<String>, selected: String, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
        OutlinedTextField(
            value = selected, onValueChange = {}, readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.fillMaxWidth().menuAnchor(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryPurple, unfocusedBorderColor = Border,
                focusedContainerColor = SurfaceElevated, unfocusedContainerColor = SurfaceElevated,
                focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary,
                focusedLabelColor = PrimaryPurple, unfocusedLabelColor = TextSecondary
            )
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.background(SurfaceElevated)) {
            options.forEach { opt ->
                DropdownMenuItem(text = { Text(opt, color = TextPrimary) }, onClick = { onSelect(opt); expanded = false })
            }
        }
    }
}
