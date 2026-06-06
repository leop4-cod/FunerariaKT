package com.funeraria.app.di

import com.funeraria.app.data.repository.*
import com.funeraria.app.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton abstract fun bindAuth(impl: AuthRepositoryImpl):         AuthRepository
    @Binds @Singleton abstract fun bindCliente(impl: ClienteRepositoryImpl):   ClienteRepository
    @Binds @Singleton abstract fun bindDifunto(impl: DifuntoRepositoryImpl):   DifuntoRepository
    @Binds @Singleton abstract fun bindProducto(impl: ProductoRepositoryImpl): ProductoRepository
    @Binds @Singleton abstract fun bindServicio(impl: ServicioRepositoryImpl): ServicioRepository
    @Binds @Singleton abstract fun bindContrato(impl: ContratoRepositoryImpl): ContratoRepository
    @Binds @Singleton abstract fun bindEmpleado(impl: EmpleadoRepositoryImpl): EmpleadoRepository
    @Binds @Singleton abstract fun bindProveedor(impl: ProveedorRepositoryImpl):ProveedorRepository
    @Binds @Singleton abstract fun bindPago(impl: PagoRepositoryImpl):         PagoRepository
}
