package com.funeraria.app.data.repository

import com.funeraria.app.data.remote.api.*
import com.funeraria.app.data.remote.dto.*
import com.funeraria.app.domain.model.*
import com.funeraria.app.domain.repository.*
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

// ─── Shared error parser ──────────────────────────────────────────────────────
private fun parseHttpError(code: Int): String = when (code) {
    400  -> "Datos inválidos. Revisa los campos."
    401  -> "No autorizado. Inicia sesión nuevamente."
    403  -> "Sin permisos para esta acción."
    404  -> "Registro no encontrado."
    500  -> "Error interno del servidor."
    else -> "Error $code."
}
private fun <T> assertSuccess(response: Response<T>): T {
    if (!response.isSuccessful) error(parseHttpError(response.code()))
    return response.body() ?: error("Respuesta vacía del servidor")
}

// ─── CLIENTE ─────────────────────────────────────────────────────────────────
@Singleton
class ClienteRepositoryImpl @Inject constructor(private val api: ClienteApi) : ClienteRepository {
    override suspend fun getAll(page: Int, search: String?) = runCatching { api.getAll(page, search).let { if (!it.isSuccessful) error(parseHttpError(it.code())) else it.body()!! } }
    override suspend fun getById(id: Int) = runCatching { assertSuccess(api.getById(id)).toCliente() }
    override suspend fun create(request: ClienteRequest) = runCatching { assertSuccess(api.create(request)).toCliente() }
    override suspend fun update(id: Int, request: ClienteRequest) = runCatching { assertSuccess(api.update(id, request)).toCliente() }
    override suspend fun delete(id: Int) = runCatching { api.delete(id); Unit }
    private fun ClienteDto.toCliente() = Cliente(id, nombreCompleto, dniCedula, telefono, email, direccion)
}

// ─── DIFUNTO ─────────────────────────────────────────────────────────────────
@Singleton
class DifuntoRepositoryImpl @Inject constructor(private val api: DifuntoApi) : DifuntoRepository {
    override suspend fun getAll(page: Int, search: String?) = runCatching { api.getAll(page, search).let { if (!it.isSuccessful) error(parseHttpError(it.code())) else it.body()!! } }
    override suspend fun getById(id: Int) = runCatching { assertSuccess(api.getById(id)).toDifunto() }
    override suspend fun create(request: DifuntoRequest) = runCatching { assertSuccess(api.create(request)).toDifunto() }
    override suspend fun update(id: Int, request: DifuntoRequest) = runCatching { assertSuccess(api.update(id, request)).toDifunto() }
    override suspend fun delete(id: Int) = runCatching { api.delete(id); Unit }
    private fun DifuntoDto.toDifunto() = Difunto(id, clienteResponsable, clienteResponsableNombre, nombreCompleto, fechaNacimiento, fechaDefuncion, causaFallecimiento, lugarVelacion)
}

// ─── PRODUCTO ────────────────────────────────────────────────────────────────
@Singleton
class ProductoRepositoryImpl @Inject constructor(private val api: ProductoApi) : ProductoRepository {
    override suspend fun getAll(page: Int, search: String?) = runCatching { api.getAll(page, search).let { if (!it.isSuccessful) error(parseHttpError(it.code())) else it.body()!! } }
    override suspend fun getById(id: Int) = runCatching { assertSuccess(api.getById(id)).toProducto() }
    override suspend fun create(request: ProductoRequest) = runCatching { assertSuccess(api.create(request)).toProducto() }
    override suspend fun update(id: Int, request: ProductoRequest) = runCatching { assertSuccess(api.update(id, request)).toProducto() }
    override suspend fun delete(id: Int) = runCatching { api.delete(id); Unit }
    private fun ProductoDto.toProducto() = Producto(id, nombre, categoria, precio, stockDisponible, proveedor)
}

// ─── SERVICIO ────────────────────────────────────────────────────────────────
@Singleton
class ServicioRepositoryImpl @Inject constructor(private val api: ServicioApi) : ServicioRepository {
    override suspend fun getAll(page: Int, search: String?) = runCatching { api.getAll(page, search).let { if (!it.isSuccessful) error(parseHttpError(it.code())) else it.body()!! } }
    override suspend fun getById(id: Int) = runCatching { assertSuccess(api.getById(id)).toServicio() }
    override suspend fun create(request: ServicioRequest) = runCatching { assertSuccess(api.create(request)).toServicio() }
    override suspend fun update(id: Int, request: ServicioRequest) = runCatching { assertSuccess(api.update(id, request)).toServicio() }
    override suspend fun delete(id: Int) = runCatching { api.delete(id); Unit }
    private fun ServicioDto.toServicio() = Servicio(id, nombre, descripcion, precioBase)
}

// ─── CONTRATO ────────────────────────────────────────────────────────────────
@Singleton
class ContratoRepositoryImpl @Inject constructor(private val api: ContratoApi) : ContratoRepository {
    override suspend fun getAll(page: Int, search: String?) = runCatching { api.getAll(page, search).let { if (!it.isSuccessful) error(parseHttpError(it.code())) else it.body()!! } }
    override suspend fun getById(id: Int) = runCatching { assertSuccess(api.getById(id)).toContrato() }
    override suspend fun create(request: ContratoRequest) = runCatching { assertSuccess(api.create(request)).toContrato() }
    override suspend fun update(id: Int, request: ContratoRequest) = runCatching { assertSuccess(api.update(id, request)).toContrato() }
    override suspend fun delete(id: Int) = runCatching { api.delete(id); Unit }
    private fun ContratoDto.toContrato() = Contrato(id, cliente, clienteNombre, difunto, difuntoNombre, fechaEmision, total, estadoPago)
}

// ─── EMPLEADO ────────────────────────────────────────────────────────────────
@Singleton
class EmpleadoRepositoryImpl @Inject constructor(private val api: EmpleadoApi) : EmpleadoRepository {
    override suspend fun getAll(page: Int, search: String?) = runCatching { api.getAll(page, search).let { if (!it.isSuccessful) error(parseHttpError(it.code())) else it.body()!! } }
    override suspend fun getById(id: Int) = runCatching { assertSuccess(api.getById(id)).toEmpleado() }
    override suspend fun create(request: EmpleadoRequest) = runCatching { assertSuccess(api.create(request)).toEmpleado() }
    override suspend fun update(id: Int, request: EmpleadoRequest) = runCatching { assertSuccess(api.update(id, request)).toEmpleado() }
    override suspend fun delete(id: Int) = runCatching { api.delete(id); Unit }
    private fun EmpleadoDto.toEmpleado() = Empleado(id, usuario, usuarioNombre, cargo, turno)
}

// ─── PROVEEDOR ───────────────────────────────────────────────────────────────
@Singleton
class ProveedorRepositoryImpl @Inject constructor(private val api: ProveedorApi) : ProveedorRepository {
    override suspend fun getAll(page: Int, search: String?) = runCatching { api.getAll(page, search).let { if (!it.isSuccessful) error(parseHttpError(it.code())) else it.body()!! } }
    override suspend fun getById(id: Int) = runCatching { assertSuccess(api.getById(id)).toProveedor() }
    override suspend fun create(request: ProveedorRequest) = runCatching { assertSuccess(api.create(request)).toProveedor() }
    override suspend fun update(id: Int, request: ProveedorRequest) = runCatching { assertSuccess(api.update(id, request)).toProveedor() }
    override suspend fun delete(id: Int) = runCatching { api.delete(id); Unit }
    private fun ProveedorDto.toProveedor() = Proveedor(id, nombreEmpresa, rucNit, contactoNombre, telefono, email, direccion, tipoProducto)
}

// ─── PAGO ────────────────────────────────────────────────────────────────────
@Singleton
class PagoRepositoryImpl @Inject constructor(private val api: PagoApi) : PagoRepository {
    override suspend fun getAll(page: Int, search: String?) = runCatching { api.getAll(page, search).let { if (!it.isSuccessful) error(parseHttpError(it.code())) else it.body()!! } }
    override suspend fun getById(id: Int) = runCatching { assertSuccess(api.getById(id)).toPago() }
    override suspend fun create(request: PagoRequest) = runCatching { assertSuccess(api.create(request)).toPago() }
    override suspend fun update(id: Int, request: PagoRequest) = runCatching { assertSuccess(api.update(id, request)).toPago() }
    override suspend fun delete(id: Int) = runCatching { api.delete(id); Unit }
    private fun PagoDto.toPago() = Pago(id, contrato, contratoCliente, monto, fechaPago, metodoPago, referencia, observaciones)
}
