package com.funeraria.app.domain.repository

import com.funeraria.app.data.local.TokenDataStore
import com.funeraria.app.data.remote.dto.*
import com.funeraria.app.domain.model.*

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<LoggedUser>
    suspend fun logout(): Result<Unit>
    suspend fun getStoredUser(): TokenDataStore.UserSnapshot?
    suspend fun isLoggedIn(): Boolean
}

interface ClienteRepository {
    suspend fun getAll(page: Int = 1, search: String? = null): Result<PaginatedResponse<ClienteDto>>
    suspend fun getById(id: Int): Result<Cliente>
    suspend fun create(request: ClienteRequest): Result<Cliente>
    suspend fun update(id: Int, request: ClienteRequest): Result<Cliente>
    suspend fun delete(id: Int): Result<Unit>
}

interface DifuntoRepository {
    suspend fun getAll(page: Int = 1, search: String? = null): Result<PaginatedResponse<DifuntoDto>>
    suspend fun getById(id: Int): Result<Difunto>
    suspend fun create(request: DifuntoRequest): Result<Difunto>
    suspend fun update(id: Int, request: DifuntoRequest): Result<Difunto>
    suspend fun delete(id: Int): Result<Unit>
}

interface ProductoRepository {
    suspend fun getAll(page: Int = 1, search: String? = null): Result<PaginatedResponse<ProductoDto>>
    suspend fun getById(id: Int): Result<Producto>
    suspend fun create(request: ProductoRequest): Result<Producto>
    suspend fun update(id: Int, request: ProductoRequest): Result<Producto>
    suspend fun delete(id: Int): Result<Unit>
}

interface ServicioRepository {
    suspend fun getAll(page: Int = 1, search: String? = null): Result<PaginatedResponse<ServicioDto>>
    suspend fun getById(id: Int): Result<Servicio>
    suspend fun create(request: ServicioRequest): Result<Servicio>
    suspend fun update(id: Int, request: ServicioRequest): Result<Servicio>
    suspend fun delete(id: Int): Result<Unit>
}

interface ContratoRepository {
    suspend fun getAll(page: Int = 1, search: String? = null): Result<PaginatedResponse<ContratoDto>>
    suspend fun getById(id: Int): Result<Contrato>
    suspend fun create(request: ContratoRequest): Result<Contrato>
    suspend fun update(id: Int, request: ContratoRequest): Result<Contrato>
    suspend fun delete(id: Int): Result<Unit>
}

interface EmpleadoRepository {
    suspend fun getAll(page: Int = 1, search: String? = null): Result<PaginatedResponse<EmpleadoDto>>
    suspend fun getById(id: Int): Result<Empleado>
    suspend fun create(request: EmpleadoRequest): Result<Empleado>
    suspend fun update(id: Int, request: EmpleadoRequest): Result<Empleado>
    suspend fun delete(id: Int): Result<Unit>
}

interface ProveedorRepository {
    suspend fun getAll(page: Int = 1, search: String? = null): Result<PaginatedResponse<ProveedorDto>>
    suspend fun getById(id: Int): Result<Proveedor>
    suspend fun create(request: ProveedorRequest): Result<Proveedor>
    suspend fun update(id: Int, request: ProveedorRequest): Result<Proveedor>
    suspend fun delete(id: Int): Result<Unit>
}

interface PagoRepository {
    suspend fun getAll(page: Int = 1, search: String? = null): Result<PaginatedResponse<PagoDto>>
    suspend fun getById(id: Int): Result<Pago>
    suspend fun create(request: PagoRequest): Result<Pago>
    suspend fun update(id: Int, request: PagoRequest): Result<Pago>
    suspend fun delete(id: Int): Result<Unit>
}
