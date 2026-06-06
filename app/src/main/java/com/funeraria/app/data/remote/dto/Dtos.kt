package com.funeraria.app.data.remote.dto

import com.google.gson.annotations.SerializedName

// ─── AUTH ───────────────────────────────────────────────────────────────────
data class TokenRequest(val username: String, val password: String)
data class TokenResponse(val access: String, val refresh: String)
data class TokenRefreshRequest(val refresh: String)

// ─── PAGINATION ──────────────────────────────────────────────────────────────
data class PaginatedResponse<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)

// ─── CLIENTE ─────────────────────────────────────────────────────────────────
data class ClienteDto(
    val id: Int,
    @SerializedName("nombre_completo") val nombreCompleto: String,
    @SerializedName("dni_cedula")      val dniCedula: String,
    val telefono: String?,
    val email: String?,
    val direccion: String?
)
data class ClienteRequest(
    @SerializedName("nombre_completo") val nombreCompleto: String,
    @SerializedName("dni_cedula")      val dniCedula: String,
    val telefono: String?,
    val email: String?,
    val direccion: String?
)

// ─── DIFUNTO ─────────────────────────────────────────────────────────────────
data class DifuntoDto(
    val id: Int,
    @SerializedName("cliente_responsable")        val clienteResponsable: Int,
    @SerializedName("cliente_responsable_nombre") val clienteResponsableNombre: String?,
    @SerializedName("nombre_completo")   val nombreCompleto: String,
    @SerializedName("fecha_nacimiento")  val fechaNacimiento: String?,
    @SerializedName("fecha_defuncion")   val fechaDefuncion: String,
    @SerializedName("causa_fallecimiento") val causaFallecimiento: String?,
    @SerializedName("lugar_velacion")    val lugarVelacion: String?
)
data class DifuntoRequest(
    @SerializedName("cliente_responsable")       val clienteResponsable: Int,
    @SerializedName("nombre_completo")  val nombreCompleto: String,
    @SerializedName("fecha_nacimiento") val fechaNacimiento: String?,
    @SerializedName("fecha_defuncion")  val fechaDefuncion: String,
    @SerializedName("causa_fallecimiento") val causaFallecimiento: String?,
    @SerializedName("lugar_velacion")   val lugarVelacion: String?
)

// ─── PROVEEDOR ───────────────────────────────────────────────────────────────
data class ProveedorDto(
    val id: Int,
    @SerializedName("nombre_empresa")  val nombreEmpresa: String,
    @SerializedName("ruc_nit")         val rucNit: String,
    @SerializedName("contacto_nombre") val contactoNombre: String?,
    val telefono: String?,
    val email: String?,
    val direccion: String?,
    @SerializedName("tipo_producto")   val tipoProducto: String
)
data class ProveedorRequest(
    @SerializedName("nombre_empresa")  val nombreEmpresa: String,
    @SerializedName("ruc_nit")         val rucNit: String,
    @SerializedName("contacto_nombre") val contactoNombre: String?,
    val telefono: String?,
    val email: String?,
    val direccion: String?,
    @SerializedName("tipo_producto")   val tipoProducto: String
)

// ─── PRODUCTO ────────────────────────────────────────────────────────────────
data class ProductoDto(
    val id: Int,
    val nombre: String,
    val categoria: String,
    val precio: Double,
    @SerializedName("stock_disponible") val stockDisponible: Int,
    val proveedor: Int?
)
data class ProductoRequest(
    val nombre: String,
    val categoria: String,
    val precio: Double,
    @SerializedName("stock_disponible") val stockDisponible: Int,
    val proveedor: Int?
)

// ─── SERVICIO ────────────────────────────────────────────────────────────────
data class ServicioDto(
    val id: Int,
    val nombre: String,
    val descripcion: String?,
    @SerializedName("precio_base") val precioBase: Double
)
data class ServicioRequest(
    val nombre: String,
    val descripcion: String?,
    @SerializedName("precio_base") val precioBase: Double
)

// ─── CONTRATO ────────────────────────────────────────────────────────────────
data class ContratoDto(
    val id: Int,
    val cliente: Int,
    @SerializedName("cliente_nombre")  val clienteNombre: String?,
    val difunto: Int?,
    @SerializedName("difunto_nombre")  val difuntoNombre: String?,
    @SerializedName("fecha_emision")   val fechaEmision: String,
    val total: Double,
    @SerializedName("estado_pago")     val estadoPago: String
)
data class ContratoRequest(
    val cliente: Int,
    val difunto: Int?,
    val total: Double,
    @SerializedName("estado_pago") val estadoPago: String
)

// ─── EMPLEADO ────────────────────────────────────────────────────────────────
data class EmpleadoDto(
    val id: Int,
    val usuario: Int,
    @SerializedName("usuario_nombre") val usuarioNombre: String?,
    val cargo: String,
    val turno: String
)
data class EmpleadoRequest(
    val usuario: Int,
    val cargo: String,
    val turno: String
)

// ─── PAGO ────────────────────────────────────────────────────────────────────
data class PagoDto(
    val id: Int,
    val contrato: Int,
    @SerializedName("contrato_cliente") val contratoCliente: String?,
    val monto: Double,
    @SerializedName("fecha_pago")   val fechaPago: String,
    @SerializedName("metodo_pago")  val metodoPago: String,
    val referencia: String?,
    val observaciones: String?
)
data class PagoRequest(
    val contrato: Int,
    val monto: Double,
    @SerializedName("metodo_pago") val metodoPago: String,
    val referencia: String?,
    val observaciones: String?
)
