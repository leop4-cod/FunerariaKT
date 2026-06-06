package com.funeraria.app.domain.model

data class LoggedUser(
    val id: Int,
    val username: String,
    val isStaff: Boolean
)

data class Cliente(
    val id: Int,
    val nombreCompleto: String,
    val dniCedula: String,
    val telefono: String?,
    val email: String?,
    val direccion: String?
)

data class Difunto(
    val id: Int,
    val clienteResponsable: Int,
    val clienteResponsableNombre: String?,
    val nombreCompleto: String,
    val fechaNacimiento: String?,
    val fechaDefuncion: String,
    val causaFallecimiento: String?,
    val lugarVelacion: String?
)

data class Proveedor(
    val id: Int,
    val nombreEmpresa: String,
    val rucNit: String,
    val contactoNombre: String?,
    val telefono: String?,
    val email: String?,
    val direccion: String?,
    val tipoProducto: String
)

data class Producto(
    val id: Int,
    val nombre: String,
    val categoria: String,
    val precio: Double,
    val stockDisponible: Int,
    val proveedor: Int?
)

data class Servicio(
    val id: Int,
    val nombre: String,
    val descripcion: String?,
    val precioBase: Double
)

data class Contrato(
    val id: Int,
    val cliente: Int,
    val clienteNombre: String?,
    val difunto: Int?,
    val difuntoNombre: String?,
    val fechaEmision: String,
    val total: Double,
    val estadoPago: String
)

data class Empleado(
    val id: Int,
    val usuario: Int,
    val usuarioNombre: String?,
    val cargo: String,
    val turno: String
)

data class Pago(
    val id: Int,
    val contrato: Int,
    val contratoCliente: String?,
    val monto: Double,
    val fechaPago: String,
    val metodoPago: String,
    val referencia: String?,
    val observaciones: String?
)
