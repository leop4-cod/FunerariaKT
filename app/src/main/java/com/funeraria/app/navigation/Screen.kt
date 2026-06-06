package com.funeraria.app.navigation

sealed class Screen(val route: String) {
    data object Login    : Screen("login")
    data object Home     : Screen("home")
    data object Profile  : Screen("profile")

    // Clientes
    data object ClienteList   : Screen("clientes")
    data object ClienteDetail : Screen("clientes/{id}") { fun createRoute(id: Int) = "clientes/$id" }
    data object ClienteForm   : Screen("clientes/form?id={id}") { fun createRoute(id: Int? = null) = if (id != null) "clientes/form?id=$id" else "clientes/form?id=-1" }

    // Difuntos
    data object DifuntoList   : Screen("difuntos")
    data object DifuntoDetail : Screen("difuntos/{id}") { fun createRoute(id: Int) = "difuntos/$id" }
    data object DifuntoForm   : Screen("difuntos/form?id={id}") { fun createRoute(id: Int? = null) = if (id != null) "difuntos/form?id=$id" else "difuntos/form?id=-1" }

    // Productos
    data object ProductoList   : Screen("productos")
    data object ProductoDetail : Screen("productos/{id}") { fun createRoute(id: Int) = "productos/$id" }
    data object ProductoForm   : Screen("productos/form?id={id}") { fun createRoute(id: Int? = null) = if (id != null) "productos/form?id=$id" else "productos/form?id=-1" }

    // Servicios
    data object ServicioList   : Screen("servicios")
    data object ServicioDetail : Screen("servicios/{id}") { fun createRoute(id: Int) = "servicios/$id" }
    data object ServicioForm   : Screen("servicios/form?id={id}") { fun createRoute(id: Int? = null) = if (id != null) "servicios/form?id=$id" else "servicios/form?id=-1" }

    // Contratos
    data object ContratoList   : Screen("contratos")
    data object ContratoDetail : Screen("contratos/{id}") { fun createRoute(id: Int) = "contratos/$id" }
    data object ContratoForm   : Screen("contratos/form?id={id}") { fun createRoute(id: Int? = null) = if (id != null) "contratos/form?id=$id" else "contratos/form?id=-1" }

    // Empleados
    data object EmpleadoList   : Screen("empleados")
    data object EmpleadoDetail : Screen("empleados/{id}") { fun createRoute(id: Int) = "empleados/$id" }
    data object EmpleadoForm   : Screen("empleados/form?id={id}") { fun createRoute(id: Int? = null) = if (id != null) "empleados/form?id=$id" else "empleados/form?id=-1" }

    // Proveedores
    data object ProveedorList   : Screen("proveedores")
    data object ProveedorDetail : Screen("proveedores/{id}") { fun createRoute(id: Int) = "proveedores/$id" }
    data object ProveedorForm   : Screen("proveedores/form?id={id}") { fun createRoute(id: Int? = null) = if (id != null) "proveedores/form?id=$id" else "proveedores/form?id=-1" }

    // Pagos
    data object PagoList   : Screen("pagos")
    data object PagoDetail : Screen("pagos/{id}") { fun createRoute(id: Int) = "pagos/$id" }
    data object PagoForm   : Screen("pagos/form?id={id}") { fun createRoute(id: Int? = null) = if (id != null) "pagos/form?id=$id" else "pagos/form?id=-1" }
}
