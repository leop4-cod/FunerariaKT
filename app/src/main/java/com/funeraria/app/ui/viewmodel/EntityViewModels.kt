package com.funeraria.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.funeraria.app.data.remote.dto.*
import com.funeraria.app.domain.model.*
import com.funeraria.app.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// ─── Generic list state ───────────────────────────────────────────────────────
data class ListState<T>(
    val items: List<T>     = emptyList(),
    val isLoading: Boolean = false,
    val error: String?     = null,
    val currentPage: Int   = 1,
    val hasMore: Boolean   = false,
    val totalCount: Int    = 0,
    val search: String     = ""
)
data class DetailState<T>(
    val item: T?           = null,
    val isLoading: Boolean = false,
    val error: String?     = null,
    val isSaving: Boolean  = false,
    val saveError: String? = null,
    val saveSuccess: Boolean = false
)

// ─── CLIENTE ─────────────────────────────────────────────────────────────────
@HiltViewModel
class ClienteViewModel @Inject constructor(private val repo: ClienteRepository) : ViewModel() {
    private val _state = MutableStateFlow(ListState<ClienteDto>())
    val state = _state.asStateFlow()
    private val _detail = MutableStateFlow(DetailState<Cliente>())
    val detail = _detail.asStateFlow()

    init { loadItems() }

    fun loadItems(search: String? = null) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null, currentPage = 1) }
            repo.getAll(1, search?.takeIf { it.isNotBlank() })
                .onSuccess { p -> _state.update { it.copy(items = p.results, isLoading = false, hasMore = p.next != null, totalCount = p.count, search = search ?: "") } }
                .onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
        }
    }
    fun loadMore() {
        val st = _state.value
        if (!st.hasMore || st.isLoading) return
        viewModelScope.launch {
            val next = st.currentPage + 1
            _state.update { it.copy(isLoading = true) }
            repo.getAll(next, st.search.takeIf { it.isNotBlank() })
                .onSuccess { p -> _state.update { it.copy(items = it.items + p.results, isLoading = false, hasMore = p.next != null, currentPage = next) } }
                .onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
        }
    }
    fun loadById(id: Int) {
        viewModelScope.launch {
            _detail.update { it.copy(isLoading = true, error = null) }
            repo.getById(id)
                .onSuccess { c -> _detail.update { it.copy(item = c, isLoading = false) } }
                .onFailure { e -> _detail.update { it.copy(isLoading = false, error = e.message) } }
        }
    }
    fun save(id: Int?, req: ClienteRequest, onDone: () -> Unit) {
        viewModelScope.launch {
            _detail.update { it.copy(isSaving = true, saveError = null) }
            val result = if (id == null || id < 0) repo.create(req) else repo.update(id, req)
            result
                .onSuccess { _detail.update { it.copy(isSaving = false, saveSuccess = true) }; loadItems(); onDone() }
                .onFailure { e -> _detail.update { it.copy(isSaving = false, saveError = e.message) } }
        }
    }
    fun delete(id: Int, onDone: () -> Unit) {
        viewModelScope.launch {
            repo.delete(id).onSuccess { loadItems(); onDone() }
                .onFailure { e -> _detail.update { it.copy(error = e.message) } }
        }
    }
    fun clearErrors() { _detail.update { it.copy(error = null, saveError = null, saveSuccess = false) } }
}

// ─── DIFUNTO ─────────────────────────────────────────────────────────────────
@HiltViewModel
class DifuntoViewModel @Inject constructor(private val repo: DifuntoRepository) : ViewModel() {
    private val _state = MutableStateFlow(ListState<DifuntoDto>())
    val state = _state.asStateFlow()
    private val _detail = MutableStateFlow(DetailState<Difunto>())
    val detail = _detail.asStateFlow()
    init { loadItems() }
    fun loadItems(search: String? = null) { viewModelScope.launch { _state.update { it.copy(isLoading = true, error = null, currentPage = 1) }; repo.getAll(1, search?.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = p.results, isLoading = false, hasMore = p.next != null, totalCount = p.count, search = search ?: "") } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadMore() { val st = _state.value; if (!st.hasMore || st.isLoading) return; viewModelScope.launch { val next = st.currentPage + 1; _state.update { it.copy(isLoading = true) }; repo.getAll(next, st.search.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = it.items + p.results, isLoading = false, hasMore = p.next != null, currentPage = next) } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadById(id: Int) { viewModelScope.launch { _detail.update { it.copy(isLoading = true, error = null) }; repo.getById(id).onSuccess { c -> _detail.update { it.copy(item = c, isLoading = false) } }.onFailure { e -> _detail.update { it.copy(isLoading = false, error = e.message) } } } }
    fun save(id: Int?, req: DifuntoRequest, onDone: () -> Unit) { viewModelScope.launch { _detail.update { it.copy(isSaving = true, saveError = null) }; val r = if (id == null || id < 0) repo.create(req) else repo.update(id, req); r.onSuccess { _detail.update { it.copy(isSaving = false, saveSuccess = true) }; loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(isSaving = false, saveError = e.message) } } } }
    fun delete(id: Int, onDone: () -> Unit) { viewModelScope.launch { repo.delete(id).onSuccess { loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(error = e.message) } } } }
    fun clearErrors() { _detail.update { it.copy(error = null, saveError = null, saveSuccess = false) } }
}

// ─── PRODUCTO ────────────────────────────────────────────────────────────────
@HiltViewModel
class ProductoViewModel @Inject constructor(private val repo: ProductoRepository) : ViewModel() {
    private val _state = MutableStateFlow(ListState<ProductoDto>())
    val state = _state.asStateFlow()
    private val _detail = MutableStateFlow(DetailState<Producto>())
    val detail = _detail.asStateFlow()
    init { loadItems() }
    fun loadItems(search: String? = null) { viewModelScope.launch { _state.update { it.copy(isLoading = true, error = null, currentPage = 1) }; repo.getAll(1, search?.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = p.results, isLoading = false, hasMore = p.next != null, totalCount = p.count, search = search ?: "") } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadMore() { val st = _state.value; if (!st.hasMore || st.isLoading) return; viewModelScope.launch { val next = st.currentPage + 1; _state.update { it.copy(isLoading = true) }; repo.getAll(next, st.search.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = it.items + p.results, isLoading = false, hasMore = p.next != null, currentPage = next) } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadById(id: Int) { viewModelScope.launch { _detail.update { it.copy(isLoading = true, error = null) }; repo.getById(id).onSuccess { c -> _detail.update { it.copy(item = c, isLoading = false) } }.onFailure { e -> _detail.update { it.copy(isLoading = false, error = e.message) } } } }
    fun save(id: Int?, req: ProductoRequest, onDone: () -> Unit) { viewModelScope.launch { _detail.update { it.copy(isSaving = true, saveError = null) }; val r = if (id == null || id < 0) repo.create(req) else repo.update(id, req); r.onSuccess { _detail.update { it.copy(isSaving = false, saveSuccess = true) }; loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(isSaving = false, saveError = e.message) } } } }
    fun delete(id: Int, onDone: () -> Unit) { viewModelScope.launch { repo.delete(id).onSuccess { loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(error = e.message) } } } }
    fun clearErrors() { _detail.update { it.copy(error = null, saveError = null, saveSuccess = false) } }
}

// ─── SERVICIO ────────────────────────────────────────────────────────────────
@HiltViewModel
class ServicioViewModel @Inject constructor(private val repo: ServicioRepository) : ViewModel() {
    private val _state = MutableStateFlow(ListState<ServicioDto>())
    val state = _state.asStateFlow()
    private val _detail = MutableStateFlow(DetailState<Servicio>())
    val detail = _detail.asStateFlow()
    init { loadItems() }
    fun loadItems(search: String? = null) { viewModelScope.launch { _state.update { it.copy(isLoading = true, error = null, currentPage = 1) }; repo.getAll(1, search?.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = p.results, isLoading = false, hasMore = p.next != null, totalCount = p.count, search = search ?: "") } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadMore() { val st = _state.value; if (!st.hasMore || st.isLoading) return; viewModelScope.launch { val next = st.currentPage + 1; _state.update { it.copy(isLoading = true) }; repo.getAll(next, st.search.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = it.items + p.results, isLoading = false, hasMore = p.next != null, currentPage = next) } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadById(id: Int) { viewModelScope.launch { _detail.update { it.copy(isLoading = true, error = null) }; repo.getById(id).onSuccess { c -> _detail.update { it.copy(item = c, isLoading = false) } }.onFailure { e -> _detail.update { it.copy(isLoading = false, error = e.message) } } } }
    fun save(id: Int?, req: ServicioRequest, onDone: () -> Unit) { viewModelScope.launch { _detail.update { it.copy(isSaving = true, saveError = null) }; val r = if (id == null || id < 0) repo.create(req) else repo.update(id, req); r.onSuccess { _detail.update { it.copy(isSaving = false, saveSuccess = true) }; loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(isSaving = false, saveError = e.message) } } } }
    fun delete(id: Int, onDone: () -> Unit) { viewModelScope.launch { repo.delete(id).onSuccess { loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(error = e.message) } } } }
    fun clearErrors() { _detail.update { it.copy(error = null, saveError = null, saveSuccess = false) } }
}

// ─── CONTRATO ────────────────────────────────────────────────────────────────
@HiltViewModel
class ContratoViewModel @Inject constructor(private val repo: ContratoRepository) : ViewModel() {
    private val _state = MutableStateFlow(ListState<ContratoDto>())
    val state = _state.asStateFlow()
    private val _detail = MutableStateFlow(DetailState<Contrato>())
    val detail = _detail.asStateFlow()
    init { loadItems() }
    fun loadItems(search: String? = null) { viewModelScope.launch { _state.update { it.copy(isLoading = true, error = null, currentPage = 1) }; repo.getAll(1, search?.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = p.results, isLoading = false, hasMore = p.next != null, totalCount = p.count, search = search ?: "") } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadMore() { val st = _state.value; if (!st.hasMore || st.isLoading) return; viewModelScope.launch { val next = st.currentPage + 1; _state.update { it.copy(isLoading = true) }; repo.getAll(next, st.search.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = it.items + p.results, isLoading = false, hasMore = p.next != null, currentPage = next) } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadById(id: Int) { viewModelScope.launch { _detail.update { it.copy(isLoading = true, error = null) }; repo.getById(id).onSuccess { c -> _detail.update { it.copy(item = c, isLoading = false) } }.onFailure { e -> _detail.update { it.copy(isLoading = false, error = e.message) } } } }
    fun save(id: Int?, req: ContratoRequest, onDone: () -> Unit) { viewModelScope.launch { _detail.update { it.copy(isSaving = true, saveError = null) }; val r = if (id == null || id < 0) repo.create(req) else repo.update(id, req); r.onSuccess { _detail.update { it.copy(isSaving = false, saveSuccess = true) }; loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(isSaving = false, saveError = e.message) } } } }
    fun delete(id: Int, onDone: () -> Unit) { viewModelScope.launch { repo.delete(id).onSuccess { loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(error = e.message) } } } }
    fun clearErrors() { _detail.update { it.copy(error = null, saveError = null, saveSuccess = false) } }
}

// ─── EMPLEADO ────────────────────────────────────────────────────────────────
@HiltViewModel
class EmpleadoViewModel @Inject constructor(private val repo: EmpleadoRepository) : ViewModel() {
    private val _state = MutableStateFlow(ListState<EmpleadoDto>())
    val state = _state.asStateFlow()
    private val _detail = MutableStateFlow(DetailState<Empleado>())
    val detail = _detail.asStateFlow()
    init { loadItems() }
    fun loadItems(search: String? = null) { viewModelScope.launch { _state.update { it.copy(isLoading = true, error = null, currentPage = 1) }; repo.getAll(1, search?.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = p.results, isLoading = false, hasMore = p.next != null, totalCount = p.count, search = search ?: "") } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadMore() { val st = _state.value; if (!st.hasMore || st.isLoading) return; viewModelScope.launch { val next = st.currentPage + 1; _state.update { it.copy(isLoading = true) }; repo.getAll(next, st.search.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = it.items + p.results, isLoading = false, hasMore = p.next != null, currentPage = next) } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadById(id: Int) { viewModelScope.launch { _detail.update { it.copy(isLoading = true, error = null) }; repo.getById(id).onSuccess { c -> _detail.update { it.copy(item = c, isLoading = false) } }.onFailure { e -> _detail.update { it.copy(isLoading = false, error = e.message) } } } }
    fun save(id: Int?, req: EmpleadoRequest, onDone: () -> Unit) { viewModelScope.launch { _detail.update { it.copy(isSaving = true, saveError = null) }; val r = if (id == null || id < 0) repo.create(req) else repo.update(id, req); r.onSuccess { _detail.update { it.copy(isSaving = false, saveSuccess = true) }; loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(isSaving = false, saveError = e.message) } } } }
    fun delete(id: Int, onDone: () -> Unit) { viewModelScope.launch { repo.delete(id).onSuccess { loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(error = e.message) } } } }
    fun clearErrors() { _detail.update { it.copy(error = null, saveError = null, saveSuccess = false) } }
}

// ─── PROVEEDOR ───────────────────────────────────────────────────────────────
@HiltViewModel
class ProveedorViewModel @Inject constructor(private val repo: ProveedorRepository) : ViewModel() {
    private val _state = MutableStateFlow(ListState<ProveedorDto>())
    val state = _state.asStateFlow()
    private val _detail = MutableStateFlow(DetailState<Proveedor>())
    val detail = _detail.asStateFlow()
    init { loadItems() }
    fun loadItems(search: String? = null) { viewModelScope.launch { _state.update { it.copy(isLoading = true, error = null, currentPage = 1) }; repo.getAll(1, search?.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = p.results, isLoading = false, hasMore = p.next != null, totalCount = p.count, search = search ?: "") } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadMore() { val st = _state.value; if (!st.hasMore || st.isLoading) return; viewModelScope.launch { val next = st.currentPage + 1; _state.update { it.copy(isLoading = true) }; repo.getAll(next, st.search.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = it.items + p.results, isLoading = false, hasMore = p.next != null, currentPage = next) } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadById(id: Int) { viewModelScope.launch { _detail.update { it.copy(isLoading = true, error = null) }; repo.getById(id).onSuccess { c -> _detail.update { it.copy(item = c, isLoading = false) } }.onFailure { e -> _detail.update { it.copy(isLoading = false, error = e.message) } } } }
    fun save(id: Int?, req: ProveedorRequest, onDone: () -> Unit) { viewModelScope.launch { _detail.update { it.copy(isSaving = true, saveError = null) }; val r = if (id == null || id < 0) repo.create(req) else repo.update(id, req); r.onSuccess { _detail.update { it.copy(isSaving = false, saveSuccess = true) }; loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(isSaving = false, saveError = e.message) } } } }
    fun delete(id: Int, onDone: () -> Unit) { viewModelScope.launch { repo.delete(id).onSuccess { loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(error = e.message) } } } }
    fun clearErrors() { _detail.update { it.copy(error = null, saveError = null, saveSuccess = false) } }
}

// ─── PAGO ────────────────────────────────────────────────────────────────────
@HiltViewModel
class PagoViewModel @Inject constructor(private val repo: PagoRepository) : ViewModel() {
    private val _state = MutableStateFlow(ListState<PagoDto>())
    val state = _state.asStateFlow()
    private val _detail = MutableStateFlow(DetailState<Pago>())
    val detail = _detail.asStateFlow()
    init { loadItems() }
    fun loadItems(search: String? = null) { viewModelScope.launch { _state.update { it.copy(isLoading = true, error = null, currentPage = 1) }; repo.getAll(1, search?.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = p.results, isLoading = false, hasMore = p.next != null, totalCount = p.count, search = search ?: "") } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadMore() { val st = _state.value; if (!st.hasMore || st.isLoading) return; viewModelScope.launch { val next = st.currentPage + 1; _state.update { it.copy(isLoading = true) }; repo.getAll(next, st.search.takeIf { it.isNotBlank() }).onSuccess { p -> _state.update { it.copy(items = it.items + p.results, isLoading = false, hasMore = p.next != null, currentPage = next) } }.onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } } } }
    fun loadById(id: Int) { viewModelScope.launch { _detail.update { it.copy(isLoading = true, error = null) }; repo.getById(id).onSuccess { c -> _detail.update { it.copy(item = c, isLoading = false) } }.onFailure { e -> _detail.update { it.copy(isLoading = false, error = e.message) } } } }
    fun save(id: Int?, req: PagoRequest, onDone: () -> Unit) { viewModelScope.launch { _detail.update { it.copy(isSaving = true, saveError = null) }; val r = if (id == null || id < 0) repo.create(req) else repo.update(id, req); r.onSuccess { _detail.update { it.copy(isSaving = false, saveSuccess = true) }; loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(isSaving = false, saveError = e.message) } } } }
    fun delete(id: Int, onDone: () -> Unit) { viewModelScope.launch { repo.delete(id).onSuccess { loadItems(); onDone() }.onFailure { e -> _detail.update { it.copy(error = e.message) } } } }
    fun clearErrors() { _detail.update { it.copy(error = null, saveError = null, saveSuccess = false) } }
}
