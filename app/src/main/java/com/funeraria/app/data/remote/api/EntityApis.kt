package com.funeraria.app.data.remote.api

import com.funeraria.app.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

// ─── CLIENTE ─────────────────────────────────────────────────────────────────
interface ClienteApi {
    @GET("clientes/")
    suspend fun getAll(@Query("page") page: Int = 1, @Query("search") search: String? = null): Response<PaginatedResponse<ClienteDto>>
    @GET("clientes/{id}/")
    suspend fun getById(@Path("id") id: Int): Response<ClienteDto>
    @POST("clientes/")
    suspend fun create(@Body request: ClienteRequest): Response<ClienteDto>
    @PUT("clientes/{id}/")
    suspend fun update(@Path("id") id: Int, @Body request: ClienteRequest): Response<ClienteDto>
    @DELETE("clientes/{id}/")
    suspend fun delete(@Path("id") id: Int): Response<Unit>
}

// ─── DIFUNTO ─────────────────────────────────────────────────────────────────
interface DifuntoApi {
    @GET("difuntos/")
    suspend fun getAll(@Query("page") page: Int = 1, @Query("search") search: String? = null): Response<PaginatedResponse<DifuntoDto>>
    @GET("difuntos/{id}/")
    suspend fun getById(@Path("id") id: Int): Response<DifuntoDto>
    @POST("difuntos/")
    suspend fun create(@Body request: DifuntoRequest): Response<DifuntoDto>
    @PUT("difuntos/{id}/")
    suspend fun update(@Path("id") id: Int, @Body request: DifuntoRequest): Response<DifuntoDto>
    @DELETE("difuntos/{id}/")
    suspend fun delete(@Path("id") id: Int): Response<Unit>
}

// ─── PRODUCTO ────────────────────────────────────────────────────────────────
interface ProductoApi {
    @GET("productos/")
    suspend fun getAll(@Query("page") page: Int = 1, @Query("search") search: String? = null): Response<PaginatedResponse<ProductoDto>>
    @GET("productos/{id}/")
    suspend fun getById(@Path("id") id: Int): Response<ProductoDto>
    @POST("productos/")
    suspend fun create(@Body request: ProductoRequest): Response<ProductoDto>
    @PUT("productos/{id}/")
    suspend fun update(@Path("id") id: Int, @Body request: ProductoRequest): Response<ProductoDto>
    @DELETE("productos/{id}/")
    suspend fun delete(@Path("id") id: Int): Response<Unit>
}

// ─── SERVICIO ────────────────────────────────────────────────────────────────
interface ServicioApi {
    @GET("servicios/")
    suspend fun getAll(@Query("page") page: Int = 1, @Query("search") search: String? = null): Response<PaginatedResponse<ServicioDto>>
    @GET("servicios/{id}/")
    suspend fun getById(@Path("id") id: Int): Response<ServicioDto>
    @POST("servicios/")
    suspend fun create(@Body request: ServicioRequest): Response<ServicioDto>
    @PUT("servicios/{id}/")
    suspend fun update(@Path("id") id: Int, @Body request: ServicioRequest): Response<ServicioDto>
    @DELETE("servicios/{id}/")
    suspend fun delete(@Path("id") id: Int): Response<Unit>
}

// ─── CONTRATO ────────────────────────────────────────────────────────────────
interface ContratoApi {
    @GET("contratos/")
    suspend fun getAll(@Query("page") page: Int = 1, @Query("search") search: String? = null): Response<PaginatedResponse<ContratoDto>>
    @GET("contratos/{id}/")
    suspend fun getById(@Path("id") id: Int): Response<ContratoDto>
    @POST("contratos/")
    suspend fun create(@Body request: ContratoRequest): Response<ContratoDto>
    @PUT("contratos/{id}/")
    suspend fun update(@Path("id") id: Int, @Body request: ContratoRequest): Response<ContratoDto>
    @DELETE("contratos/{id}/")
    suspend fun delete(@Path("id") id: Int): Response<Unit>
}

// ─── EMPLEADO ────────────────────────────────────────────────────────────────
interface EmpleadoApi {
    @GET("empleados/")
    suspend fun getAll(@Query("page") page: Int = 1, @Query("search") search: String? = null): Response<PaginatedResponse<EmpleadoDto>>
    @GET("empleados/{id}/")
    suspend fun getById(@Path("id") id: Int): Response<EmpleadoDto>
    @POST("empleados/")
    suspend fun create(@Body request: EmpleadoRequest): Response<EmpleadoDto>
    @PUT("empleados/{id}/")
    suspend fun update(@Path("id") id: Int, @Body request: EmpleadoRequest): Response<EmpleadoDto>
    @DELETE("empleados/{id}/")
    suspend fun delete(@Path("id") id: Int): Response<Unit>
}

// ─── PROVEEDOR ───────────────────────────────────────────────────────────────
interface ProveedorApi {
    @GET("proveedores/")
    suspend fun getAll(@Query("page") page: Int = 1, @Query("search") search: String? = null): Response<PaginatedResponse<ProveedorDto>>
    @GET("proveedores/{id}/")
    suspend fun getById(@Path("id") id: Int): Response<ProveedorDto>
    @POST("proveedores/")
    suspend fun create(@Body request: ProveedorRequest): Response<ProveedorDto>
    @PUT("proveedores/{id}/")
    suspend fun update(@Path("id") id: Int, @Body request: ProveedorRequest): Response<ProveedorDto>
    @DELETE("proveedores/{id}/")
    suspend fun delete(@Path("id") id: Int): Response<Unit>
}

// ─── PAGO ────────────────────────────────────────────────────────────────────
interface PagoApi {
    @GET("pagos/")
    suspend fun getAll(@Query("page") page: Int = 1, @Query("search") search: String? = null): Response<PaginatedResponse<PagoDto>>
    @GET("pagos/{id}/")
    suspend fun getById(@Path("id") id: Int): Response<PagoDto>
    @POST("pagos/")
    suspend fun create(@Body request: PagoRequest): Response<PagoDto>
    @PUT("pagos/{id}/")
    suspend fun update(@Path("id") id: Int, @Body request: PagoRequest): Response<PagoDto>
    @DELETE("pagos/{id}/")
    suspend fun delete(@Path("id") id: Int): Response<Unit>
}
