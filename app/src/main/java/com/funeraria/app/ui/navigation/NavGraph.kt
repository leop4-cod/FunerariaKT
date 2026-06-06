package com.funeraria.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.funeraria.app.navigation.Screen
import com.funeraria.app.ui.auth.LoginScreen
import com.funeraria.app.ui.components.LoadingScreen
import com.funeraria.app.ui.entity.*
import com.funeraria.app.ui.home.HomeScreen
import com.funeraria.app.ui.profile.ProfileScreen
import com.funeraria.app.ui.viewmodel.*

@Composable
fun NavGraph(authViewModel: AuthViewModel) {
    val navController       = rememberNavController()
    val isChecking         by authViewModel.isCheckingSession.collectAsState()
    val isAuthenticated    by authViewModel.isAuthenticated.collectAsState()
    val isAdmin            by authViewModel.isAdmin.collectAsState()

    // Shared entity ViewModels scoped to NavGraph lifetime
    val clienteVm:  ClienteViewModel  = hiltViewModel()
    val difuntoVm:  DifuntoViewModel  = hiltViewModel()
    val productoVm: ProductoViewModel = hiltViewModel()
    val servicioVm: ServicioViewModel = hiltViewModel()
    val contratoVm: ContratoViewModel = hiltViewModel()
    val empleadoVm: EmpleadoViewModel = hiltViewModel()
    val proveedorVm:ProveedorViewModel= hiltViewModel()
    val pagoVm:     PagoViewModel     = hiltViewModel()

    if (isChecking) { LoadingScreen("Iniciando..."); return }

    LaunchedEffect(isAuthenticated) {
        if (!isAuthenticated) {
            navController.navigate(Screen.Login.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    val bottomBarRoutes = setOf(
        Screen.Home.route, Screen.ClienteList.route, Screen.ProductoList.route,
        Screen.ContratoList.route, Screen.Profile.route
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute      = navBackStackEntry?.destination?.route
    val showBottomBar     = currentRoute in bottomBarRoutes

    NavHost(
        navController    = navController,
        startDestination = if (isAuthenticated) Screen.Home.route else Screen.Login.route
    ) {
        // Auth
        composable(Screen.Login.route) {
            LoginScreen(authViewModel) {
                navController.navigate(Screen.Home.route) { popUpTo(Screen.Login.route) { inclusive = true } }
            }
        }

        // Main with BottomBar
        composable(Screen.Home.route) {
            MainScaffold(navController, showBottomBar = true) {
                HomeScreen(authViewModel, clienteVm, contratoVm, productoVm, pagoVm) { route -> navController.navigate(route) }
            }
        }
        composable(Screen.Profile.route) {
            MainScaffold(navController, showBottomBar = true) {
                ProfileScreen(authViewModel) {
                    navController.navigate(Screen.Login.route) { popUpTo(0) { inclusive = true } }
                }
            }
        }

        // ── CLIENTES ──────────────────────────────────────────────────────
        composable(Screen.ClienteList.route) {
            MainScaffold(navController, showBottomBar = true) {
                ClienteListScreen(clienteVm, isAdmin, { navController.navigate(Screen.ClienteDetail.createRoute(it)) }, { navController.navigate(Screen.ClienteForm.createRoute()) })
            }
        }
        composable(Screen.ClienteDetail.route, listOf(navArgument("id") { type = NavType.IntType })) { b ->
            val id = b.arguments?.getInt("id") ?: return@composable
            MainScaffold(navController, false) { ClienteDetailScreen(id, clienteVm, isAdmin, { navController.popBackStack() }, { navController.navigate(Screen.ClienteForm.createRoute(it)) }) }
        }
        composable(Screen.ClienteForm.route, listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })) { b ->
            val id = b.arguments?.getInt("id")?.takeIf { it >= 0 }
            MainScaffold(navController, false) { ClienteFormScreen(id, clienteVm) { navController.popBackStack() } }
        }

        // ── DIFUNTOS ──────────────────────────────────────────────────────
        composable(Screen.DifuntoList.route) {
            MainScaffold(navController, false) { DifuntoListScreen(difuntoVm, isAdmin, { navController.navigate(Screen.DifuntoDetail.createRoute(it)) }, { navController.navigate(Screen.DifuntoForm.createRoute()) }) }
        }
        composable(Screen.DifuntoDetail.route, listOf(navArgument("id") { type = NavType.IntType })) { b ->
            val id = b.arguments?.getInt("id") ?: return@composable
            MainScaffold(navController, false) { DifuntoDetailScreen(id, difuntoVm, isAdmin, { navController.popBackStack() }, { navController.navigate(Screen.DifuntoForm.createRoute(it)) }) }
        }
        composable(Screen.DifuntoForm.route, listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })) { b ->
            val id = b.arguments?.getInt("id")?.takeIf { it >= 0 }
            MainScaffold(navController, false) { DifuntoFormScreen(id, difuntoVm) { navController.popBackStack() } }
        }

        // ── PRODUCTOS ─────────────────────────────────────────────────────
        composable(Screen.ProductoList.route) {
            MainScaffold(navController, true) { ProductoListScreen(productoVm, isAdmin, { navController.navigate(Screen.ProductoDetail.createRoute(it)) }, { navController.navigate(Screen.ProductoForm.createRoute()) }) }
        }
        composable(Screen.ProductoDetail.route, listOf(navArgument("id") { type = NavType.IntType })) { b ->
            val id = b.arguments?.getInt("id") ?: return@composable
            MainScaffold(navController, false) { ProductoDetailScreen(id, productoVm, isAdmin, { navController.popBackStack() }, { navController.navigate(Screen.ProductoForm.createRoute(it)) }) }
        }
        composable(Screen.ProductoForm.route, listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })) { b ->
            val id = b.arguments?.getInt("id")?.takeIf { it >= 0 }
            MainScaffold(navController, false) { ProductoFormScreen(id, productoVm) { navController.popBackStack() } }
        }

        // ── SERVICIOS ─────────────────────────────────────────────────────
        composable(Screen.ServicioList.route) {
            MainScaffold(navController, false) { ServicioListScreen(servicioVm, isAdmin, { navController.navigate(Screen.ServicioDetail.createRoute(it)) }, { navController.navigate(Screen.ServicioForm.createRoute()) }) }
        }
        composable(Screen.ServicioDetail.route, listOf(navArgument("id") { type = NavType.IntType })) { b ->
            val id = b.arguments?.getInt("id") ?: return@composable
            MainScaffold(navController, false) { ServicioDetailScreen(id, servicioVm, isAdmin, { navController.popBackStack() }, { navController.navigate(Screen.ServicioForm.createRoute(it)) }) }
        }
        composable(Screen.ServicioForm.route, listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })) { b ->
            val id = b.arguments?.getInt("id")?.takeIf { it >= 0 }
            MainScaffold(navController, false) { ServicioFormScreen(id, servicioVm) { navController.popBackStack() } }
        }

        // ── CONTRATOS ─────────────────────────────────────────────────────
        composable(Screen.ContratoList.route) {
            MainScaffold(navController, true) { ContratoListScreen(contratoVm, isAdmin, { navController.navigate(Screen.ContratoDetail.createRoute(it)) }, { navController.navigate(Screen.ContratoForm.createRoute()) }) }
        }
        composable(Screen.ContratoDetail.route, listOf(navArgument("id") { type = NavType.IntType })) { b ->
            val id = b.arguments?.getInt("id") ?: return@composable
            MainScaffold(navController, false) { ContratoDetailScreen(id, contratoVm, isAdmin, { navController.popBackStack() }, { navController.navigate(Screen.ContratoForm.createRoute(it)) }) }
        }
        composable(Screen.ContratoForm.route, listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })) { b ->
            val id = b.arguments?.getInt("id")?.takeIf { it >= 0 }
            MainScaffold(navController, false) { ContratoFormScreen(id, contratoVm) { navController.popBackStack() } }
        }

        // ── EMPLEADOS ─────────────────────────────────────────────────────
        composable(Screen.EmpleadoList.route) {
            MainScaffold(navController, false) { EmpleadoListScreen(empleadoVm, isAdmin, { navController.navigate(Screen.EmpleadoDetail.createRoute(it)) }, { navController.navigate(Screen.EmpleadoForm.createRoute()) }) }
        }
        composable(Screen.EmpleadoDetail.route, listOf(navArgument("id") { type = NavType.IntType })) { b ->
            val id = b.arguments?.getInt("id") ?: return@composable
            MainScaffold(navController, false) { EmpleadoDetailScreen(id, empleadoVm, isAdmin, { navController.popBackStack() }, { navController.navigate(Screen.EmpleadoForm.createRoute(it)) }) }
        }
        composable(Screen.EmpleadoForm.route, listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })) { b ->
            val id = b.arguments?.getInt("id")?.takeIf { it >= 0 }
            MainScaffold(navController, false) { EmpleadoFormScreen(id, empleadoVm) { navController.popBackStack() } }
        }

        // ── PROVEEDORES ───────────────────────────────────────────────────
        composable(Screen.ProveedorList.route) {
            MainScaffold(navController, false) { ProveedorListScreen(proveedorVm, isAdmin, { navController.navigate(Screen.ProveedorDetail.createRoute(it)) }, { navController.navigate(Screen.ProveedorForm.createRoute()) }) }
        }
        composable(Screen.ProveedorDetail.route, listOf(navArgument("id") { type = NavType.IntType })) { b ->
            val id = b.arguments?.getInt("id") ?: return@composable
            MainScaffold(navController, false) { ProveedorDetailScreen(id, proveedorVm, isAdmin, { navController.popBackStack() }, { navController.navigate(Screen.ProveedorForm.createRoute(it)) }) }
        }
        composable(Screen.ProveedorForm.route, listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })) { b ->
            val id = b.arguments?.getInt("id")?.takeIf { it >= 0 }
            MainScaffold(navController, false) { ProveedorFormScreen(id, proveedorVm) { navController.popBackStack() } }
        }

        // ── PAGOS ─────────────────────────────────────────────────────────
        composable(Screen.PagoList.route) {
            MainScaffold(navController, false) { PagoListScreen(pagoVm, isAdmin, { navController.navigate(Screen.PagoDetail.createRoute(it)) }, { navController.navigate(Screen.PagoForm.createRoute()) }) }
        }
        composable(Screen.PagoDetail.route, listOf(navArgument("id") { type = NavType.IntType })) { b ->
            val id = b.arguments?.getInt("id") ?: return@composable
            MainScaffold(navController, false) { PagoDetailScreen(id, pagoVm, isAdmin, { navController.popBackStack() }, { navController.navigate(Screen.PagoForm.createRoute(it)) }) }
        }
        composable(Screen.PagoForm.route, listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })) { b ->
            val id = b.arguments?.getInt("id")?.takeIf { it >= 0 }
            MainScaffold(navController, false) { PagoFormScreen(id, pagoVm) { navController.popBackStack() } }
        }
    }
}

@Composable
private fun MainScaffold(
    navController: androidx.navigation.NavController,
    showBottomBar: Boolean,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = { if (showBottomBar) BottomNavBar(navController) }
    ) { padding ->
        androidx.compose.foundation.layout.Box(modifier = Modifier.padding(padding)) {
            content()
        }
    }
}
